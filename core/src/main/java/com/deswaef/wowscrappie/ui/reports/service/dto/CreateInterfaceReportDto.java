package com.deswaef.wowscrappie.ui.reports.service.dto;

public class CreateInterfaceReportDto {
    private String comment;
    private Long interfaceId;
    private String interfaceType;

    public String getComment() {
        return comment;
    }

    public CreateInterfaceReportDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Long getInterfaceId() {
        return interfaceId;
    }

    public CreateInterfaceReportDto setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public CreateInterfaceReportDto setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
        return this;
    }
}
