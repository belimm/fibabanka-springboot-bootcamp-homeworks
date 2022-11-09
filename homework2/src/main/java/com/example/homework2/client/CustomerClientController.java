package com.example.homework2.client;

import com.example.homework2.entity.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Controller
@RequestMapping("client")
public class CustomerClientController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("customer/get/{customerId}")
    @ResponseBody
    public String getCustomerById(@PathVariable("customerId")Long customerId){

        String uri = "http://localhost:8080/api/v1/customer/"+customerId;
        Customer customer =restTemplate.getForObject(uri,Customer.class);

        return String.format("Customer whose ID is %s name's is %s",customerId,customer.getCustomerName());
    }

    @GetMapping("customer/post")
    @ResponseBody
    public String addCustomer(){
        Customer newCustomer = new Customer(4L,"Kaan",4000D);
        String uri = "http://localhost:8080/api/v1/customer";

        restTemplate.postForObject(
                uri,
                newCustomer,
                Customer.class
        );

        return "New Customer was added! "+ newCustomer.getCustomerName();
    }

    @GetMapping("/customer/put")
    @ResponseBody
    public String updateEmployeeById(){
        Long employeeId= 2L;

        Customer updatedCustomer = new Customer(
                employeeId,
                "Bet",
                10000D
        );


        String uri = "http://localhost:8080/api/v1/customer";
        restTemplate.exchange(
                uri+"/"+employeeId,
                HttpMethod.PUT,
                new HttpEntity<Customer>(updatedCustomer),
                Customer.class
        );

        return "Customer with ID "+updatedCustomer.getCustomerId()+" was updated!";
    }

    @GetMapping("customer/delete")
    @ResponseBody
    public String deleteEmployeeById(){
        Long customerId = 2L;

        String uri = "http://localhost:8080/api/v1/customer";
        restTemplate.delete(uri+"/"+customerId);

        return "Customer with ID "+ customerId+" was deleted!";
    }
}
