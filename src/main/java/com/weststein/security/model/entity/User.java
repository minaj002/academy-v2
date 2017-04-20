package com.weststein.security.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
//@Entity
//@Table(name="APP_USER")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
//    @Id
//    @Column(name="ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    @Column(name="username")
    private String username;
    
//    @Column(name="password")
    private String password;
    
//    @OneToMany
//    @JoinColumn(name="APP_USER_ID", referencedColumnName="ID")
    private List<UserRole> roles;
    

}
