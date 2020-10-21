package com.app.azure.bus;

import com.microsoft.azure.spring.autoconfigure.jms.ConnectionStringResolver;
import com.microsoft.azure.spring.autoconfigure.jms.ServiceBusKey;
import javax.jms.ConnectionFactory;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

/**
 * @author Anish Panthi
 */
@Configuration
public class JmsConfig {

  @Value("${spring.jms.servicebus.connection-string}")
  private String connectionString;

  @Value("${spring.jms.servicebus.idle-timeout}")
  private int idleTimeout;

  @Value("${spring.jms.servicebus.topic-client-id}")
  private String clientId;

  private static final String AMQP_URI_FORMAT = "amqps://%s?amqp.idleTimeout=%d";

  @Bean
  public ConnectionFactory connectionFactory() {
    ServiceBusKey serviceBusKey = ConnectionStringResolver.getServiceBusKey(connectionString);
    String host = serviceBusKey.getHost();
    String sasKeyName = serviceBusKey.getSharedAccessKeyName();
    String sasKey = serviceBusKey.getSharedAccessKey();

    String remoteUri = String.format(AMQP_URI_FORMAT, host, idleTimeout);
    JmsConnectionFactory jmsConnectionFactory = new JmsConnectionFactory();
    jmsConnectionFactory.setRemoteURI(remoteUri);
    jmsConnectionFactory.setClientID(clientId);
    jmsConnectionFactory.setUsername(sasKeyName);
    jmsConnectionFactory.setPassword(sasKey);
    return new CachingConnectionFactory(jmsConnectionFactory);
  }

  @Bean
  public JmsListenerContainerFactory<?> jmsTopicFactory(ConnectionFactory connectionFactory) {
    DefaultJmsListenerContainerFactory topicFactory = new DefaultJmsListenerContainerFactory();
    topicFactory.setConnectionFactory(connectionFactory);
    topicFactory.setSubscriptionDurable(Boolean.TRUE);
    return topicFactory;
  }

}
