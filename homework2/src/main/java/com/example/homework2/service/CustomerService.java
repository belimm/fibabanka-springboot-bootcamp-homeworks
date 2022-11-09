package com.example.homework2.service;

import com.example.homework2.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public List<Customer> getCustomers();

    Optional<Customer> getCustomerById(Long customerId);

    Customer addCustomer(Customer customer);

    Customer updateCustomerById(Customer customer, Long customerId);

    String deleteCustomerById(Long customerId);
}
