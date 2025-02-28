package uz.gita.latizx.quiz.ui.result

import android.util.Log
import uz.gita.latizx.quiz.data.models.CategoryModel

class ResultPresenter(private val view: ResultContract.View, private val categoryModel: CategoryModel) : ResultContract.Presenter {
    private val model: ResultContract.Model = ResultModel(categoryModel.categoryEnum)

    override fun getArgs(correctCount: Int, wrongCount: Int, skippedCount: Int) {
        view.loadData(correctCount = correctCount, wrongCount = wrongCount, skippedCount = skippedCount)
    }

    override fun clickHome() {
        view.openHomeScreen()
    }

    override fun clickRes() {
        if ((model.getLevel(categoryModel.categoryEnum) + 1) * 10 == model.getQuestionSize(categoryModel.categoryEnum)) {
            model.saveLevel()
        } else if ((model.getLevel(categoryModel.categoryEnum) + 1) * 10 < model.getQuestionSize(categoryModel.categoryEnum)){
            view.openGameScreen(categoryModel)
        }
    }

    override fun clickNext() {
        if ((model.getLevel(categoryModel.categoryEnum) + 1) * 10 < model.getQuestionSize(categoryModel.categoryEnum)) {
            model.saveLevel()
            view.openGameScreen(categoryModel)
        } else view.showSoonDialog()
    }
}