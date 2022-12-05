/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.controller;

import com.kruger.vaccination.dto.CreateEmployeeRQ;
import com.kruger.vaccination.dto.EmployeeUpdateRQ;
import com.kruger.vaccination.model.Employee;
import com.kruger.vaccination.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DIANA
 */
@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ApiOperation(value = "Get all employees")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok,  when you find all employees"),
        @ApiResponse(code = 400, message = "Bad Request, employees not found")})
    public ResponseEntity getAllEmployees() {
        try {
            List<Employee> employees = this.employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation(value = "Create new employees")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok, when you create the employee"),
        @ApiResponse(code = 400, message = "Bad Request, could not create the employee")})
    public ResponseEntity createEmployee(@RequestBody @Valid CreateEmployeeRQ employeeCreateRQ) {
        try {
            return ResponseEntity.ok(this.employeeService.createEmployee(employeeCreateRQ));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/{identification}")
    @ApiOperation(value = "Obtain employees by identification")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok, the employee has been found by his ID"),
        @ApiResponse(code = 400, message = "Bad Request, employee not found")})
    public ResponseEntity getEmployeeByIdentification(@PathVariable String identification) {
        try {
            Employee employee = this.employeeService.getEmployeeByIdentification(identification);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{identification}")
    @ApiOperation(value = "Update employee")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok, the employee has been modified "),
        @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity updateEmployee(@RequestBody EmployeeUpdateRQ employeeUpdateRQ, @PathVariable String identification) {
        try {
            this.employeeService.updateEmployeeInfoByIdentification(employeeUpdateRQ, identification);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{identification}")
    @ApiOperation(value = "Delete employee by identification")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok, the employee has been deleted"),
        @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity deleteEmployeeByIdentification(@PathVariable String identification) {
        try {
            this.employeeService.deleteEmployeeByIdentification(identification);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

