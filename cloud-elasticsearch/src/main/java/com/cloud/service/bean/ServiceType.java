package com.cloud.service.bean;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
public class ServiceType {

    @Field(type = FieldType.Nested)
    private List<String> names;
}
