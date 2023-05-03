package com.github.dericksm.springelk.controller;

import com.github.dericksm.springelk.mapper.ProductMapper;
import com.github.dericksm.springelk.model.dto.ProductDTO;
import com.github.dericksm.springelk.model.dto.ProductRequestDTO;
import com.github.dericksm.springelk.model.dto.ProductSearchResponseDTO;
import com.github.dericksm.springelk.model.dto.UpdateProductRequestDTO;
import com.github.dericksm.springelk.model.entity.Product;
import com.github.dericksm.springelk.repository.ProductRepositoryElk;
import com.github.dericksm.springelk.service.ProductServiceImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductMapper productMapper;
    private final ProductServiceImpl productService;
    private final ProductRepositoryElk productRepositoryElk;

    @Autowired
    public ProductsController(ProductMapper productMapper, ProductServiceImpl productService,
                              ProductRepositoryElk productRepositoryElk) {
        this.productMapper = productMapper;
        this.productService = productService;
        this.productRepositoryElk = productRepositoryElk;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        final var product = productService.save(productMapper.productRequestDTOtoProduct(productRequestDTO));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                                              .path("/{id}")
                                              .buildAndExpand(product.getId())
                                              .toUri())
                .body(productMapper.productToProductDTO(product));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductSearchResponseDTO> search(@RequestParam final String searchContent) {
        final List<ProductDTO> products = productRepositoryElk.findByNameOrDescription(searchContent, searchContent)
                .stream()
                .map(productMapper::productElkToProductDTO)
                .toList();
        return ResponseEntity.ok(new ProductSearchResponseDTO(products));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        productService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProductDTO> update(@PathVariable final UUID id,
                                       @RequestBody UpdateProductRequestDTO updateProductRequestDTO) {
        var product = productService.getById(id);
        if (!ObjectUtils.isEmpty(updateProductRequestDTO.description())) {
            product.setDescription(updateProductRequestDTO.description());
        }
        if (!ObjectUtils.isEmpty(updateProductRequestDTO.name())) {
            product.setName(updateProductRequestDTO.name());
        }
        product = productService.save(product);
        return ResponseEntity.ok(productMapper.productToProductDTO(product));
    }

}
