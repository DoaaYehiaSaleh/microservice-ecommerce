package com.doaa.order.order.service;

import com.doaa.order.customer.CustomerClient;
import com.doaa.order.exception.BusinessException;
import com.doaa.order.kafka.OrderConfirmation;
import com.doaa.order.kafka.OrderProducer;
import com.doaa.order.order.dto.OrderRequest;
import com.doaa.order.order.dto.OrderResponse;
import com.doaa.order.order.mapper.OrderMapper;
import com.doaa.order.order.repository.OrderRepository;
import com.doaa.order.orderline.OrderLineRequest;
import com.doaa.order.orderline.OrderLineService;
import com.doaa.order.payment.PaymentClient;
import com.doaa.order.payment.PaymentRequest;
import com.doaa.order.product.ProductClient;
import com.doaa.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    private final OrderRepository repository;

    public Integer createOrder(OrderRequest request) {
        var customer = customerClient.findById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer with id " + request.customerId()));

        var purchasedProducts = productClient.purchaseProducts(request.products());

        var order = repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        order.getReference(),
                        order.getTotalAmount(),
                        order.getPaymentMethod(),
                        customer,
                        purchasedProducts
                        )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toOrderResponse)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException("No order with id " + orderId));
    }
}
