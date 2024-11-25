package org.com.test3.dto;

import lombok.Data;

@Data
public class SignUpDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
}
