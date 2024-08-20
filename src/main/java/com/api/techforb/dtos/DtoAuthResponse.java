package com.api.techforb.dtos;

import lombok.Data;

@Data
public class DtoAuthResponse {
    private String accesToken;
    private String tokenType;

    public DtoAuthResponse(String accesToken){
        this.accesToken =accesToken;
        this.tokenType = "Bearer ";
    }

}
