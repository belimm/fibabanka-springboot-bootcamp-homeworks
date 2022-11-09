package com.example.homework2.service;

import com.example.homework2.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    ArrayList<Customer> customers = new ArrayList<>(List.of(
        new Customer(
                1L,
                "Berk",
                1000D
        ),
        new Customer(
                2L,
                "Mahmut",
                2000D
        ),
        new Customer(
                3L,
                "Arcan",
                3000D
        ))
    );


    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        Optional<Customer> customer = Optional.empty();

        for(Customer c:customers){
            if(c.getCustomerId()==customerId){
                customer = Optional.of((Customer) c);
                break;
            }
        }

        return customer;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customers.add(customer);

        return customer;
    }

    @Override
    public Customer updateCustomerById(Customer customer, Long customerId) {

        int foundedIndex = 0;
        boolean founded = false;

        for (Customer c:customers) {
            if(c.getCustomerId() == customerId) {
                founded = true;
                break;
            }
            foundedIndex++;
        }

        if (founded){
            customers.set(foundedIndex, customer);
        }

        return customer;
    }

    @Override
    public String deleteCustomerById(Long customerId) {
        int foundedIndex = 0;
        boolean founded = false;


        for(Customer c:customers) {
            if(c.getCustomerId() == customerId){
                founded = true;
                break;
            }
        }

        if(founded){
            customers.remove(foundedIndex);
            return "Customer with ID "+ customerId+" removed from the list!";
        }

        return "Customer with ID couldnt be found in the list!";
    }


}
