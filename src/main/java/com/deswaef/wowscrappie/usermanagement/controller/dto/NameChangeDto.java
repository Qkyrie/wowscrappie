package com.deswaef.wowscrappie.usermanagement.controller.dto;

public class NameChangeDto {
    private String newName;

    public String getNewName() {
        return newName;
    }

    public NameChangeDto setNewName(String newName) {
        this.newName = newName;
        return this;
    }
}
