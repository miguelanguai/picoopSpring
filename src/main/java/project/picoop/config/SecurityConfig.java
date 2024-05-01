package project.picoop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import project.picoop.auth.JWTAuthFilter;
import project.picoop.user.OurUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private OurUserDetailsService ourUserDetailsService;
    @Autowired
    private JWTAuthFilter jwtAuthFIlter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                // disable conection csrf
                .csrf(AbstractHttpConfigurer::disable)

                // configure cors conection with default values
                .cors(Customizer.withDefaults())

                /*
                 * authorization of requests. it allows all auth and public. If starts with
                 * /admin, it needs admin authority. If starts with /user, it needs user
                 * authority. All the rest of requests need to be authenticated
                 */
                .authorizeHttpRequests(request -> request.requestMatchers("/auth/**", "/public/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN").requestMatchers("/user/**")
                        .hasAnyAuthority("USER").requestMatchers("/adminuser/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated())

                // session management is statless. It allows them to be created and managed by
                // JWT
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // authenticationProvider is specified by calling the method below
                .authenticationProvider(authenticationProvider())

                // this adds a JWTAuthFilter before the username password default filter
                .addFilterBefore(jwtAuthFIlter, UsernamePasswordAuthenticationFilter.class);

        // returns and builds
        return httpSecurity.build();
    }

    /**
     * Takes user details from {@link OurUserDetailsService} and sets its Password
     * Encoder with the {@link SecurityConfig.passwordEncoder()}
     * 
     * @return daoAuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(ourUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * uses a BCrypt algorithm to store passwords and secure them
     * 
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * It processes an authentication request
     * 
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
