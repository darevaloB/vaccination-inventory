/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.dao;

import com.kruger.vaccination.model.Employee;
import com.kruger.vaccination.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DIANA
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmployee(Employee employee);
    
    boolean existsByEmployee(Employee employee);

    Optional<User> findByUsername(String username);

}
