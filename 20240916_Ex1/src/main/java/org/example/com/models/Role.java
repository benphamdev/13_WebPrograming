package org.example.com.models;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private int roleId;
    private String roleName;
}