package com.api.techforb.dtos;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class DtoRegistrer {


    private Long idUser;
    private String name;
    private String lastName;
    private String email;
    private String password;

    //valida que todos los campos contengan datos
    public boolean objValidator(){
        return name != null && !name.isEmpty() &&
                lastName != null && !lastName.isEmpty() && email.contains("@") &&
                email != null && !email.isEmpty() &&
                password != null && !password.isEmpty();
    }



}
