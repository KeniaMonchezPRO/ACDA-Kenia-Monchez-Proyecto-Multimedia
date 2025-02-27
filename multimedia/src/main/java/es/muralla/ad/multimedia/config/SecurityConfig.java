package es.muralla.ad.multimedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails pepita = User.builder()
				.username("pepita")
				.password("{noop}matrix10")
				.roles("USUARIO")
				.build();
		
		UserDetails manolo = User.builder()
				.username("manolo")
				.password("{noop}abc123.")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(pepita,manolo);
		
	}

}
