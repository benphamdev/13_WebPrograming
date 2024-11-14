package org.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@NamedQueries(
        {
                @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
        }
)
public class User extends BaseEntity<Long> implements Serializable {
    private String username;
    private String password;

    @Column(name = "full_name")
    private String fullName;

    private String status;
    private String image;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}