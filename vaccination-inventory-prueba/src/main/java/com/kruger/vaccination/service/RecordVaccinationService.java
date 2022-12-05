package com.kruger.vaccination.service;

import com.kruger.vaccination.dao.RecordVaccinationRepository;
import com.kruger.vaccination.dao.TypeVaccineRepository;
import com.kruger.vaccination.model.RecordVaccination;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DIANA
 */
@Service
@Slf4j
public class RecordVaccinationService {

    @Autowired
    private RecordVaccinationRepository recordVaccinationRepository;

    @Autowired
    private TypeVaccineRepository typeVaccineRepository;

    public List<RecordVaccination> findByTypeVaccineName(String typeVaccineName) {
        return this.recordVaccinationRepository.findByTypeVaccine(this.typeVaccineRepository
                .findByName(typeVaccineName));
    }

    public List<RecordVaccination> findByDateBetween(String startDate, String endDate) {
        return this.recordVaccinationRepository.findByDateBetween(LocalDate.parse(startDate),
                LocalDate.parse(endDate));
    }

}
