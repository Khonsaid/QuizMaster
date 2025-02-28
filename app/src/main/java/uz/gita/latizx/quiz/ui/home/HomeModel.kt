package uz.gita.latizx.quiz.ui.home

import uz.gita.latizx.quiz.data.AppRepository
import uz.gita.latizx.quiz.data.models.CategoryModel
import uz.gita.latizx.quiz.utils.CategoryEnum

class HomeModel : HomeContract.Model {
    private val repository = AppRepository.getInstance()

    override fun getAllCategory(): List<CategoryModel> = repository.getAllCategory()
    override fun getLevel(categoryEnum: CategoryEnum): Int = repository.getLevel(categoryEnum)
    override fun getQuestionSize(categoryEnum: CategoryEnum): Int = repository.getQuestionSizeByCategory(categoryEnum)
}