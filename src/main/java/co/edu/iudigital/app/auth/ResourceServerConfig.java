package co.edu.iudigital.app.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Config Servidor de recursos
 * accesos de clientes a los recursos de nuestra app
 * si token es válido
 * @author JULIOCESARMARTINEZ
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	// protección del lado de oath2
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//se parte desde rutas más específicas a mas generales
			.antMatchers(HttpMethod.GET, "/delitos", "/uploads/img/**").permitAll()
			.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
			.antMatchers(HttpMethod.GET, "/usuarios/upload").hasAnyRole("USER", "ADMIN")
			//.antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN") or hasAnyRole
			.anyRequest()
			.authenticated()
			.and().httpBasic().disable();
		// las rutas no especificadas, serán para usuarios autenticados
	}
	/*
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-type", "Authorization"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>();
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}*/
}
