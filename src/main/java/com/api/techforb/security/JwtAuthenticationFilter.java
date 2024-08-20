package com.api.techforb.security;

import com.api.techforb.Enums.TypeRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//Valida informacion del token, si es true establece la autenticacion de un usuario para dicha solicitud
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUsersDetailsService customUsersDetailsService;
    @Autowired
    private JwtGenerator jwtGenerator;

    private  String getTokenToRequest(HttpServletRequest request){
        String rta = null;
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){ //se valida de primer momento que no sea nulo y que su longitud sea mayot a 0
            rta = bearerToken.substring(7, bearerToken.length());
        }
        return rta;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenToRequest(request);
        if(StringUtils.hasText(token) && jwtGenerator.validateToken(token)){
            String email = jwtGenerator.getEmailJWT(token);
            UserDetails userDetails = customUsersDetailsService.loadUserByUsername(email);
            List<String> userRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            if(userRoles.contains(TypeRole.USER) || userRoles.contains(TypeRole.ADMIN)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                                                                                                                    null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
