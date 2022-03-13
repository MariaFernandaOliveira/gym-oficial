package com.gym.gymoficial.security;

import com.gym.gymoficial.service.UserDetailsServiceImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JWTsecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImplement userDetailsServiceImplement;
    private final PasswordEncoder passwordEncoder;

    public JWTsecurityConfiguration(UserDetailsServiceImplement userDetailsServiceImplement, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceImplement = userDetailsServiceImplement;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImplement).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/mapUser").permitAll()
                .antMatchers(HttpMethod.POST, "/saveUser").permitAll()
                .antMatchers(HttpMethod.POST, "/saveBike").permitAll()
                .antMatchers(HttpMethod.GET, "/getBike").permitAll()
                .antMatchers(HttpMethod.GET, "/getAllReservation").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTauthenticationFilter(authenticationManager()))
                .addFilter(new JWTvalidationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

        @Override
        public void configure(WebSecurity web) throws Exception{
            web.ignoring()
                    .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
        }


    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
