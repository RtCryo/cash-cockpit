package org.myproject.cash.cockpit.handler.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.producer.ReportDto;
import org.myproject.cash.cockpit.handler.producer.ReportProducer;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessengerService {

    private final ReportProducer reportProducer;

    public void sendReport(final UUID userId, final UUID fileInfoId, final String msg, final boolean result) {
        reportProducer.send(ReportDto.builder().userId(userId).fileInfoId(fileInfoId).successful(result).msg(msg).build());
    }

}
