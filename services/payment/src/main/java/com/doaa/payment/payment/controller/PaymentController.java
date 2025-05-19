package com.doaa.payment.payment.controller;

import com.doaa.payment.payment.dto.PaymentRequest;
import com.doaa.payment.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody @Valid PaymentRequest request) {
        try {
            Integer paymentId = service.createPayment(request);
            return ResponseEntity.ok(paymentId);
        } catch (Exception ex) {
            // log the exception (assuming you have a logger)
            log.error("Payment creation failed for orderReference={}", request.orderReference(), ex);
            // return meaningful error message and status 500
            return ResponseEntity.status(500)
                    .body("Payment processing failed: " + ex.getMessage());
        }
    }
}
