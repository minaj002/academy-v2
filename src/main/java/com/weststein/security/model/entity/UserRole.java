package com.weststein.security.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
//@Entity
//@Table(name = "USER_ROLE")
public class UserRole {

//    @Id
    private Long id;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "ROLE", insertable=false, updatable=false)
    private Role role;

}
