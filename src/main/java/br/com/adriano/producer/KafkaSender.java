package br.com.adriano.producer;

import br.com.adriano.dto.DemoAvroDTO;
import br.com.adriano.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaSender {

  private final KafkaTemplate<String, DemoDTO> kafkaTemplateDemoDTO;
  private final KafkaTemplate<String, DemoAvroDTO> kafkaTemplateDemoAvroDTO;

  private final String kafkaTopicDemoDTO = "captacao-demo-tst-adriano";
  private final String kafkaTopicDemoAvroDTO = "captacao-demo-tst-adriano-avro";

  public KafkaSender(KafkaTemplate<String, DemoDTO> kafkaTemplateDemoDTO,
                     KafkaTemplate<String, DemoAvroDTO> kafkaTemplateDemoAvroDTO) {

    this.kafkaTemplateDemoDTO = kafkaTemplateDemoDTO;
    this.kafkaTemplateDemoAvroDTO = kafkaTemplateDemoAvroDTO;
  }

  public void sendDemoDTO(DemoDTO message) {

    log.info("sendDemoDTO [{}]", message);

    ListenableFuture<SendResult<String, DemoDTO>> future = kafkaTemplateDemoDTO.send(kafkaTopicDemoDTO, message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, DemoDTO>>() {

      @Override
      public void onFailure(Throwable ex) {

        log.error("sendDemoDTO [TOPICO][ERROR] - [{}][{}]", kafkaTopicDemoDTO, ex.getMessage());
      }

      @Override
      public void onSuccess(SendResult<String, DemoDTO> result) {

        log.info("sendDemoDTO SUCCESS [TOPICO][MSG] - [{}][{}]", kafkaTopicDemoDTO, message);
      }
    });
  }

  public void sendDemoDemoAvroDTO(DemoAvroDTO message) {

    log.info("sendDemoDemoAvroDTO [{}]", message);

    ListenableFuture<SendResult<String, DemoAvroDTO>> future = kafkaTemplateDemoAvroDTO.send(kafkaTopicDemoAvroDTO, message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, DemoAvroDTO>>() {

      @Override
      public void onFailure(Throwable ex) {

        log.error("sendDemoDemoAvroDTO [TOPICO][ERROR] - [{}][{}]", kafkaTopicDemoAvroDTO, ex.getMessage());
      }

      @Override
      public void onSuccess(SendResult<String, DemoAvroDTO> result) {

        log.info("sendDemoDemoAvroDTO SUCCESS [TOPICO][MSG] - [{}][{}]", kafkaTopicDemoAvroDTO, message);
      }
    });
  }
}
