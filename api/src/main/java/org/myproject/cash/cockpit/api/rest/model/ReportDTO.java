package org.myproject.cash.cockpit.api.rest.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record ReportDTO(long tags, long transactions, long consumers, long files, double vault, long rules) {

}
