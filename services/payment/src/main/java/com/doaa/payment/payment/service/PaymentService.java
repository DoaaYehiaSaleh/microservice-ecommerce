package com.doaa.payment.payment.service;

import com.doaa.payment.notification.NotificationProducer;
import com.doaa.payment.notification.PaymentNotificationRequest;
import com.doaa.payment.payment.dto.PaymentRequest;
import com.doaa.payment.payment.mapper.PaymentMapper;
import com.doaa.payment.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        try {
            var payment = repository.save(mapper.toPayment(request));
            payment.setCreatedDate(LocalDateTime.now());
            notificationProducer.sendPaymentNotification(
                    new PaymentNotificationRequest(
                            request.orderReference(),
                            request.amount(),
                            request.paymentMethod(),
                            request.customer().firstName(),
                            request.customer().lastName(),
                            request.customer().email()
                    )
            );
            return payment.getId();
        } catch (Exception ex) {
            log.error("Failed to create payment: {}", ex.toString(), ex);
            throw ex;
        }
    }

}
