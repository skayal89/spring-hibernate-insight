package com.learning.springsecurity.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/* Below configuration
 * - Registers a client with client-id ‘my-trusted-client’ and password ‘secret’ and roles & scope 
 *   he is allowed for.
 * - Specifies that any generated access token will be valid for only 300 seconds
 * - Specifies that any generated refresh token will be valid for only 9000 seconds
 * */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
private static String REALM="MY_OAUTH_REALM";
	
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	/* Configure the ClientDetailsService, e.g. declaring individual clients and their properties. 
	 * Note that password grant is not enabled (even if some clients are allowed it) unless an 
	 * AuthenticationManager is supplied to the configure(AuthorizationServerEndpointsConfigurer). 
	 * At least one client, or a fully formed custom ClientDetailsService must be declared or the 
	 * server will not start.
	 * */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
	        .withClient("my-trusted-client") // username to get tokens
            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .secret("secret") // password to get tokens
            .accessTokenValiditySeconds(300)//Access token is only valid for 5 minutes.
            .refreshTokenValiditySeconds(9000);//Refresh token is only valid for 15 minutes.
	}

	/* Configure the non-security features of the Authorization Server endpoints, like token store, 
	 * token customizations, user approvals and grant types. You shouldn't need to do anything by 
	 * default, unless you need password grants, in which case you need to provide an AuthenticationManager
	 * */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager);
	}

	/* Configure the security of the Authorization Server, which means in practical terms 
	 * the /oauth/token endpoint. The /oauth/authorize endpoint also needs to be secure, 
	 * but that is a normal user-facing endpoint and should be secured the same way as the 
	 * rest of your UI, so is not covered here.
	 * */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.realm(REALM+"/client");
	}
}
