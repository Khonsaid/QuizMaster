package uz.gita.latizx.quiz.ui.game

import uz.gita.latizx.quiz.data.models.CategoryModel
import uz.gita.latizx.quiz.data.models.QuestionModel
import uz.gita.latizx.quiz.utils.CategoryEnum

interface GameContract {
    interface Model {
        fun getCurrentQuestion(index: Int): QuestionModel
        fun changeSelectedVariant(variant: String, currIndex: Int)
        fun getQuestionSize(): Int
        fun saveLevel()
    }

    interface View {
        fun loadData(questionModel: QuestionModel, categoryName: String)
        fun unselectedVariants()
        fun loadProgress(current: Int)
        fun loadQuestionSize(current: String)
        fun openPrevScreen()
        fun showDialogBack()
        fun btnNextEnable(isEnable: Boolean)
        fun selectVariant(variantIndex: Int)

        fun updateTimerText(timeText: String)
        fun showTimerFinished()
        fun changeTextNextButton(text: String)

        fun result(correctCount: Int, wrongCount: Int, skippedCount: Int,categoryModel: CategoryModel)
        fun setBgVariant1(wrong0OrCorrect1: Int)
        fun setBgVariant2(wrong0OrCorrect1: Int)
        fun setBgVariant3(wrong0OrCorrect1: Int)
        fun setBgVariant4(wrong0OrCorrect1: Int)
        fun setViewsEnabled(enabled: Boolean)
    }

    interface Presenter {
        fun clickBack()
        fun clickNext()
        fun prevScreen()
        fun clickVariant(variant: String)

        //        fun startTimer()
        fun stopTimer()
        fun onDestroy()
        fun pauseTimer()
        fun resumeTimer()
    }
}