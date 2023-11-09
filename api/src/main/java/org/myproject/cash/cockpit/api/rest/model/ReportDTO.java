package org.myproject.cash.cockpit.api.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ReportDTO {

    private final long tags;
    private final long transactions;
    private final long consumers;
    private final long files;
    private final double vault;
    private final long rules;

}
