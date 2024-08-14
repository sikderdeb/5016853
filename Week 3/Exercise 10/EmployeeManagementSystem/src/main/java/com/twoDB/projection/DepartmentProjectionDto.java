package com.twoDB.projection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentProjectionDto {
    private final int id;
    private final String name;

    public DepartmentProjectionDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
