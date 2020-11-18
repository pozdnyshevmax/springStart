package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class CustomBean extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    @Value("${spring.queries.users-query}")
    private String usersQuery;
    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    public void configure(AuthenticationManagerBuilder builder){
        try {
            builder.jdbcAuthentication()
                    .usersByUsernameQuery(usersQuery)
                    .authoritiesByUsernameQuery(rolesQuery)
                    .dataSource(dataSource)
                    .passwordEncoder(passwordEncoder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void configure(HttpSecurity http) {
         try {
             http.authorizeRequests()
                     .antMatchers("/").permitAll()
                     .antMatchers("/login").permitAll()
                     .antMatchers("/registration").permitAll()
                     .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                     .authenticated().and().csrf().disable().formLogin()
                     .loginPage("/login").failureUrl("/login?error=true")
                     .defaultSuccessUrl("/admin/adminHome")
                     .usernameParameter("login")
                     .passwordParameter("pass")
                     .and().logout()
                     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                     .logoutSuccessUrl("/").and().exceptionHandling()
                     .accessDeniedPage("/access-denied");
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}
