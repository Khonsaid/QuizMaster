package uz.gita.latizx.quiz.ui.home

import android.util.Log
import uz.gita.latizx.quiz.data.models.CategoryModel

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {
    private val model: HomeContract.Model = HomeModel()

    init {
        submitList()
    }

    override fun submitList() {
        view.submitListCategory(model.getAllCategory())
    }

    override fun clickPlay(categoryModel: CategoryModel) {
        if (model.getLevel(categoryModel.categoryEnum) * 10 < model.getQuestionSize(categoryModel.categoryEnum)) {
            view.openGameScreen(categoryModel)
        } else view.showSoonDialog()
    }
}