package org.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
@NamedQueries(
        {
                @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
        }
)
public class Role extends BaseEntity<Long> implements Serializable {
    private String name;
    private String code;

    @OneToOne
    private User user;
}
