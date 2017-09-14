package com.weststein.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Audited
public class UserCredentials {

    public enum Status {
        ACTIVE, BLOCKED, TEMPORARY_BLOCKED, REQUESTED, DELETED
    }

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private String email;
    private String password;
    private String verification;
    private Boolean verified;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<UserRole> roles;
    @Column(columnDefinition = "TEXT")
    @NotAudited
    private String token;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer loginAttempt;
    private LocalDateTime blockedAt;
    private String resetToken;
    @OneToOne(cascade = CascadeType.ALL)
    private UserProfile userProfile;

}

