package com.twoDB.projection;

import org.springframework.beans.factory.annotation.Value;

public interface DepartmentProjection {
    int getId();
    @Value("#{target.name + ' Department'}")
    String getName();
}
