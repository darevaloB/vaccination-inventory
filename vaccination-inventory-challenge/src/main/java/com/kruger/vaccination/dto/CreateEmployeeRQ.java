/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.vaccination.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 * @author DIANA
 */
@Data
public class CreateEmployeeRQ {

    private String identification;

    @Pattern(regexp = "^([A-Za-zÑñÁáÉéÍíÓóÚú][A-Za-zÑñÁáÉéÍíÓóÚú]+)(\\s+([A-Za-zÑñÁáÉéÍíÓóÚú][A-Za-zÑñÁáÉéÍíÓóÚú]+))*$",
            message = "They must not contain numbers or special characters.")
    private String names;

    @Pattern(regexp = "^([A-Za-zÑñÁáÉéÍíÓóÚú][A-Za-zÑñÁáÉéÍíÓóÚú]+)(\\s+([A-Za-zÑñÁáÉéÍíÓóÚú][A-Za-zÑñÁáÉéÍíÓóÚú]+))*$",
            message = "They must not contain numbers or special characters.")
    private String surnames;

    @Email(message = "must be a correctly formatted e-mail address")
    private String email;

}
