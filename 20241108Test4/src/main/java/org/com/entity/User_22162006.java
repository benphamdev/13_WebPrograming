package org.com.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User_22162006 implements Serializable {
    private String email;
    private String fullName;
    private String phone;
    private String username;
    private String password;
    private boolean isActive;
    private boolean isAdmin;
    private String images;
    private List<Share_22162006> share22162006s;
    private List<Favorite_22162006> favorite22162006s;
}
