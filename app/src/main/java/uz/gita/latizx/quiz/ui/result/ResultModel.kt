package uz.gita.latizx.quiz.ui.result

import uz.gita.latizx.quiz.data.AppRepository
import uz.gita.latizx.quiz.utils.CategoryEnum

class ResultModel(private val categoryEnum: CategoryEnum) : ResultContract.Model {
    private val repository = AppRepository.getInstance()
    override fun saveLevel() {
        repository.setLevel(categoryEnum)
    }

    override fun getLevel(categoryEnum: CategoryEnum): Int = repository.getLevel(categoryEnum)

    override fun getQuestionSize(categoryEnum: CategoryEnum): Int = repository.getQuestionSizeByCategory(categoryEnum)
}