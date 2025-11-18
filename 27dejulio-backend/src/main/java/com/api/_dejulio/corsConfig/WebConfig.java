import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors() // Habilita CORS para Spring Security
            .and()
            .csrf().disable() // Solo si estás usando API REST
            .authorizeHttpRequests()
                .anyRequest().permitAll(); // Ajustar según roles/seguridad

        return http.build();
    }

    // Esto usa la configuración de CORS definida en WebConfig
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
            Arrays.asList(
                "https://27dejulio.com",
                "https://www.27dejulio.com",
                "https://27dejulioapi.com",
                "https://www.27dejulioapi.com",
                "https://a0041148.ferozo.com"
            )
        );
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","Accept","Origin","User-Agent"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

