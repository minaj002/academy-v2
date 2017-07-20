package com.weststein.repository;

import com.weststein.security.model.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Builder
public class UserRole {

    public enum RoleType {
        BUSINESS, PRIVATE
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
    @Enumerated(EnumType.STRING)
    private Role role;
    private Long entityId;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

}
