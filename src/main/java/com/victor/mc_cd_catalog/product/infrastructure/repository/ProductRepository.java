package com.victor.mc_cd_catalog.product.infrastructure.repository;

import com.victor.mc_cd_catalog.product.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
