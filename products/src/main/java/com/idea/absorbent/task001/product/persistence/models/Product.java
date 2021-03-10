package com.idea.absorbent.task001.product.persistence.models;

import com.idea.absorbent.task001.product.web.dto.CreateProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Products_ID_seq")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CreditID")
    private Integer creditId;

    @Column(name = "Value")
    private Integer Value;

    public Product(CreateProductDto dto) {
        this.setCreditId(dto.getCreditId());
        this.setValue(dto.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return creditId.equals(product.creditId);
    }

    @Override
    public int hashCode() {
        return creditId.hashCode();
    }
}
