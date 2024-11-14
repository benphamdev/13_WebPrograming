package org.com.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private Long id;
    private String name;       // Tên của vai trò (ví dụ: "Admin", "User")
    private String code;       // Mã định danh vai trò (ví dụ: "ADMIN", "USER")
    private List<User> users;  // Quan hệ nhiều-nhiều với User
}
