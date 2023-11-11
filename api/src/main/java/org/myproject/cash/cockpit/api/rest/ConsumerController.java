package org.myproject.cash.cockpit.api.rest;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.ConsumerDTO;
import org.myproject.cash.cockpit.api.service.consumer.ConsumerRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    private final ConsumerRepositoryService consumerRepositoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConsumerDTO> findAllConsumer() {
        return consumerRepositoryService.findAllConsumer();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ConsumerDTO save(@RequestBody final ConsumerDTO consumerDTO) {
        return consumerRepositoryService.save(consumerDTO);
    }

}

