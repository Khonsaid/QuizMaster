package uz.gita.latizx.quiz.data.models

import uz.gita.latizx.quiz.utils.CategoryEnum

data class QuestionModel(
    val question: String,
    val variants: List<String>,
    val answer: String,
    var selectedVariant: String = "",
)