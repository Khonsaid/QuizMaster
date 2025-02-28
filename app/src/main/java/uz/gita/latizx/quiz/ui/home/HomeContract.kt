package uz.gita.latizx.quiz.ui.home

import uz.gita.latizx.quiz.data.models.CategoryModel
import uz.gita.latizx.quiz.utils.CategoryEnum

interface HomeContract {
    interface Model {
        fun getAllCategory(): List<CategoryModel>
        fun getLevel(categoryEnum: CategoryEnum): Int
        fun getQuestionSize(categoryEnum: CategoryEnum): Int
    }

    interface View {
        fun submitListCategory(list: List<CategoryModel>)
        fun openGameScreen(categoryModel: CategoryModel)
        fun showSoonDialog()
    }

    interface Presenter {
        fun submitList()
        fun clickPlay(categoryModel: CategoryModel)
    }
}