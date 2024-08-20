package com.api.techforb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //le indica a spring que esta clase es seguridad
@EnableWebSecurity //activa la seguridad web de la app
public class SecurityConfig {

    private JwtAuthenticationEntryPoint jwtAutEntryPoint;

    @Autowired
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint){
        this.jwtAutEntryPoint = jwtAuthenticationEntryPoint;
    }

    //bean que se encarga de verificar la informacion del usuario que se logee en nuestra web.
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    //encriptamos las contraseñas
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Este bean incorpora el filtro de seguridad jwt que creamos en la clase anterior
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    //creo un bean el cual establece una cadena de filtros de seguridad en nuestra aplicacion.
    //Y es donde determino los permisos segun los roles
    /**@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .exceptionHandling() //permito el manejo de exceptions
                .authenticationEntryPoint(jwtAutEntryPoint) //establece un punto de entrada personalizado para el manejo de auth no autorizadas.
                .and() //filtro de seguridad
                .sessionManagement() //permite la gestion de sessiones
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests() //toda peticion http debe ser autorizada.
                .requestMatchers("/api/auth/**").permitAll()//aca determinamos que tipos de endpoint pueden acceder determinados usuarios
                .anyRequest().authenticated() //indica que cualquier solicitud que no sea la que esta arriba debe estar authenticada
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }*/
    //creo un bean el cual establece una cadena de filtros de seguridad en nuestra aplicacion.
    //Y es donde determino los permisos segun los roles
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.GET,"/api/lectura").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/usuario").permitAll()
                        .anyRequest().permitAll())
                //.addFilter(new JwtAuthenticationFilter())
                //.addFilter(new JwtAuthenticationFilter())
                .csrf(config ->config.disable())
                //.cors(cors-> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
