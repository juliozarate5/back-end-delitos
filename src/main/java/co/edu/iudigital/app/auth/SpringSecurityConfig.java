package co.edu.iudigital.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Registrar la clase de UsuarioService
 * @author JULIOCESARMARTINEZ
 *
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)//para usar el @Secured
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService usuarioService;
	
	// registramos el objeto como componente de spring
	// esta es la forma de registrar objetos que retornan métodos
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}
	
	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	// protección del lado de spring
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/swagger-ui.html").permitAll()
			
			.anyRequest().authenticated()
			.and()
			.csrf().disable()//sin proteccion de forms ataques cross por estar en capa react separado del back
			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
			//.and()
			//.httpBasic().realmName("HelmeIUD")
			;	
	}
	
	/*swagger*/
    @Override
    public void configure(WebSecurity web) throws Exception {
    	 web.ignoring()
         //.antMatchers(HttpMethod.GET, "/**")
         .antMatchers("/app/**/*.{js,html}")
         .antMatchers("/i18n/**")
         .antMatchers("/content/**")
         .antMatchers("/h2-console/**")
         .antMatchers("/swagger-ui/index.html")
         .antMatchers("/swagger-ui.html")
         .antMatchers("/v2/api-docs")
         .antMatchers("/test/**");
    }

}
