package com.floxie.auth.infrastructure.rabbitmq;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.commons.rabbitmq.RabbitMqUserQueues;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqProducer {

  private final AmqpTemplate amqpTemplate;

  public void delete(UUID userId) {
    String token = userId.toString();

    RabbitMqUserQueues.getAllQueuesUserDeletion()
        .forEach(queue -> amqpTemplate.convertAndSend(queue, token));
  }
}
