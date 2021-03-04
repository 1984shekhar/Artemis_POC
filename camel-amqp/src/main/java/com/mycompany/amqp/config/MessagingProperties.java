package com.mycompany.amqp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mycompany.broker")
public class MessagingProperties {
  private String producerUrl;

  private String producerOutQueue;

  private String consumerUrl;

  private String consumerInQueue;

  private Integer concurrentConsumers;

  private Boolean asyncConsumer;

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

  
  

}
