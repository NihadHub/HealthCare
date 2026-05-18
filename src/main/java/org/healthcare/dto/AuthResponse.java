package org.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @AllArgsConstructor
public class AuthResponse {
    private String token;
   private String role;
    public AuthResponse(String token){this.token=token;}

}
