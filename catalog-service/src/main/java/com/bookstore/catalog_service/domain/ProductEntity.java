package com.bookstore.catalog_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_generator")
    private long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Product code is required")
    private String code;

    @Column(nullable = false)
    @NotEmpty(message = "Product name is required")
    private String name;

    private String description;

    private String image_url;

    @Column(nullable = false)
    @DecimalMin(value = "0.1", inclusive = true)
    @NotNull(message = "Product price is required") private BigDecimal price;
}
