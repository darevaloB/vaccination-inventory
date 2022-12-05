/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.dao;

import com.kruger.vaccination.model.RecordVaccination;
import com.kruger.vaccination.model.TypeVaccine;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DIANA
 */
public interface RecordVaccinationRepository extends JpaRepository<RecordVaccination, Integer> {

    List<RecordVaccination> findByTypeVaccine(TypeVaccine typeVaccine);

    List<RecordVaccination> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
