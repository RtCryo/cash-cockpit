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

    public void sendReport(final UUID userId, final UUID fileInfoId, final int found, final int saved) {
        reportProducer.send(ReportDto.builder().userId(userId).fileInfoId(fileInfoId).found(found).saved(saved).successful(true).build());
    }

    public void sendReport(final UUID userId, final UUID fileInfoId, final String msgError) {
        reportProducer.send(ReportDto.builder().userId(userId).fileInfoId(fileInfoId).successful(false).errorMsg(msgError).build());
    }

}
