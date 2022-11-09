package com.example.homework3.client;

import com.example.homework3.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Controller
@RequestMapping("/client/employee")
public class EmployeeClientController {


    private RestTemplate restTemplate;

    public EmployeeClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/get")
    @ResponseBody
    public String getEmployee(){
        Long employeeId=1L;
        String uri = "http://localhost:8080/api/v1/employee/"+employeeId;
        Optional<Employee> response = Optional.ofNullable(restTemplate.getForObject(uri, Employee.class));

        if(response.isPresent()){
            Employee employee = response.get();
            return "Employee "+employee.getEmployeeId()+" "+employee.getEmployeeName();
        }

        return "Employee with "+employeeId+" couldn't be found!";
    }


    @GetMapping("/post")
    @ResponseBody
    public String postEmployee(){
        Employee newEmployee = new Employee(
                "Ahmet",
                25000D
        );

        String uri = "http://localhost:8080/api/v1/employee";
        Optional<Employee> response = Optional.ofNullable(restTemplate.postForObject(uri,newEmployee,Employee.class));


        if(response.isPresent()){
            Employee employee = response.get();
            return "Employee "+employee.getEmployeeId()+" "+employee.getEmployeeName();
        }

        return "Employee with name of 'Ahmet' couldn't be added!";
    }

    @PutMapping("/put")
    @ResponseBody
    public String putEmployee(){
        Long employeeId= 1L;

        Employee updatedEmployee = new Employee(
                employeeId,
                "Bet",
                10000D
        );

        String uri = "http://localhost:8080/api/v1/employee";
        restTemplate.exchange(
                uri+"/"+employeeId,
                HttpMethod.PUT,
                new HttpEntity<Employee>(updatedEmployee),
                Employee.class
        );

        return "Customer with ID "+updatedEmployee.getEmployeeId()+" was updated!";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteEmployee(){
        Long employeeId = 1L;

        String uri = "http://localhost:8080/api/v1/employee";
        restTemplate.delete(uri+"/"+employeeId);

        return "Employee with ID "+ employeeId+" was deleted!";
    }



}
