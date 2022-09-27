package marcelodias.cadastroEstados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
       return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
      http.authorizeRequests().antMatchers("/uf/**").permitAll().antMatchers("/bairro/**").permitAll()
                                                .antMatchers("/municipio/**").permitAll().antMatchers("/pessoa/**").permitAll().antMatchers("/endereco/**").permitAll()
                                                .anyRequest().authenticated().and().csrf().disable();
        return http.build();
    }
}
