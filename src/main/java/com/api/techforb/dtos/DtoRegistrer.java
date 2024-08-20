package com.api.techforb.dtos;

import lombok.Data;

@Data
public class DtoRegistrer {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
