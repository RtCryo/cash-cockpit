package org.myproject.cash.cockpit.api.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthenticationResponse(@JsonProperty("accessToken") String accessToken,
                                     @JsonProperty("refreshToken") String refreshToken,
                                     @JsonProperty("username") String username) {
}
