package uz.gita.latizx.quiz.ui.home

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
import uz.gita.latizx.quiz.databinding.ScreenHomeBinding
import uz.gita.latizx.quiz.ui.game.GameScreen
import uz.gita.latizx.quiz.ui.home.adapter.CategoryAdapter
import uz.gita.latizx.quiz.utils.moveToNext

class HomeScreen : Fragment(), HomeContract.View {
    private var _binding: ScreenHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryAdapter
    private lateinit var presenter: HomeContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ScreenHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CategoryAdapter()
        presenter = HomePresenter(this)
        binding.rvHome.adapter = adapter

        adapter.setOnClickPlay {
            presenter.clickPlay(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun submitListCategory(list: List<CategoryModel>) {
        adapter.submitList(list)
    }

    override fun openGameScreen(categoryModel: CategoryModel) {
        moveToNext(GameScreen(), categoryModel)
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

    override fun onResume() {
        super.onResume()
        presenter.submitList()
    }
}