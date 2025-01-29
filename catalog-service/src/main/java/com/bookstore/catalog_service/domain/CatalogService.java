package com.bookstore.catalog_service.domain;

import com.bookstore.catalog_service.exception.ProductNotFoundException;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatalogService {

    private final CatalogRepository catalogRepository;

    @Value("${catalog.page-size}")
    @Min(value = 1)
    int pageSize;

    CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public ProductsResponse getProducts(int pageNo) {

        pageNo = pageNo <= 0 ? 1 : pageNo - 1;
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());
        Page<ProductDTO> pagedResult = catalogRepository.findAll(page).map(ProductsMapper::toProductDTO);

        return new ProductsResponse(
                pagedResult.getContent(),
                pagedResult.getTotalElements(),
                pagedResult.getTotalPages(),
                pagedResult.getNumber() + 1,
                pagedResult.isFirst(),
                pagedResult.isLast(),
                pagedResult.hasNext(),
                pagedResult.hasPrevious());
    }

    public ProductDTO getProductByCode(String code) {
        return catalogRepository
                .findByCode(code)
                .map(ProductsMapper::toProductDTO)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
