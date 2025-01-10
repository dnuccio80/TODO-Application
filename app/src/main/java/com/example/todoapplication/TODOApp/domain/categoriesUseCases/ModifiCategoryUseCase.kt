package com.example.todoapplication.TODOApp.domain.categoriesUseCases

import com.example.todoapplication.TODOApp.data.categories.CategoryRepository
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import javax.inject.Inject

class ModifyCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(category:CategoryModel) = categoryRepository.modifyCategory(category)
}