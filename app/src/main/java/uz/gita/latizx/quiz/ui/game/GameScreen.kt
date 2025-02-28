package uz.gita.latizx.quiz.ui.game

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import uz.gita.latizx.quiz.R
import uz.gita.latizx.quiz.data.models.CategoryModel
import uz.gita.latizx.quiz.data.models.QuestionModel
import uz.gita.latizx.quiz.databinding.ScreenGameBinding
import uz.gita.latizx.quiz.ui.home.HomeScreen
import uz.gita.latizx.quiz.ui.result.ResultScreen
import uz.gita.latizx.quiz.utils.CategoryEnum
import uz.gita.latizx.quiz.utils.moveToNext

class GameScreen : Fragment(), GameContract.View {
    private var _binding: ScreenGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: GameContract.Presenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = arguments?.getSerializable("data") as? CategoryModel
        presenter = GamePresenter(this, args!!)

        binding.progress.max = 10

        binding.apply {
            tvTestOption1.setOnClickListener { presenter.clickVariant(tvTestOption1.text.toString()) }
            tvTestOption2.setOnClickListener { presenter.clickVariant(tvTestOption2.text.toString()) }
            tvTestOption3.setOnClickListener { presenter.clickVariant(tvTestOption3.text.toString()) }
            tvTestOption4.setOnClickListener { presenter.clickVariant(tvTestOption4.text.toString()) }

            btnNext.setOnClickListener { presenter.clickNext() }
            btnBack.setOnClickListener { presenter.clickBack() }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun loadData(questionModel: QuestionModel, categoryName: String) {
        binding.apply {
            tvCategory.text = "$categoryName quiz"
            tvQuestion.text = questionModel.question
            tvTestOption1.text = questionModel.variants[0]
            tvTestOption2.text = questionModel.variants[1]
            tvTestOption3.text = questionModel.variants[2]
            tvTestOption4.text = questionModel.variants[3]
        }
    }

    override fun unselectedVariants() {
        binding.apply {
            tvTestOption1.setBackgroundResource(R.drawable.bg_variant)
            tvTestOption2.setBackgroundResource(R.drawable.bg_variant)
            tvTestOption3.setBackgroundResource(R.drawable.bg_variant)
            tvTestOption4.setBackgroundResource(R.drawable.bg_variant)
        }
    }

    override fun loadProgress(current: Int) {
        binding.apply {
            progress.progress = current
            tvCurrQuestion.text = current.toString()
        }
    }

    override fun loadQuestionSize(current: String) {
        binding.tvCountQuestion.text = current
    }

    override fun btnNextEnable(isEnable: Boolean) {
        binding.btnNext.isEnabled = isEnable
    }

    override fun selectVariant(variantIndex: Int) {
        when (variantIndex) {
            1 -> binding.tvTestOption1.setBackgroundResource(R.drawable.bg_btn1)
            2 -> binding.tvTestOption2.setBackgroundResource(R.drawable.bg_btn1)
            3 -> binding.tvTestOption3.setBackgroundResource(R.drawable.bg_btn1)
            4 -> binding.tvTestOption4.setBackgroundResource(R.drawable.bg_btn1)
        }
    }

    override fun setBgVariant1(wrong0OrCorrect1: Int) {
        binding.tvTestOption1.setBackgroundResource(if (wrong0OrCorrect1 == 0) R.drawable.bg_wrong else R.drawable.bg_correct)
    }

    override fun setBgVariant2(wrong0OrCorrect1: Int) {
        binding.tvTestOption2.setBackgroundResource(if (wrong0OrCorrect1 == 0) R.drawable.bg_wrong else R.drawable.bg_correct)
    }

    override fun setBgVariant3(wrong0OrCorrect1: Int) {
        binding.tvTestOption3.setBackgroundResource(if (wrong0OrCorrect1 == 0) R.drawable.bg_wrong else R.drawable.bg_correct)
    }

    override fun setBgVariant4(wrong0OrCorrect1: Int) {
        binding.tvTestOption4.setBackgroundResource(if (wrong0OrCorrect1 == 0) R.drawable.bg_wrong else R.drawable.bg_correct)
    }

    override fun updateTimerText(timeText: String) {
        _binding?.let {
            binding.timer.text = timeText
        }
    }

    override fun showTimerFinished() {
        _binding?.let {
            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) vibrator.vibrate(
                    VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE)
                )
                else vibrator.vibrate(300)
            }
            Toast.makeText(requireContext(), "Time's up!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun changeTextNextButton(text: String) {
        binding.btnNext.text = text
    }

    override fun result(correctCount: Int, wrongCount: Int, skippedCount: Int,categoryModel: CategoryModel) {
        moveToNext(ResultScreen(), correctCount, wrongCount, skippedCount,categoryModel)
    }

    override fun showDialogBack() {
        presenter.pauseTimer()

        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_exit, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        val dialog = builder.create()
        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setCancelable(false)
        dialog.show()

        view.findViewById<AppCompatButton>(R.id.btn_yes).setOnClickListener {
            presenter.prevScreen(); dialog.dismiss()
            presenter.resumeTimer()
        }
        view.findViewById<AppCompatButton>(R.id.btn_no).setOnClickListener {
            dialog.dismiss()
            presenter.resumeTimer()
        }
    }

    override fun setViewsEnabled(enabled: Boolean) {
        setViewAndChildrenEnabled(binding.rootLayout, enabled)
    }
    private fun setViewAndChildrenEnabled(view: View, enabled: Boolean) {
        view.isEnabled = enabled
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                setViewAndChildrenEnabled(view.getChildAt(i), enabled)
            }
        }
    }
    override fun openPrevScreen() {
        moveToNext(HomeScreen())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
        _binding = null
    }
}