/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DIANA
 */
@Entity
@Table(name = "record_vaccination")
@Data
@NoArgsConstructor
public class RecordVaccination implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "record_id", nullable = false)
    private Integer id;

    @Column(name = "vaccination_date", nullable = false)
    private LocalDate date;

    @Column(name = "number_doses", nullable = false)
    private Integer numberDoses;

    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee employee;

    @JoinColumn(name = "vaccine_id", referencedColumnName = "vaccine_id", nullable = false)
    @ManyToOne(optional = false)
    private TypeVaccine typeVaccine;

}
