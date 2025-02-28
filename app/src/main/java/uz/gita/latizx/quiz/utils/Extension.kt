package uz.gita.latizx.quiz.utils

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import uz.gita.latizx.quiz.R
import uz.gita.latizx.quiz.data.models.CategoryModel

private val container = R.id.main_container

fun Fragment.moveToNext(fragment: Fragment) {
    parentFragmentManager.beginTransaction().replace(container, fragment).commit()
}

fun Fragment.moveToNext(fragment: Fragment, categoryModel: CategoryModel) {
    val bundle = Bundle()
    bundle.putSerializable("data", categoryModel)
    fragment.arguments = bundle
    parentFragmentManager.beginTransaction().replace(container, fragment).commit()
}

fun Fragment.moveToNextIgnoreBackStack(fragment: Fragment, categoryModel: CategoryModel) {
    val bundle = Bundle()
    bundle.putSerializable("data", categoryModel)
    fragment.arguments = bundle
    parentFragmentManager.beginTransaction().replace(container, fragment).commit()
}

fun Fragment.moveToNext(fragment: Fragment, correctCunt: Int, wrongCount: Int, skippedCount: Int,categoryModel: CategoryModel) {
    val bundle = Bundle()
    bundle.putInt("correctCunt", correctCunt)
    bundle.putInt("wrongCount", wrongCount)
    bundle.putInt("skippedCount", skippedCount)
    bundle.putSerializable("data", categoryModel)
    fragment.arguments = bundle
    parentFragmentManager.beginTransaction().replace(container, fragment).commit()
}