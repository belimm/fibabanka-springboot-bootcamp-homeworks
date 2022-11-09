package com.example.homework3.controller;

import com.example.homework3.dto.DepartmentDto;
import com.example.homework3.entity.Department;

import com.example.homework3.repository.DepartmentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/departments")
    public ResponseEntity<?> getDepartments(){

        List<Department> departmentList = (List<Department>) departmentRepository.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for(Department d:departmentList){
            departmentDtos.add(toDepartmentDto(d));
        }

        return ResponseEntity
                .ok()
                .body(departmentDtos);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("departmentId")Long departmentId){

        if(departmentId<=0){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Please enter a valid ID!");
        }

        Optional<Department> department = departmentRepository.findById(departmentId);

        if(department.isPresent()) {
            return ResponseEntity
                    .ok()
                    .body(toDepartmentDto(department.get()));
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("There is no Department with ID "+departmentId);
    }

    @PostMapping("/department")
    public ResponseEntity<?> addDepartment(@RequestBody Department department){
        try{
            Department newDepartment =departmentRepository.save(department);
            return ResponseEntity
                    .ok()
                    .body(toDepartmentDto(newDepartment));
        }
        catch (Error e){
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body("Something went wrong");
        }

    }

    @PutMapping("/department/{departmentId}")
    public ResponseEntity<?> updateDepartmentById(
            @PathVariable("departmentId") Long departmentId,
            @RequestBody Department department){

        Optional<Department> d = departmentRepository.findById(departmentId);

        d.get().setDepartmentName(department.getDepartmentName());


        departmentRepository.save(d.get());

        return ResponseEntity
                .ok()
                .body(d.get());
    }

    @DeleteMapping("/department/{departmentId}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable("departmentId") Long departmentId){

        departmentRepository.deleteById(departmentId);

        return ResponseEntity
            .ok()
            .body("Department with the ID "+departmentId+" was deleted from the list!");
    }

    public DepartmentDto toDepartmentDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentId(department.getDepartmentId());
        departmentDto.setDepartmentName(department.getDepartmentName());

        return departmentDto;
    }
}
