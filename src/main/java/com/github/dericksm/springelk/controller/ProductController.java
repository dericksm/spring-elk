package com.github.dericksm.springelk.controller;

import com.github.dericksm.springelk.mapper.ProductMapper;
import com.github.dericksm.springelk.model.dto.ProductDTO;
import com.github.dericksm.springelk.model.dto.ProductRequestDTO;
import com.github.dericksm.springelk.model.entity.Product;
import com.github.dericksm.springelk.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductServiceImpl productService;

    public ProductController(ProductMapper productMapper, ProductServiceImpl productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        Product product = productService.create(productMapper.productRequestDTOtoProduct(productRequestDTO));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri())
            .body(productMapper.productToProductDTO(product));
    }

    C:\Workspace\Workspace\2. Java\3. ELK\spring-elk\docker\
}
