package com.example.homework3.controller;

import com.example.homework3.entity.Employee;
import com.example.homework3.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees(){


        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();

        return ResponseEntity
            .ok()
            .body(employeeList);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId")Long employeeId){

        if(employeeId<=0){
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please enter a valid ID!");
        }

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if(employee.isPresent()) {
            return ResponseEntity
                .ok()
                .body(employee);
        }

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("There is no Employee with ID "+employeeId);
    }

    @PostMapping("/employee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee){

        System.out.println(employee);
        try{

            Employee newEmployee =employeeRepository.save(employee);

            return ResponseEntity
                .ok()
                .body(newEmployee);
        }
        catch (Error e){
            return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body("Something went wrong");
        }

    }

    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<?> updateEmployeeById(
            @PathVariable("employeeId") Long employeeId,
            @RequestBody Employee employee){

        Optional<Employee> e = employeeRepository.findById(employeeId);

        e.get().setEmployeeName(employee.getEmployeeName());
        e.get().setMonthlySalary(employee.getMonthlySalary());

        employeeRepository.save(e.get());

        return ResponseEntity
            .ok()
            .body(e.get());
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<?> deleteEmplyoeeById(@PathVariable("employeeId") Long employeeId){

        employeeRepository.deleteById(employeeId);

        return ResponseEntity
                .ok()
                .body("Employee with the ID "+employeeId+" was deleted from the list!");

    }
}
