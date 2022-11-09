package com.homework1.server.controller;

import com.homework1.server.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author berklimoncu
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    ArrayList<Employee> employees= new ArrayList<>(
        List.of(
            new Employee(
                    1L,
                    "Berk Limoncu",
                    5000D
            ),
            new Employee(
                    2L,
                    "Mahmut Sezgin",
                    6000D
            ),
            new Employee(
                    3L,
                    "İrfan Kaan",
                    1000D
            )
        )
    );

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity
                .ok()
                .body(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {

        System.out.println(employee);
        employees.add(employee);

        System.out.println(employees);

        return ResponseEntity
                .ok()
                .body(employee);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        if (employeeId<=0){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Invalid Employee ID!");
        }
        Employee employee = null;

        for(Employee e: employees) {
            if(e.getEmployeeId().equals(employeeId)){
                employee = e;
                break;
            }
        }

        return ResponseEntity
                .ok()
                .body(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployeeById(
            @PathVariable("employeeId") Long employeeId,
            @RequestBody Employee employee){

        if (employeeId<=0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Invalid Employee ID!");
        }

        int foundIndex=0;
        boolean found=false;
        Employee newEmp = null;

        for (Employee e:employees) {

            if(e.getEmployeeId()==employeeId) {
                newEmp = employee;
                newEmp.setEmployeeId(employeeId);
                found=true;
                break;
            }
            foundIndex++;
        }

        if(found)
            employees.set(foundIndex,newEmp);

        return ResponseEntity
                .ok()
                .body(newEmp);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("employeeId") Long employeeId) {
        if (employeeId<=0){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Invalid Employee ID!");
        }

        int index=0;
        boolean founded=false;

        for(Employee e:employees) {
            if(e.getEmployeeId()==employeeId) {
                founded=true;
                break;
            }
            index++;
        }

        if(!founded)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Employee with \"+employeeId+ \" Does Not Exist!");

        employees.remove(index);

        return ResponseEntity
                .ok()
                .body("Employee with "+employeeId+ " Succesfuly Deleted");
    }




}
