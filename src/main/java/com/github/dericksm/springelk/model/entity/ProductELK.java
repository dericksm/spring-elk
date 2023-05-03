package com.github.dericksm.springelk.model.entity;

import org.springframework.data.domain.Persistable;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(indexName = "product-index", createIndex = true)
public class ProductELK implements Persistable<UUID> {

    @Id
    @Field(type = FieldType.Text)
    private UUID id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Double)
    private BigDecimal price;
    
    @Field(type = FieldType.Long)
    private Long quantity;

    @Override
    public boolean isNew() {
        return true;
    }
}
