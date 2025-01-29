package com.bookstore.catalog_service.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(
        properties = {
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/test_data.sql")
//@Import(TestcontainersConfiguration.class) If there are multiple containers configures it would unnecessarily spin-up those
class CatalogRepositoryTest {

    @Autowired
    private CatalogRepository catalogRepository;

    @Test
    void shouldReturnProductByCode() {
        ProductEntity productEntity = catalogRepository.findByCode("P107").get();
        assertEquals( "P107", productEntity.getCode());
        assertEquals("The Alchemist", productEntity.getName());
        assertEquals("Paulo Coelho's masterpiece tells the mystical story of Santiago, an Andalusian shepherd boy who yearns to travel in search of a worldly treasure", productEntity.getDescription());
        assertThat(productEntity.getPrice()).isEqualTo(new BigDecimal("12.0"));

    }

    @Test
    void shoudReturnEmptyWhenProductNotFound() {
        assertEquals(Optional.empty(), catalogRepository.findByCode("invalid_code"));

    }

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = catalogRepository.findAll();
        assertThat(products).hasSize(15);
    }
}