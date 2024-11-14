package org.com.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Long id;
    private String email;
    private String fullname;
    private String phone;
    private String password;
    private Date signupDate;
    private Date lastLogin;
    private boolean isAdmin;
    private List<Role> roles;  // Quan hệ nhiều-nhiều với Role
}
