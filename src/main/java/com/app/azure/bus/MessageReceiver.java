package com.app.azure.bus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Anish Panthi
 */
@Component
@Slf4j
public class MessageReceiver {

  private final static String SOURCE_TOPIC_NAME = "replace_with_your_topic_name";

  private static final String SUBSCRIPTION_NAME = "replace_with_your_subscription_name";

  @JmsListener(destination = SOURCE_TOPIC_NAME, subscription = SUBSCRIPTION_NAME,
      containerFactory = "jmsTopicFactory")
  public void receiveMessage(String message) {
    log.info("Message Received: {}", message);
  }
}
