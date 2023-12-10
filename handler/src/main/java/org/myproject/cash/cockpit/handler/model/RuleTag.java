package org.myproject.cash.cockpit.handler.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
public class RuleTag {

    private final String name;
    private final String rule;
    private final String area;

}
