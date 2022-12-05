package com.kruger.vaccination.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author DIANA
 */

@Data
//@Builder
public class LoginResponse {
    
    private String username;
    private String password;
    
}
