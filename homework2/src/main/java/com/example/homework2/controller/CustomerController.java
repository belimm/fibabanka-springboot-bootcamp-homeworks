package com.example.homework2.controller;

import com.example.homework2.entity.Customer;
import com.example.homework2.service.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getCustomers(){

        List<Customer> customers = customerService.getCustomers();

        return ResponseEntity
            .ok()
            .body(customers);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable("customerId") Long customerId){
        if(customerId<=0) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please enter a valid ID!");
        }

        Optional<Customer> customer = customerService.getCustomerById(customerId);

        if(customer.isPresent())
            return ResponseEntity
                .ok()
                .body(customer);


        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("The customer with ID "+customerId+ " does not exist!");

    }

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerService.addCustomer(customer);

        return ResponseEntity
            .ok()
            .body(newCustomer);
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<?> updateCustomerById(
            @PathVariable("customerId") Long customerId,
            @RequestBody Customer customer) {

        if(customerId<=0) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please enter a valid ID!");
        }

        Optional<Customer> newCustomer = Optional.ofNullable(customerService.updateCustomerById(customer, customerId));

        if(newCustomer.isPresent())
            return ResponseEntity
                    .ok()
                    .body(newCustomer);

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("The customer with ID "+customerId+ " does not exist!");
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") Long customerId){

        String message = customerService.deleteCustomerById(customerId);

        return ResponseEntity
            .ok()
            .body(message);
    }

}
