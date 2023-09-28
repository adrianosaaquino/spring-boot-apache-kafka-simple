package br.com.adriano.controller;

import br.com.adriano.dto.DemoAvroDTO;
import br.com.adriano.dto.DemoDTO;
import br.com.adriano.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

  private final DemoService demoService;

  public DemoController(DemoService demoService) {
    this.demoService = demoService;
  }

  @PostMapping(value = "/json")
  public DemoDTO createJSON(@RequestBody HashMap<String, String> params) {

    log.info("DemoDTO [{}]", params);

    DemoDTO response = demoService.processJSON(params.get("name"));

    log.info("DemoDTO response [{}]", response);

    return response;
  }

  @PostMapping(value = "/avro")
  public HashMap<String, String> createAVRO(@RequestBody HashMap<String, String> params) {

    log.info("DemoAvroDTO [{}]", params);

    demoService.processAvro(params.get("name"));

    log.info("DemoAvroDTO response [{}]", params);

    return params;
  }
}
