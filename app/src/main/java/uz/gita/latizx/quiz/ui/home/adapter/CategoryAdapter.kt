package uz.gita.latizx.quiz.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import uz.gita.latizx.quiz.R
import uz.gita.latizx.quiz.data.models.CategoryModel
import uz.gita.latizx.quiz.databinding.ItemCategoryBinding

class CategoryAdapter :
    RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    private var lastOpened = -1
    private var onClickPlay: ((CategoryModel) -> Unit)? = null
    private lateinit var listCategory: List<CategoryModel>

    @SuppressLint("NotifyDataSetChanged")
    inner class CategoryVH(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (lastOpened != adapterPosition) {
                    if (lastOpened != -1) notifyItemChanged(lastOpened, Unit) // Avvalgi itemni yangilash
                    lastOpened = adapterPosition
                } else lastOpened = -1 // O'zini bosganda yopish

                changeVisibility(lastOpened == adapterPosition)

                notifyItemChanged(adapterPosition, Unit)
            }
            binding.btnPlay.setOnClickListener { onClickPlay?.invoke(listCategory[adapterPosition]) }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.apply {
                imgCategory.setImageResource(listCategory[adapterPosition].img)
                tvCategory.text = listCategory[adapterPosition].categoryEnum.categoryName
                tvLevel.text = "Level ${listCategory[adapterPosition].level + 1}"
                tvCountQues.text = listCategory[adapterPosition].questionCount.toString()

                progress.progress = (listCategory[adapterPosition].level * 100) * 10 / listCategory[adapterPosition].questionCount
                tvProgress.text = "${(listCategory[adapterPosition].level * 100) * 10 / listCategory[adapterPosition].questionCount}%"
                tvCurrQuiz.text = "Solved ${listCategory[adapterPosition].level * 10} out of"
                if (adapterPosition == lastOpened) changeVisibility(true)
                else changeVisibility(false)
            }
        }

        private fun changeVisibility(isVisible: Boolean) {
            val upAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.up)
            val downAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.down)
            binding.apply {
                if (isVisible) cardView.startAnimation(downAnim)
                else cardView.startAnimation(upAnim)

                tvCurrQuiz.visibility = if (isVisible) View.VISIBLE else View.GONE
                tvCountQues.visibility = if (isVisible) View.VISIBLE else View.GONE
                txt.visibility = if (isVisible) View.VISIBLE else View.GONE
                btnPlay.visibility = if (isVisible) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH =
        CategoryVH(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listCategory.size
    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind()
    }

    fun submitList(listCategory: List<CategoryModel>) {
        this.listCategory = listCategory
    }

    fun setOnClickPlay(onClickPlay: (CategoryModel) -> Unit) {
        this.onClickPlay = onClickPlay
    }
}