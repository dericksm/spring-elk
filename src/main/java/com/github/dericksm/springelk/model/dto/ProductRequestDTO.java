package com.github.dericksm.springelk.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price,
                                Long quantity) implements Serializable {

}
