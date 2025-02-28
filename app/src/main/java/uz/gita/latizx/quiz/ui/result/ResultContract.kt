package uz.gita.latizx.quiz.ui.result

import uz.gita.latizx.quiz.data.models.CategoryModel
import uz.gita.latizx.quiz.utils.CategoryEnum

interface ResultContract {
    interface Model {
        fun saveLevel()
        fun getLevel(categoryEnum: CategoryEnum): Int
        fun getQuestionSize(categoryEnum: CategoryEnum): Int
    }

    interface View {
        fun loadData(correctCount: Int, wrongCount: Int, skippedCount: Int)
        fun openHomeScreen()
        fun openGameScreen(categoryModel: CategoryModel)
        fun showSoonDialog()
    }

    interface Presenter {
        fun getArgs(correctCount: Int, wrongCount: Int, skippedCount: Int)
        fun clickHome()
        fun clickRes()
        fun clickNext()
    }
}