package com.github.dericksm.springelk.model.dto;

import java.io.Serializable;

public record UpdateProductRequestDTO(String name, String description) implements Serializable {

}
