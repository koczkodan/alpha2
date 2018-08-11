package com.siseth.spring.alpha2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authentication) throws Exception{
        authentication.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from uzytkownik where email=?")
                .authoritiesByUsernameQuery("select email, role from uzytkownik where email=?").passwordEncoder(passwordEncoder());
      /*  authentication.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from admin where email=?")
                .authoritiesByUsernameQuery("select email, role from admin where email=?").passwordEncoder(passwordEncoder());
                */
    }
    @Override
    public void configure(HttpSecurity security) throws Exception{
        security.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole( "USER")
                .antMatchers("/sign-up").hasRole("ADMIN")
                .antMatchers("/login").permitAll().and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/successlogged", true).permitAll();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}