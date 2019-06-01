package de.davelee.trams.vehicles.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
/**
 * This class configures Spring Security. Currently there is one admin user configured.
 * @author Dave Lee
 */
public class WicketSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean(name = "authenticationManager")
    /**
     * Configure the authenticationManagerBean for Spring Security.
     * @return a <code>AuthenticationManager</code> object representing the security configuration.
     */
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    /**
     * Configure the supplied HttpSecurity object so that all http requests within wicket must be logged in
     * first but everyone can log out.
     * @param http a <code>HttpSecurity</code> object which will be processed in this method.
     */
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .logout()
                .deleteCookies("remove")
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/admin/login")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.headers().frameOptions().disable();
    }

    @Autowired
    /**
     * Configure the users who are allowed to login. Currently there is only in memory authentication with one
     * admin user.
     * @param auth a <code>AuthenticationManagerBuilder</code> object which users can be added to.
     */
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").authorities("USER", "ADMIN");
    }

}
