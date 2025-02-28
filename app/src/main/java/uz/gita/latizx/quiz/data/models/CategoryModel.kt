package uz.gita.latizx.quiz.data.models

import uz.gita.latizx.quiz.utils.CategoryEnum
import java.io.Serializable

data class CategoryModel(
    val categoryEnum: CategoryEnum,
    val img: Int,
    val questionCount: Int,
    val level: Int
) : Serializable