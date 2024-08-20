package com.api.techforb.entity;

import com.api.techforb.Enums.TypeRole;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_roles")
    private Long idRole;
    @Enumerated(EnumType.STRING)
    private TypeRole typeRole;


    public String getTypeRole(){
        return typeRole.toString();
    }

}
