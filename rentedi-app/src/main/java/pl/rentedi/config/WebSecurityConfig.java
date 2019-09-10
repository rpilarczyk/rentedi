package pl.rentedi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.rentedi.security.JWTAuthenticationFilter;
import pl.rentedi.security.JWTAuthorizationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired //'com.sun.proxy.$Proxy146'  UserDetailsServiceImpl
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                //.antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/register/sign",
                        "/register/passwordReset",
                        "/register/emailPasswordReset"
                ).permitAll()
                .antMatchers(HttpMethod.POST,
                        "/register/phone",
                        "/register/email",
                        "/register/paswordReset"
                ).permitAll()
                .antMatchers(HttpMethod.GET,
                        "/swagger-ui.html"
                ).permitAll()
                .antMatchers(HttpMethod.GET,
                        "/**"
                ).permitAll()
                .antMatchers(HttpMethod.POST,
                        "/**"
                ).permitAll()
                .antMatchers(HttpMethod.PUT,
                        "/**"
                ).permitAll()
                .antMatchers(HttpMethod.DELETE,
                        "/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}