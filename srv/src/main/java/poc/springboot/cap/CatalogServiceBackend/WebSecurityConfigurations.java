package poc.springboot.cap.CatalogServiceBackend;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity /*(debug = true)*/ //enable this for debug output in case of problems with authentication. Don't use in production!
public class WebSecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                // Configure this service to be stateless, i.e. not to create a session.
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Configure authorization checks based on endpoints.
                .authorizeRequests()
                .antMatchers("/favicon.ico").permitAll() //permit unprotected access to the favorite / browser bookmark icon if there is one. This is needed by Spring and should be opened up.
                .antMatchers("/error**").permitAll()     //permit unprotected access to the /error endpoint. This is useful and recommended.
                .antMatchers("/actuator/**").permitAll() //Don't use this in production. This permits unauthenticated access to all actuator endpoints without authorization checks.
                .antMatchers("/odata/v2/**").hasAuthority("SCOPE.bookshop_user") //Permits access to the OData endpoint only, if the user is authenticated and has the specified scope in its JWT token.
                .anyRequest().authenticated()
                .and()
                // Configure cross site request forgery check handling.
                // Makes sure the service sends an X-CSRF token cookie
                // which we can send as an HTTP header in POST requests.
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                // Configure OAuth2 Resource Server
                // with JWT token support.
                .oauth2ResourceServer()
                .jwt();
        // @formatter:on
    }
}


