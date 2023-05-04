package com.github.dericksm.springelk.service;

import com.github.dericksm.springelk.model.entity.Product;
import com.github.dericksm.springelk.model.entity.ProductELK;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    @Transactional
    Product save(final Product product);

    Product getById(final UUID id);

    void deleteById(final UUID id);

    List<ProductELK> search(final String searchContent);
}
