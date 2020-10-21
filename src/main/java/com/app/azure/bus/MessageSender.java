package com.app.azure.bus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Anish Panthi
 */
@Component
@Slf4j
public class MessageSender {

  private static final String DESTINATION_TOPIC_NAME = "replace_with_your_topic_name";

  private final JmsTemplate jmsTemplate;

  public MessageSender(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void postMessage() {
    try {
      log.info("Sending message to topic: {}", DESTINATION_TOPIC_NAME);
      AzureBusMessage azureBusMessage = new AzureBusMessage();
      azureBusMessage.setMessage("Hello From Anish Panthi");
      log.info("Message to sent: {}", azureBusMessage.toString());
      jmsTemplate.convertAndSend(DESTINATION_TOPIC_NAME, azureBusMessage.toString());
      log.info("Message Sent...");
    } catch (Exception e) {
      log.error("", e);
    }
  }
}
