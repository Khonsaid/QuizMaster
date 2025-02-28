package uz.gita.latizx.quiz.ui.result

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import uz.gita.latizx.quiz.R
import uz.gita.latizx.quiz.data.models.CategoryModel
import uz.gita.latizx.quiz.databinding.ScreenResultBinding
import uz.gita.latizx.quiz.ui.game.GameScreen
import uz.gita.latizx.quiz.ui.home.HomeScreen
import uz.gita.latizx.quiz.utils.moveToNext
import uz.gita.latizx.quiz.utils.moveToNextIgnoreBackStack

class ResultScreen : Fragment(), ResultContract.View {
    private var _binding: ScreenResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: ResultContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categoryModel = arguments?.getSerializable("data") as CategoryModel
        presenter = ResultPresenter(this, categoryModel)

        arguments?.apply {
            presenter.getArgs(
                correctCount = getInt("correctCunt"),
                skippedCount = getInt("skippedCount"),
                wrongCount = getInt("wrongCount")
            )
        }
        binding.apply {
            btnNext.setOnClickListener { presenter.clickNext() }
            btnHome.setOnClickListener { presenter.clickHome() }
            btnRes.setOnClickListener { presenter.clickRes() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    override fun loadData(correctCount: Int, wrongCount: Int, skippedCount: Int) {
        val percent = (correctCount * 100) / (correctCount + wrongCount)
        binding.apply {
            tvResultWrong.text = "${wrongCount - skippedCount}"
            tvResultCorrect.text = correctCount.toString()
            tvResultMissedAnswer.text = skippedCount.toString()
            tvProssent.text = "${percent}%"
            progress.progress = percent
            tvTxtResult.text = if (correctCount > 6) "Testni muvaffaqiyatli topshirdingiz" else "Testni qoniqarli topshirdingiz!"
        }
    }

    override fun openHomeScreen() {
        moveToNext(HomeScreen())
    }

    override fun openGameScreen(categoryModel: CategoryModel) {
        moveToNextIgnoreBackStack(GameScreen(), categoryModel)
    }

    override fun showSoonDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_soon, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        val dialog = builder.create()
        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setCancelable(false)
        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
        }, 1500)
    }
}