package com.github.dericksm.springelk.model.dto;

import java.io.Serializable;
import java.util.List;

public record ProductSearchResponseDTO(List<ProductDTO> products) implements Serializable {

}
