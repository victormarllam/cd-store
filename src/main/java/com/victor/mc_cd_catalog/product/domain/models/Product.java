package com.victor.mc_cd_catalog.product.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Cacheable
@NoArgsConstructor
@Table(name = "Product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank
    private String uid;

    @Column
    @NotBlank
    private String title;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductType productType;

    public Product(int id) {
        this.id = id;
    }
}
