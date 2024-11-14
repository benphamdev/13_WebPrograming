package org.com.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "modified_date")
    @UpdateTimestamp
    private Date modifiedDate;

    @Column(name = "created_by")
    private String createdBy = "system";

    @Column(name = "modified_by")
    private String modifiedBy = "system";
}
