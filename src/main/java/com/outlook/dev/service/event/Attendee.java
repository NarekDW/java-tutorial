package com.outlook.dev.service.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 24.06.2017 by K.N.K
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attendee {
    private String type;
    private EmailAddress emailAddress;
}
