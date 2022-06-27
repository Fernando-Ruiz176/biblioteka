package cl.aiep.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests(authorize -> authorize
				.mvcMatchers("/", "/listadoInicio", "/libro/buscar").permitAll()
				.mvcMatchers("/index", "/libro/**", "/autor/**").hasRole("ADMIN")
				.anyRequest().authenticated())
		.formLogin(form -> form 
				
				.defaultSuccessUrl("/libro/index", true)
				.permitAll()
			)
		
		.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/salir", "GET"))
				);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.mvcMatchers("/img/**", "/css/**", "/js/**")
		;
	}

}
