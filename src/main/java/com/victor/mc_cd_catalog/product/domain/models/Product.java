package com.victor.mc_cd_catalog.product.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Cacheable
@NoArgsConstructor
@Table(name = "Product")
public class Product  {
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
//    private List<String> genres;
}
