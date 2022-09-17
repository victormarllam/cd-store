package com.victor.mc_cd_catalog.catalogs.domain.models;

import com.victor.mc_cd_catalog.product.domain.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Cacheable
@NoArgsConstructor
@Table(name = "CatalogProduct")
@NamedQuery(
        name = "CatalogProduct.findLikeTitle",
        query = "SELECT catalogProduct FROM " +
                "CatalogProduct catalogProduct JOIN FETCH catalogProduct.product product " +
                "WHERE " +
                "(:title IS NULL OR lower(product.title) LIKE CONCAT('%',:title,'%'))"
)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CatalogProduct {
    @Id
    private Integer id;

    @MapsId
    @JoinColumn(name = "id")
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column
    private int money; //change for the Martin model
    @Column
    private Integer discount; //validation from 0-100.
}
