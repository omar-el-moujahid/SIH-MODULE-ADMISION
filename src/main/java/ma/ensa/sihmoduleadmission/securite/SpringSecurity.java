//package ma.ensa.sihmoduleadmission.securite;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.HttpSecurityDsl;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import javax.sql.DataSource;
//import java.security.Security;
//
//@Configuration
//@EnableWebMvc
//public class SpringSecurity {
////    @Bean
////    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
////        return new JdbcUserDetailsManager(dataSource)
////    }
//
//
//    //@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeHttpRequests(authorizeRequests ->
//                authorizeRequests
//                        .requestMatchers("/login/**").permitAll()
//                        .anyRequest().authenticated()
//        );
//
//        return httpSecurity.build();
//    }
//}
