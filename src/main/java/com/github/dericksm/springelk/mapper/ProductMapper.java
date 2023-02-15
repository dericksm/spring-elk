package com.github.dericksm.springelk.mapper;

import com.github.dericksm.springelk.model.dto.ProductDTO;
import com.github.dericksm.springelk.model.dto.ProductRequestDTO;
import com.github.dericksm.springelk.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product productRequestDTOtoProduct(ProductRequestDTO productRequestDTO);
    ProductDTO productToProductDTO(Product product);
}
