/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DIANA
 */
@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Integer id;

    @Column(name = "identification", nullable = false, length = 10)
    private String identification;

    @Column(name = "names", nullable = false, length = 40)
    private String names;

    @Column(name = "surnames", nullable = false, length = 60)
    private String surnames;

    @Column(name = "email", nullable = false, length = 40)
    private String email;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "vaccination_status")
    private Boolean vaccinationStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @JsonBackReference
    private List<RecordVaccination> recordsVaccination;

}
