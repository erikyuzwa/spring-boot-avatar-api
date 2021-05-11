package com.wazooinc.avatarapi.models;

import lombok.Getter;

public enum ClassType {
    PALADIN("PALADIN"),
    THIEF("THIEF"),
    WIZARD("WIZARD"),
    MAGE("MAGE"),
    DRUID("DRUID"),
    RANGER("RANGER");

    @Getter
    private String value;

    private ClassType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
    
}
