package com.github.dericksm.springelk.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(UUID id, String name, String description, BigDecimal price,
                         Long quantity) implements Serializable {

}
