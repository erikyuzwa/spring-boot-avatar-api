package com.wazooinc.avatarapi.models;

import lombok.Getter;

public enum RaceType {
    HUMAN("HUMAN"),
    ELF("ELF"),
    DWARF("DWARF"),
    ORC("ORC");

    @Getter
    private String value;

    private RaceType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

}
