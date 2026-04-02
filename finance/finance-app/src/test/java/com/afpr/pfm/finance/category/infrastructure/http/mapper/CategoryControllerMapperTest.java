package com.afpr.pfm.finance.category.infrastructure.http.mapper;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.domain.CategoryMother;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequestDto;
import com.afpr.pfm.finance.client.dto.CategoryResponseDto;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponseDto;
import com.afpr.pfm.finance.client.dto.CategoryEditionRequestDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryControllerMapperTest {
    private final CategoryControllerMapper mapper = Mappers.getMapper(CategoryControllerMapper.class);

    @Test
    void toDomain_CategoryCreateRequestDtoToCategory() {
        var category = CategoryMother.random();
        var request = CategoryCreateRequestDto.builder().name(category.getName()).build();

        Category domain = mapper.toDomain(request);

        assertThat(domain.getName()).isEqualTo(category.getName());
    }

    @Test
    void toDomain_CategoryEditionRequestDtoToCategory() {
        var id = UUID.randomUUID();
        var name = CategoryMother.randomName();
        var request = CategoryEditionRequestDto.builder().id(id).name(name).build();

        Category domain = mapper.toDomain(request);

        assertThat(domain.getId()).isEqualTo(id);
        assertThat(domain.getName()).isEqualTo(name);
    }

    @Test
    void toResponse_CategoryToCategoryResponseDto() {
        var category = CategoryMother.random();
        
        CategoryResponseDto response = mapper.toResponse(category);
        
        assertThat(response.getId()).isEqualTo(category.getId());
        assertThat(response.getName()).isEqualTo(category.getName());
    }

    @Test
    void toPagedResponse_PageToPagedCategoryResponseDto() {
        var pageable = PageRequest.of(1, 5);
        var categories = List.of(CategoryMother.random(), CategoryMother.random());
        var page = new PageImpl<>(categories, pageable, 12L);

        PagedCategoryResponseDto result = mapper.toPagedResponse(page);

        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getTotalElements()).isEqualTo(12L);
        assertThat(result.getTotalPages()).isEqualTo(3);
        assertThat(result.getPageNumber()).isEqualTo(1);
        assertThat(result.getPageSize()).isEqualTo(5);
    }
}
