package com.example.diploma.ui.dashboard.vacancies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.data.COMPANY_NAMES
import com.example.diploma.data.IMAGE_INDEX
import com.example.diploma.data.VACANCY_IMAGES
import com.example.diploma.data.model.Vacancy
import com.example.diploma.databinding.ItemVacancyBinding
import java.lang.Exception


class VacanciesAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<VacanciesAdapter.ViewHolder>() {

    var vacancies = mutableListOf<Vacancy?>()
    var ind = -1

    @SuppressLint("NotifyDataSetChanged")
    fun setList(vacancies: List<Vacancy?>) {
        this.vacancies = vacancies.toMutableList()
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(vacancy: Vacancy)
    }

    inner class ViewHolder(
        private val binding: ItemVacancyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(vacancy: Vacancy) = with(binding) {
            root.setOnClickListener {
                listener.onClick(vacancy)
                IMAGE_INDEX = layoutPosition
            }

            try {
                vacancyTitle.text = vacancy.title
                ind += 1
                companyName.text = COMPANY_NAMES[ind]
                var job = ""
                if (vacancy.jobType == "INTERNSHIP") {
                    job = "Internship"
                } else if (vacancy.jobType == "FULL_TIME") {
                    job = "Full TIme"
                }
                vacancyDetail.text = "${vacancy.location} - $job"
                val salaryText = vacancy.final_salary.toString()
                salary.text = salaryText.substring(0, salaryText.lastIndexOf(".")) + " KZT"
                VACANCY_IMAGES[layoutPosition]?.let { vacancyImage.setImageResource(it) }
            } catch (e: Exception) {
                print(e)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vacancy = vacancies[position]
        vacancy?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemVacancyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }

}