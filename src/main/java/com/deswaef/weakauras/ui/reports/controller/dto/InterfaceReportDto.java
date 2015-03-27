package com.deswaef.weakauras.ui.reports.controller.dto;

public class InterfaceReportDto {
    private String comment;
    private Long interfaceId;
    private String interfaceType;

    public String getComment() {
        return comment;
    }

    public InterfaceReportDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Long getInterfaceId() {
        return interfaceId;
    }

    public InterfaceReportDto setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public InterfaceReportDto setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
        return this;
    }
}
