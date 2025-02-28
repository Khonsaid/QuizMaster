package uz.gita.latizx.quiz.ui.game

import uz.gita.latizx.quiz.data.AppRepository
import uz.gita.latizx.quiz.utils.CategoryEnum

class GameModel(private val categoryEnum: CategoryEnum) : GameContract.Model {
    private val repository = AppRepository.getInstance()
    private val questions = repository.getQuestionByLevel(categoryEnum)

    override fun getCurrentQuestion(index: Int) = questions[index]

    override fun changeSelectedVariant(variant: String, currIndex: Int) {
        questions[currIndex].selectedVariant = variant
    }

    override fun getQuestionSize(): Int = questions.size
    override fun saveLevel() {
        repository.setLevel(categoryEnum)
    }
}