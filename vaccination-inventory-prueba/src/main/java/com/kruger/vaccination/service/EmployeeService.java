/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.service;

import com.kruger.vaccination.dao.EmployeeRepository;
import com.kruger.vaccination.dao.RecordVaccinationRepository;
import com.kruger.vaccination.dao.TypeVaccineRepository;
import com.kruger.vaccination.dao.UserRepository;
import com.kruger.vaccination.dto.CreateEmployeeRequest;
import com.kruger.vaccination.dto.LoginResponse;
import com.kruger.vaccination.dto.EmployeeUpdateRequest;
import com.kruger.vaccination.dto.RecordVaccinationRequest;
import com.kruger.vaccination.model.Employee;
import com.kruger.vaccination.model.RecordVaccination;
import com.kruger.vaccination.model.Roles;
import com.kruger.vaccination.model.User;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author DIANA
 */
@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecordVaccinationRepository recordVaccinationRepository;

    @Autowired
    private TypeVaccineRepository typeVaccineRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByIdentification(String identification) throws Exception {
        Employee employee = this.employeeRepository.findByIdentification(identification);
        if (employee == null) {
            throw new Exception("employee with the entered ID not found");
        }
        return this.employeeRepository.findByIdentification(identification);
    }

    public List<Employee> getEmployeesByStateVaccination(boolean status) {
        return this.employeeRepository.findByVaccinationStatus(status);
    }

    public LoginResponse createEmployee(CreateEmployeeRequest createEmployeeRQ) throws Exception {
        if (this.employeeRepository.existsByIdentification(createEmployeeRQ.getIdentification())) {
            throw new Exception("Employee already exists");
        }
        if (!this.validateIdentification(createEmployeeRQ.getIdentification())) {
            throw new Exception("Employee identification invalid");
        }

        Employee employee = new Employee();

        employee.setIdentification(createEmployeeRQ.getIdentification());
        employee.setNames(createEmployeeRQ.getNames());
        employee.setSurnames(createEmployeeRQ.getSurnames());
        employee.setEmail(createEmployeeRQ.getEmail());

        Employee employeeCreated = this.employeeRepository.save(employee);

        String username = employeeCreated.getSurnames().substring(0, 3) + employeeCreated.getId();
        username = username.toLowerCase();

        String password = username + Integer.parseInt(employeeCreated.getIdentification().substring(3, 6));

        User user = new User();

        user.setEmployee(employee);
        user.setUsername(username);
        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        user.setRole(Roles.EMPLOYEE.toString().toUpperCase());

        log.info("password: {} tamaño: {}", user.getPassword(), user.getPassword().length());
        this.userRepository.save(user);

        user.setPassword(password);
        LoginResponse credentialsRS = new LoginResponse();
        credentialsRS.setPassword(user.getPassword());
        credentialsRS.setUsername(user.getUsername());
        return credentialsRS;
    }

    public void updateEmployeeInfoByIdentification(EmployeeUpdateRequest employeeUpdateRQ, String identificacion) throws Exception {

        if (!this.employeeRepository.existsByIdentification(identificacion)) {
            throw new Exception("Employee not found");
        }
        if ((!employeeUpdateRQ.getVaccinationStatus() && !employeeUpdateRQ.getRecordVaccinationRQ().isEmpty())
                || (employeeUpdateRQ.getVaccinationStatus() && employeeUpdateRQ.getRecordVaccinationRQ().isEmpty())) {
            throw new Exception("Verify Data to Update");
        }

        Employee employee = this.employeeRepository.findByIdentification(identificacion);

        employee.setNames(employeeUpdateRQ.getNames());
        employee.setSurnames(employeeUpdateRQ.getSurnames());
        employee.setAddress(employeeUpdateRQ.getAddress());
        employee.setBirthdate(employeeUpdateRQ.getBirthdate());
        employee.setEmail(employeeUpdateRQ.getEmail());
        employee.setPhone(employeeUpdateRQ.getPhone());
        employee.setVaccinationStatus(employeeUpdateRQ.getVaccinationStatus());

        this.employeeRepository.save(employee);

        if (employeeUpdateRQ.getVaccinationStatus()) {
            for (RecordVaccinationRequest recordVaccinationRQ : employeeUpdateRQ.getRecordVaccinationRQ()) {
                RecordVaccination recordVaccination = new RecordVaccination();
                recordVaccination.setDate(recordVaccinationRQ.getDate());
                recordVaccination.setEmployee(employee);
                recordVaccination.setNumberDoses(recordVaccinationRQ.getNumberDoses());
                recordVaccination.setTypeVaccine(this.typeVaccineRepository.findById(
                        recordVaccinationRQ.getTypeVaccineId()).get());

                this.recordVaccinationRepository.save(recordVaccination);
            }
        }
    }

    public void deleteEmployeeByIdentification(String identification) throws Exception {
        if (!this.employeeRepository.existsByIdentification(identification)) {
            throw new Exception("Employee not found");
        }
        Employee employee =this.employeeRepository.findByIdentification(identification);
        User user = this.userRepository.findByEmployee(employee);
        this.userRepository.deleteById(user.getId());
        this.employeeRepository.deleteById(user.getEmployee().getId());
    }

    private boolean validateIdentification(String identification) {
        if (identification.length() != 10) {
            return false;
        }
        int sum = 0;
        int num = 0;
        int validationNum = 0;
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                num = Integer.parseInt(identification.substring(i, i + 1)) * 2;
            } else {
                num = Integer.parseInt(identification.substring(i, i + 1));
            }
            if (num >= 10) {
                num += -9;
            }
            sum += num;
        }
        validationNum = (((sum / 10) + 1) * 10) - sum;
        return validationNum == Integer.parseInt(identification.substring(9));
    }
}
