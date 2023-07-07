package ar.edu.unlp.pas.person.services;

import ar.edu.unlp.pas.person.services.implementations.PersonService;
import ar.edu.unlp.pas.person.services.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PersonService personService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String env = System.getenv("ENVIRONMENT");
        if (env.equals("dev")) {
            http.authorizeRequests()
                    .antMatchers("/h2-console/**", "/swagger-ui.html/**", "/webjars/springfox-swagger-ui/**",
                            "/swagger-resources/**", "/v2/api-docs/**")
                    .permitAll()
                    .and()
                    .csrf()
                    .ignoringAntMatchers("/h2-console/**", "/swagger-ui.html/**", "/webjars/springfox-swagger-ui/**",
                            "/swagger-resources/**",
                            "/v2/api-docs/**")
                    .and()
                    .headers()
                    .frameOptions()
                    .sameOrigin();
            this.configureAuthRequests(http);
        }

        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .anyRequest().authenticated();
    }

    private void configureAuthRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/person").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/address").hasAnyAuthority("ADMIN","USER","SELLER","DELIVERER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/*/*").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/*").permitAll();
    }
}
