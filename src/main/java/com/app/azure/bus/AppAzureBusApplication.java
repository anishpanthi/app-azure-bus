package com.app.azure.bus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author Anish Panthi
 */
@SpringBootApplication
public class AppAzureBusApplication {

  public static void main(String[] args) {
    SpringApplication.run(AppAzureBusApplication.class, args);
  }

  @Configuration
  static class Runner implements CommandLineRunner {

    private final MessageSender messageSender;

    public Runner(MessageSender messageSender) {
      this.messageSender = messageSender;
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
      messageSender.postMessage();
    }
  }
}
