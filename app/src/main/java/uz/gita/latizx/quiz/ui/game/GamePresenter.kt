package uz.gita.latizx.quiz.ui.game

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import uz.gita.latizx.quiz.data.models.CategoryModel
import uz.gita.latizx.quiz.data.models.QuestionModel

class GamePresenter(private val view: GameContract.View, private val categoryModel: CategoryModel) : GameContract.Presenter {
    private val model: GameContract.Model = GameModel(categoryModel.categoryEnum)
    private var timer: CountDownTimer? = null
    private var timerRemaining: Long = 36000

    private var currIndex: Int = 0
    private var questionModel: QuestionModel = model.getCurrentQuestion(currIndex)
    private var correctCunt: Int = 0
    private var wrongCount: Int = 0
    private var skippedCount: Int = 0

    init {
        view.loadQuestionSize(model.getQuestionSize().toString())
        loadData()
    }

    private fun loadData() {
        stopTimer()
        view.btnNextEnable(false)
        view.loadData(questionModel, categoryModel.categoryEnum.categoryName)
        view.loadProgress(currIndex + 1)
        view.unselectedVariants()
        checkNextBtn(currIndex)
        timerRemaining = 36000
        createAndStartTimer(timerRemaining)
    }

    override fun clickNext() {
        if (currIndex < model.getQuestionSize() - 1) {
            if (questionModel.selectedVariant == questionModel.answer) {
                correctCunt++
                view.unselectedVariants()
                setBgWrongOrCorrect(selectedVariant(questionModel.selectedVariant), 1)
                disableViewsTemporarily()
            } else {
                wrongCount++
                view.unselectedVariants()
                setBgWrongOrCorrect(selectedVariant(questionModel.selectedVariant), 0)
                setBgWrongOrCorrect(answerVariant(questionModel.answer), 1)
                disableViewsTemporarily()
            }
            currIndex++
            questionModel = model.getCurrentQuestion(currIndex)
        } else if (currIndex == model.getQuestionSize() - 1) {
            if (questionModel.selectedVariant == questionModel.answer) correctCunt++
            else wrongCount++
            view.result(correctCunt, wrongCount, skippedCount, categoryModel)
        }
    }

    private fun selectedVariant(selectedVariant: String): Int = questionModel.variants.indexOf(selectedVariant)
    private fun answerVariant(answerVariant: String): Int = questionModel.variants.indexOf(answerVariant)

    private fun updateSelectedVariant(variant: String) {
        view.unselectedVariants()
        val index = questionModel.variants.indexOf(variant)
        if (index != -1) {
            view.selectVariant(index + 1)
            view.btnNextEnable(true)
        }
    }

    override fun clickVariant(variant: String) {
        model.changeSelectedVariant(variant, currIndex)
        updateSelectedVariant(variant)
    }

    private fun checkNextBtn(currentIndex: Int) {
        if (currentIndex != model.getQuestionSize() - 1) view.changeTextNextButton("Keyingisi");
        else view.changeTextNextButton("Tugatish");
    }

    private fun setBgWrongOrCorrect(variant: Int, bg: Int) {
        when (variant) {
            0 -> view.setBgVariant1(bg)
            1 -> view.setBgVariant2(bg)
            2 -> view.setBgVariant3(bg)
            3 -> view.setBgVariant4(bg)
        }
    }

    private fun disableViewsTemporarily() {
        // Barcha viewlarni o'chirish
        view.setViewsEnabled(false)
        stopTimer()

        Handler(Looper.getMainLooper()).postDelayed({
            view.setViewsEnabled(true)
            loadData()
        }, 1500) // 1500 millisekund = 1.5 sekund
    }

    override fun stopTimer() {
        timer?.cancel() // Taymerni to'xtatish
        timer = null // Taymerni tozalash
    }

    override fun pauseTimer() {
        timer?.cancel() // Taymerni to'xtatish
    }

    override fun resumeTimer() {
        createAndStartTimer(timerRemaining) // Qolgan vaqt bilan yangi taymerni ishga tushirish
    }

    private fun createAndStartTimer(time: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(time, 1000) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                timerRemaining = millisUntilFinished // Update remaining time
                val secondsRemaining = millisUntilFinished / 1000
                val timText = String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60)
                view.updateTimerText(timText)
            }

            override fun onFinish() {
                timerRemaining = 0
                view.updateTimerText("00:00")
                view.showTimerFinished()
                if (questionModel.selectedVariant == "") skippedCount++
                clickNext()
            }
        }.start()
    }

    override fun prevScreen() {
        view.openPrevScreen()
    }

    override fun clickBack() {
        view.showDialogBack()
    }

    override fun onDestroy() {
        stopTimer()
    }
}