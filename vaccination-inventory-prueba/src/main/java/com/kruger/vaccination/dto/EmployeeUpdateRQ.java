/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kruger.vaccination.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

/**
 *
 * @author DIANA
 */
@Data
public class EmployeeUpdateRQ {

    private String names;
    private String surnames;
    private String email;
    private LocalDate birthdate;
    private String address;
    private String phone;
    private Boolean vaccinationStatus;
    private List<RecordVaccinationRQ> recordVaccinationRQ;
}
