package co.edu.iudigital.app.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
			.antMatchers(HttpMethod.GET, "/delitos").permitAll()
			.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
			.antMatchers(HttpMethod.GET, "/casos", "/casos/caso/**").permitAll()
			.antMatchers(HttpMethod.POST, "/usuarios/upload").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/uploads/img/**").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.POST, "/delitos").hasAnyRole("ADMIN") //otra form es con @secured en el controller
			.antMatchers(HttpMethod.DELETE, "/delitos/delito/**").hasRole("ADMIN")
			//.antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN") or hasAnyRole
			.anyRequest()
			.authenticated()// las rutas no especificadas, serán para usuarios autenticados
			.and().cors().configurationSource(corsConfigurationSource())
			.and().httpBasic().disable();
		
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000"));
		config.setAllowedMethods(Arrays.asList("POST", "GET", "OPTIONS", "PUT", "DELETE", "PATCH"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		//registramos la configuración
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);// para todas las rutas del back
		return source;
	}
	
	// configuramos el filtro en la prioridad mas alta de los filtros de spring
	// se aplique al servidor de auth para generar el token y validarlo para acceder al resto de servicios 
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);//entre más bajo el orden, mayor la precedencia 
		// como es el filtro más alto es sufiente para aplicar a todos los controllers
		return bean;
	}
}
