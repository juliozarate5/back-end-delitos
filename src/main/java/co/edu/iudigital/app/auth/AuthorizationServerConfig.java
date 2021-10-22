package co.edu.iudigital.app.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
/**
 * proceso de autenticación por OAuth2: login, etc.
 * @author JULIOCESARMARTINEZ
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Value("${security.jwt.client-service}")
	private String client;
	
	@Value("${security.jwt.password-service}")
	private String secret;
	
	@Value("${security.jwt.scope-read}")
	private String read;
	
	@Value("${security.jwt.scope-write}")
	private String write;
	
	@Value("${security.jwt.grant-password}")
	private String grantPassword;
	
	@Value("${security.jwt.grant-refresh}")
	private String grantRefresh;
	
	@Value("${security.jwt.token-validity-seconds}")
	private Integer accessTime;
	
	@Value("${security.jwt.refresh-validity-seconds}")
	private Integer refreshTime;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenMoreInfo tokenMoreInfo;

	// se implementan los 3 métodos de config.
	
	// ruta de login debe ser publica (servicio de autenticación)
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(passwordEncoder).tokenKeyAccess("permitAll()")//usuarios anónimos o no
				.checkTokenAccess("isAuthenticated()");//permiso a endpoint que valida token
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
		.inMemory().withClient(client)
		.secret(passwordEncoder.encode(secret))
		.scopes(read, write)
		.authorizedGrantTypes(grantPassword, grantRefresh)
		.accessTokenValiditySeconds(accessTime)
		.refreshTokenValiditySeconds(refreshTime);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//registramos la info adiciona con la creación del 
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();//unimos la info del token por default y la nueva
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenMoreInfo, accessTokenConverter()));//agregamos ambas
		endpoints.authenticationManager(authenticationManager)
				.tokenStore(tokenStore())//opcional
				.accessTokenConverter(accessTokenConverter())
				.tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLIC);
		return new JwtAccessTokenConverter();
	}
}
