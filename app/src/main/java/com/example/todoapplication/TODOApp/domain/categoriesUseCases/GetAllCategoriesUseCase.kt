package com.example.todoapplication.TODOApp.domain.categoriesUseCases

import com.example.todoapplication.TODOApp.data.categories.CategoryRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(private val categoryRepository: CategoryRepository)  {

    suspend operator fun invoke() = categoryRepository.getAllCategories()

}