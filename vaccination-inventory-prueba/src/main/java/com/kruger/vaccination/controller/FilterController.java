package com.kruger.vaccination.controller;

import com.kruger.vaccination.model.Employee;
import com.kruger.vaccination.model.RecordVaccination;
import com.kruger.vaccination.service.EmployeeService;
import com.kruger.vaccination.service.RecordVaccinationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jdismael
 */
@RestController
@RequestMapping("/api/user")
public class FilterController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RecordVaccinationService recordVaccinationService;

    @GetMapping(value = "/status/{vaccinationStatus}")
    @ApiOperation(value = "Obtain records by vaccination status")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok "),
        @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity getEmployeesByVaccinationStatus(@PathVariable boolean vaccinationStatus) {
        try {
            List<Employee> employees = this.employeeService.getEmployeesByStateVaccination(vaccinationStatus);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/dates")
    @ApiOperation(value = "Obtain records by date")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity findEmployeesByVaccinationDates(@RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate) {
        try {
            List<RecordVaccination> recordsVaccination = this.recordVaccinationService.findByDateBetween(startDate, endDate);
            return ResponseEntity.ok(recordsVaccination);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/type/{typeVaccineName}")
    @ApiOperation(value = "Obtain records by type of vaccine")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity findEmployeesByVaccineType(@PathVariable String typeVaccineName) {
        try {
            List<RecordVaccination> recordsVaccination = this.recordVaccinationService.findByTypeVaccineName(typeVaccineName);
            return ResponseEntity.ok(recordsVaccination);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
