package com.academy.core.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class AcademyUser {

    public enum Role {

        ROLE_USER, ROLE_ADMIN, ROLE_OWNER;
    }

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
    @Id
    private Long id;
    private String name;
    private String password;
    @OneToOne
    private Academy academy;

    private boolean active;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    public void addRole(Role role) {
        if (roles == null) {
            roles = new ArrayList<Role>();
        }
        roles.add(role);
    }

    public String[] getRolesAsArray() {
        Collection<String> rolesString = roles.stream().map(role -> role.name()).collect(Collectors.toList());
        String[] array = rolesString.toArray(new String[roles.size()]);
        return array;
    }

}
