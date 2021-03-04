package com.mycompany.core.config;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "mycompany.broker")
@Validated
public class MessagingProperties {
  @NotEmpty
  private String producerUrl;

  @NotEmpty
  private String producerOutQueue;

  @NotEmpty
  private String consumerUrl;

  @NotEmpty
  private String consumerInQueue;

  @NotNull
  private Integer concurrentConsumers;

  @NotNull
  private Boolean asyncConsumer;

  @NotEmpty
  private String inbox;

  @NotEmpty
  private String outbox;

  public String getProducerUrl() {
    return producerUrl;
  }

  public void setProducerUrl(String producerUrl) {
    this.producerUrl = producerUrl;
  }

  public String getProducerOutQueue() {
    return producerOutQueue;
  }

  public void setProducerOutQueue(String producerOutQueue) {
    this.producerOutQueue = producerOutQueue;
  }

  public String getConsumerUrl() {
    return consumerUrl;
  }

  public void setConsumerUrl(String consumerUrl) {
    this.consumerUrl = consumerUrl;
  }

  public String getConsumerInQueue() {
    return consumerInQueue;
  }

  public void setConsumerInQueue(String consumerInQueue) {
    this.consumerInQueue = consumerInQueue;
  }

  public Integer getConcurrentConsumers() {
    return concurrentConsumers;
  }

  public void setConcurrentConsumers(Integer concurrentConsumers) {
    this.concurrentConsumers = concurrentConsumers;
  }

  public Boolean getAsyncConsumer() {
    return asyncConsumer;
  }

  public void setAsyncConsumer(Boolean asyncConsumer) {
    this.asyncConsumer = asyncConsumer;
  }

  public String getInbox() {
    return inbox;
  }

  public void setInbox(String inbox) {
    this.inbox = inbox;
  }

  public String getOutbox() {
    return outbox;
  }

  public void setOutbox(String outbox) {
    this.outbox = outbox;
  }
}
