package com.github.dericksm.springelk.repository;

import com.github.dericksm.springelk.model.entity.ProductELK;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepositoryElk extends ElasticsearchRepository<ProductELK, UUID> {

    List<ProductELK> findByName(String name);

    List<ProductELK> findByNameOrDescription(String name, String description);
}