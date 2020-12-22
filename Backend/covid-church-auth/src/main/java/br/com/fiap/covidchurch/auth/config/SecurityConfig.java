package br.com.fiap.covidchurch.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.fiap.covidchurch.auth.security.JwtAuthenticationEntryPoint;
import br.com.fiap.covidchurch.auth.security.JwtRequestFilter;
import br.com.fiap.covidchurch.auth.security.JwtUserDetails;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUserDetails jwtUserDetailService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntrypoint;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtUserDetails jwtUserDetailService,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntrypoint,
                          JwtRequestFilter jwtRequestFilter,
                          PasswordEncoder passwordEncoder){
        this.jwtUserDetailService = jwtUserDetailService;
        this.jwtAuthenticationEntrypoint = jwtAuthenticationEntrypoint;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/usuarios/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                .anyRequest().authenticated()
                    .and()

                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntrypoint)
                    .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

                .csrf().disable()
                .formLogin().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/v3/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**"
                );
    }
}
