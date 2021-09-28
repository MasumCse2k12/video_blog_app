package com.rokomari.videoapp.security;


import com.rokomari.videoapp.authentication.service.AuthenticationService;
import com.rokomari.videoapp.authentication.service.CustomAuthenticationDetailSource;
import com.rokomari.videoapp.config.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CustomSuccessHandler customSuccessHandler;


    @Bean
    public CustomAuthenticationDetailSource authenticationDetailSource() {
        System.out.println("Configuring Spring Security : Initializaing user details service...");
        return new CustomAuthenticationDetailSource();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/login/**")
                .antMatchers("/user/**")
                .antMatchers("/blog/**")
                .antMatchers("/signout/**");
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().httpStrictTransportSecurity().and().frameOptions().disable().xssProtection().and().cacheControl();
        http
                .csrf().ignoringAntMatchers("/j_login")
                .and()
                .logout().logoutUrl("/signout").logoutSuccessUrl("/signout").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                //.and().logout().logoutUrl("/signout").logoutSuccessUrl("/signout")
                .and().formLogin().authenticationDetailsSource(authenticationDetailSource())
                .loginPage("/login").loginProcessingUrl("/j_login")
                .successHandler(customSuccessHandler).failureUrl("/login?error=true");
        http.sessionManagement().maximumSessions(10).expiredUrl("/login").and().invalidSessionUrl("/login");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
