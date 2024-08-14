package com.twoDB.projection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmployeeProjectionDto {
    private final int id;
    private final String name;
    private final String email;

    public EmployeeProjectionDto(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
