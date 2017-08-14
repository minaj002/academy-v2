package com.weststein.repository;

import com.weststein.repository.business.BusinessInformation;
import com.weststein.security.model.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class UserToBusinessRoleRequest {

    public enum Status {
        REQUESTED, GRANTED, DECLINED
    }

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @OneToOne
    private UserCredentials user;
    @OneToOne
    private BusinessInformation business;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    private UserCredentials requester;
    private LocalDateTime created;
    private Role requestedRole;

}
