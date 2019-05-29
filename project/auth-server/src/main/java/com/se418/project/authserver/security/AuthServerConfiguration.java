package com.se418.project.authserver.security;

import com.netflix.discovery.converters.jackson.EurekaXmlJacksonCodec;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
@EnableAuthorizationServer
public class authServerConfiguration extends AuthorizationServerConfigurerAdapter{
    @Override
    public void configure(ClientDetailsServiceConfigurer client ) throws Exception {
        client.inMemory()
                .withClient("summer855")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .authorities("USER")
                .redirectUris("http://example.com");
    }
}
