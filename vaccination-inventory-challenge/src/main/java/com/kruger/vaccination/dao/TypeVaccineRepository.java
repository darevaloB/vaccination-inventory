/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.dao;

import com.kruger.vaccination.model.TypeVaccine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DIANA
 */
public interface TypeVaccineRepository extends JpaRepository<TypeVaccine, Integer> {

    TypeVaccine findByName(String name);

}
