package org.com.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Share_22162006 implements Serializable {
    private Long id;
    private Date sharedDate;
    private String emails;
    private String username;
    private Long videoId;
}
