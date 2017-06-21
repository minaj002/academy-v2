package com.weststein.security.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
@Entity
public class UserRole {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

}
