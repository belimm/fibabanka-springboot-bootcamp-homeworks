package com.example.homework3.client;

import com.example.homework3.entity.Department;
import com.example.homework3.entity.Employee;
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
@RequestMapping("/client/department")
public class DepartmentClientController {

    private RestTemplate restTemplate;

    public DepartmentClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/get")
    @ResponseBody
    public String getDepartment(){
        Long departmentId=1L;
        String uri = "http://localhost:8080/api/v1/department/"+departmentId;
        Optional<Department> response = Optional.ofNullable(restTemplate.getForObject(uri, Department.class));

        if(response.isPresent()){
            Department department = response.get();
            return "Department "+department.getDepartmentId()+" "+department.getDepartmentName();
        }

        return "Department with "+departmentId+" couldn't be found!";
    }


    @GetMapping("/post")
    @ResponseBody
    public String postDepartment(){
        Department newDepartment = new Department(
                4L,
                "Marketing"
        );

        String uri = "http://localhost:8080/api/v1/department";
        Optional<Department> response = Optional.ofNullable(restTemplate.postForObject(uri,newDepartment,Department.class));


        if(response.isPresent()){
            Department department = response.get();
            return "Department "+department.getDepartmentId()+" "+department.getDepartmentName();
        }

        return "";
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
