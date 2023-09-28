package br.com.adriano.listener;

import br.com.adriano.dto.DemoAvroDTO;
import br.com.adriano.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.FixedDelayStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoListener {

  /*
  * captacao-demo-tst-adriano-engine
  * captacao-demo-tst-adriano-engine-retry
  * captacao-demo-tst-adriano-engine-dlt
  * */

  @RetryableTopic(
          attempts = "10",
          fixedDelayTopicStrategy = FixedDelayStrategy.SINGLE_TOPIC,
          backoff = @Backoff(10000),
          exclude = {
                  NullPointerException.class
          }
  )
  @KafkaListener(
          groupId = "consumer-squad-ms-matricula-engine",
          topics = "captacao-demo-tst-adriano"
  )
  public void demoDTOListener(
          @Payload DemoDTO demoDTO,
          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
          @Header(KafkaHeaders.OFFSET) String offset,
          @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partition) {

    log.info("demoDTO demoDTO [{}]", demoDTO);
    log.info("demoDTO topic [{}]", topic);
    log.info("demoDTO offset [{}]", offset);
    log.info("demoDTO partition [{}]", partition);
  }

  @KafkaListener(
          groupId = "consumer-squad-ms-matricula-engine",
          topics = "captacao-demo-tst-adriano-avro",
          properties = "value.deserializer:io.confluent.kafka.serializers.KafkaAvroDeserializer"
  )
  public void demoAvroDTOListener(
          @Payload DemoAvroDTO demoAvroDTO,
          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
          @Header(KafkaHeaders.OFFSET) String offset,
          @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partition) {

    log.info("demoAvroDTO demoAvroDTO [{}]", demoAvroDTO);
    log.info("demoAvroDTO topic [{}]", topic);
    log.info("demoAvroDTO offset [{}]", offset);
    log.info("demoAvroDTO partition [{}]", partition);
  }
}
