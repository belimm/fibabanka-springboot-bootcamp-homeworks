package com.homework1.client;

import com.homework1.server.model.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;



@Controller
@RequestMapping("/client/employees")
public class EmployeeClientController {
    RestTemplate restTemplate = new RestTemplate();
    String uri = "http://localhost:8080/api/v1/employees";

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<?> getEmployeeById(){
        Employee employee = restTemplate.getForObject(uri+"/1",Employee.class);

        return ResponseEntity
                .ok()
                .body(employee);
    }

    @GetMapping("/post")
    @ResponseBody
    public ResponseEntity<?> addNewEmployee(){
        Employee newEmployee = new Employee(
                4L,
                "Aykut",
                1000D
        );

        restTemplate.postForObject(uri,newEmployee, Employee.class);

        return ResponseEntity
                .ok()
                .body("Employee was added!");
    }

    @GetMapping("/put")
    @ResponseBody
    public ResponseEntity<?> updateEmployeeById(){
        Long employeeId= 2L;

        Employee updatedEmployee = new Employee(
                employeeId,
                "Arcan",
                10000D
        );

        restTemplate.exchange(
                uri+"/"+employeeId,
                HttpMethod.PUT,
                new HttpEntity<Employee>(updatedEmployee),
                Employee.class
        );

        return ResponseEntity
                .ok()
                .body("Employee with id "+employeeId +" was updated!");
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> deleteEmployeeById(){
        Long employeeId = 3L;

        restTemplate.delete(uri+"/"+employeeId);
        return ResponseEntity
                .ok()
                .body("Employee with "+ employeeId+" was deleted!");
    }

}
