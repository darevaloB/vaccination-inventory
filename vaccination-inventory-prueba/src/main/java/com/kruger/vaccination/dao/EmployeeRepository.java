/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.dao;

import com.kruger.vaccination.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DIANA
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByIdentification(String identification);

    boolean existsByIdentification(String Identification);

    void deleteByIdentification(String identification);

    List<Employee> findByVaccinationStatus(boolean vaccinationStatus);

}
