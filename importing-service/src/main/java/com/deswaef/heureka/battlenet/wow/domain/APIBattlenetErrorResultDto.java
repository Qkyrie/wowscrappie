package com.deswaef.heureka.battlenet.wow.domain;

import com.deswaef.heureka.battlenet.wow.client.BattlenetConverter;
import com.deswaef.heureka.infrastructure.exception.HeurekaException;

public class APIBattlenetErrorResultDto extends AbstractBattlenetDto {
    private String status;
    private String reason;

    public String getStatus() {
        return status;
    }

    public APIBattlenetErrorResultDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public APIBattlenetErrorResultDto setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public static APIBattlenetErrorResultDto fromJson(String json) {
        if (!isErrorMessage(json)) {
            throw new HeurekaException("Exception was thrown by battlenet servers");
        } else {
            return BattlenetConverter.convert(json, APIBattlenetErrorResultDto.class);
        }
    }
}