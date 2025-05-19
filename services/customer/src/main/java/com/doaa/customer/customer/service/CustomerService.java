package com.doaa.customer.customer.service;

import com.doaa.customer.customer.dto.CustomerRequest;
import com.doaa.customer.customer.dto.CustomerResponse;
import com.doaa.customer.customer.mapper.CustomerMapper;
import com.doaa.customer.customer.model.Customer;
import com.doaa.customer.customer.repository.CustomerRepository;
import com.doaa.customer.excption.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer with id " + request.id() + " not found."));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer,  CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if (request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getAllCustomers() {
        return repository.findAll().stream()
                .map(mapper::fromCustomer)
                .toList();
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + customerId + " not found."));
    }


    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}