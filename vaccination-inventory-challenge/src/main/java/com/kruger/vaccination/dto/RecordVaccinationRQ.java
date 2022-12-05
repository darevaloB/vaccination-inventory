/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.dto;

import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author DIANA
 */
@Data
public class RecordVaccinationRQ {
    private LocalDate date;
    private Integer numberDoses;
    private Integer typeVaccineId;
}
