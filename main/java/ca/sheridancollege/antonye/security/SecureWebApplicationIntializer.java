package ca.sheridancollege.antonye.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecureWebApplicationIntializer extends AbstractSecurityWebApplicationInitializer {
	public SecureWebApplicationIntializer() {
		super(SecurityConfiguration.class);
		}
		
}
