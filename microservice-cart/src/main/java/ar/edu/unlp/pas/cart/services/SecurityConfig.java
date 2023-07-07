package ar.edu.unlp.pas.cart.services;

import ar.edu.unlp.pas.cart.services.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
                .addFilterAfter(new JWTAuthorizationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .anyRequest().authenticated();

    }

    private void configureAuthRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET,
                "/api/shopping-cart").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH,
                "/api/systemConfig").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,
                "/api/checkout").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,
                "/api/checkout").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/ping").permitAll();
    }

}
