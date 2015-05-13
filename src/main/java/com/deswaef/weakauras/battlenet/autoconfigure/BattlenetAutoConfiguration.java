package com.deswaef.weakauras.battlenet.autoconfigure;

import com.deswaef.weakauras.battlenet.api.Battlenet;
import com.deswaef.weakauras.battlenet.api.BattlenetTemplate;
import com.deswaef.weakauras.battlenet.connect.BattlenetConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.*;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.GenericConnectionStatusView;
import org.springframework.web.servlet.View;

import javax.sql.DataSource;
import java.math.BigInteger;

@Configuration
@ConditionalOnClass({SocialConfigurerAdapter.class, BattlenetConnectionFactory.class})
@ConditionalOnProperty(prefix = "battlenet.api.client", name = "appId")
@AutoConfigureBefore(SocialWebAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class BattlenetAutoConfiguration {

    @Configuration
    @EnableSocial
    @EnableConfigurationProperties(BattlenetProperties.class)
    @ConditionalOnWebApplication
    protected static class BattlenetConfigurerAdapter extends SocialConfigurerAdapter {

        @Autowired
        private BattlenetProperties properties;
        @Autowired
        private DataSource dataSource;

        @Bean
        @ConditionalOnMissingBean(Battlenet.class)
        @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
        public Battlenet battlenet(ConnectionRepository repository) {
            Connection<Battlenet> connection = repository
                    .findPrimaryConnection(Battlenet.class);
            return connection != null ? connection.getApi() : new BattlenetTemplate();
        }

        @Bean(name = { "connect/battlenet/callback", "connect/battlenet" })
        @ConditionalOnProperty(prefix = "spring.social", name = "auto-connection-views")
        public View facebookConnectView() {
            return new GenericConnectionStatusView("battlenet", "Battlenet");
        }

        protected ConnectionFactory<?> createConnectionFactory() {
            return new BattlenetConnectionFactory(this.properties.getAppId(),
                    this.properties.getAppSecret());
        }


        @Override
        public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                           Environment environment) {
            configurer.addConnectionFactory(createConnectionFactory());
        }

        @Override
        public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
            return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        }

        public String toHex(String arg) {
            return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
        }
    }
}
