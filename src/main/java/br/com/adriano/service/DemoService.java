package br.com.adriano.service;

import br.com.adriano.dto.DemoAvroDTO;
import br.com.adriano.dto.DemoDTO;
import br.com.adriano.producer.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class DemoService {

  private final KafkaSender kafkaSender;

  public DemoService(KafkaSender kafkaSender) {
    this.kafkaSender = kafkaSender;
  }

  public DemoDTO processJSON(String name) {

    log.info("processJSON [{}]", name);

    DemoDTO demoDTO = new DemoDTO();
    demoDTO.setName(name);
    demoDTO.setDate(new Date());

    kafkaSender.sendDemoDTO(demoDTO);

    return demoDTO;
  }

  public DemoAvroDTO processAvro(String name) {

    log.info("processAvro [{}]", name);

    DemoAvroDTO demoAvroDTO = new DemoAvroDTO();
    demoAvroDTO.setName(name);
    /*demoAvroDTO.setDate(
            new Date().toInstant().atZone(ZoneId.of("UTC")).toLocalDate()
    );*/

    kafkaSender.sendDemoDemoAvroDTO(demoAvroDTO);

    log.info("processAvro finished [{}]", name);

    return demoAvroDTO;
  }
}
