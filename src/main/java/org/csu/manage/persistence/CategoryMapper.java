package org.csu.manage.persistence;

import org.csu.manage.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}
