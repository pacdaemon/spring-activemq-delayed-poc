package poc.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class SpringJMSConfiguration {


    @Value("${activemq.broker.url}")
    private String brokerUrl;

    @Value("${activemq.broker.username}")
    private String userName;

    @Value("${activemq.broker.password}")
    private String password;

    @Value("${activemq.truststore}")
    private String trustStore;

    @Value("${activemq.truststore.password}")
    private String trustStorePassword;

    @Value("${activemq.keystore}")
    private String keyStore;

    @Value("${activemq.keystore.password}")
    private String keyStorePassword;

    @Value("${activemq.pool.max.connections}")
    private int maxConnections;

    @Value("${activemq.concurrent.consumers}")
    private int concurrentConsumers;


    @Bean
    public ActiveMQConnectionFactory jmsConnectionFactory() throws Exception {
        ActiveMQSslConnectionFactory factory = new ActiveMQSslConnectionFactory();
        factory.setBrokerURL(brokerUrl.trim());
        factory.setUserName(userName);
        factory.setPassword(password);
        factory.setTrustStore(trustStore);
        factory.setTrustStorePassword(trustStorePassword.trim());
        factory.setKeyStore(keyStore);
        factory.setKeyStorePassword(keyStorePassword.trim());
        factory.setTrustAllPackages(true);
        return factory;
    }
    @Bean
    public JmsListenerContainerFactory<?> myFactory(DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                    ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
