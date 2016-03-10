package com.deswaef.wowscrappie.configuration;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"local", "integrationtest", "default"})
public class GreenmailConfiguration {

    private Log logger = LogFactory.getLog(this.getClass());

    @Bean
    public GreenmailServerBean provideGreenMail() {
        return new GreenmailServerBean();
    }

    public class GreenmailServerBean implements InitializingBean, DisposableBean, BeanDefinitionRegistryPostProcessor {

        private GreenMail greenMail;

        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        }

        @Override
        public void destroy() throws Exception {
            if (greenMail != null) {
                try {
                    logger.debug("stopping greenmail");
                    greenMail.stop();
                    logger.debug("geenmail stopped");
                } catch (Exception ex) {
                    logger.error(String.format("unable to stop greenmail: %s", ex.getMessage()));
                }
            }
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            try {
                logger.debug("starting greenmail");
                greenMail = new GreenMail(ServerSetup.SMTP);
                greenMail.start();
                logger.debug("greenmail started");
            } catch (Exception ex) {
                logger.error(String.format("unable to start greenmail: %s", ex.getMessage()));
            }
        }

        public GreenMail getGreenMail() {
            return greenMail;
        }
    }


}
