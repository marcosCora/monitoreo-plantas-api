package com.api.techforb.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    //Generador de token atraves de la authentication
    public String generateToken(Authentication authentication){
        String emailUser = authentication.getName();
        Date currentTime = new Date();
        Date expirationToken = new Date(currentTime.getTime() + ConstSecurity.JWT_EXPIRATION_TOKEN);
        String token = Jwts.builder()
                .setSubject(emailUser) //le establacemos el usuario que queremos al token
                .setIssuedAt(new Date()) //seteamos el inicio del token
                .setExpiration(expirationToken) //le asignamos el tiempo de expiracion
                .signWith(SignatureAlgorithm.HS512, ConstSecurity.JWT_SIGNATURE) //le decimos el metodo de encriptamiento y le pasamos la firma nuestra creada
                .compact();
        return token;
    }

    //metodo para obtener el email del usuario a traves del token
    public String getEmailJWT(String token){
        Claims claims = Jwts.parser()//comienzo del parseo
                .setSigningKey(ConstSecurity.JWT_SIGNATURE)//validamos la firma nuestra con la que trae el token
                .parseClaimsJws(token)//para que verificar que no haya sufrido cambios
                .getBody(); //obtenemos el body del token
        return claims.getSubject(); //en el subject almacena el email del user
    }

    //metodo para validar token
    public boolean validateToken(String token){
        boolean rta = false;
        try{
            Jwts.parser().setSigningKey(ConstSecurity.JWT_SIGNATURE).parseClaimsJws(token);
            rta = true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("expired token or incorrect token");
        }
        return rta;
    }

}
