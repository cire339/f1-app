package com.cire.formula1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RaceSessions {

    @JsonProperty("sessions")
    private List<BigInteger> sessions;

    public RaceSessions(List<BigInteger> raceSessions) {
        this.sessions = raceSessions;
    }

    public List<BigInteger> getSessions() {
        return sessions;
    }

    public void setSessions(List<BigInteger> sessions) {
        this.sessions = sessions;
    }
}
