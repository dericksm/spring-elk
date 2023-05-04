package com.github.dericksm.springelk.service;

import com.github.dericksm.springelk.exception.ProductNoFoundException;
import com.github.dericksm.springelk.model.entity.Product;
import com.github.dericksm.springelk.model.entity.ProductELK;
import com.github.dericksm.springelk.repository.ProductRepository;
import com.github.dericksm.springelk.repository.ProductRepositoryElk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SpringProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductRepositoryElk productRepositoryElk;

    @Autowired
    public SpringProductService(final ProductRepository productRepository, final ProductRepositoryElk productRepositoryElk) {
        this.productRepository = productRepository;
        this.productRepositoryElk = productRepositoryElk;
    }

    @Override
    @Transactional
    public Product save(final Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public Product getById(final UUID id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNoFoundException(id));
    }
    
    @Override
    public void deleteById(final UUID id) {
        getById(id);
        productRepository.deleteById(id);
    }
    
    @Override
    public List<ProductELK> search(final String searchContent){
        return productRepositoryElk.findByNameOrDescription(searchContent, searchContent);
    }

}
