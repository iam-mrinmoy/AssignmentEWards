package com.mp.assignment_ewards.adapter

import android.app.Activity
import android.view.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mp.assignment_ewards.R
import com.mp.assignment_ewards.databinding.EmpItemBinding
import com.mp.assignment_ewards.dto.EmployeeDto

class EmployeesListingAdapter(
    var activity: Activity,
    var arrayList: ArrayList<EmployeeDto.Emp>
) :
    RecyclerView.Adapter<EmployeesListingAdapter.MyViewHolder>() {

    private lateinit var adapterListener:EmployeesListingAdapterListener
    private var prevCardView: MaterialCardView?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = EmpItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(arrayList[holder.adapterPosition]) {
                binding.tvEmpName.text="$employeeName $id"
                binding.tvEmpSal.text=employeeSalary.toString()

                itemView.setOnClickListener {
                    adapterListener.onItemClicked(adapterPosition)
                }
                if(isSelected) {
                    binding.rootCard.strokeColor = ContextCompat.getColor(activity, R.color.black)
                    if(prevCardView!=binding.rootCard) {
                        prevCardView?.strokeColor = ContextCompat.getColor(activity, R.color.white)
                        prevCardView = binding.rootCard
                    }
                }else {
                    binding.rootCard.strokeColor = ContextCompat.getColor(activity, R.color.white)
                }

            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    class MyViewHolder(val binding: EmpItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface EmployeesListingAdapterListener{
        fun onItemClicked(pos:Int)
    }
    fun setOnEmployeesListingAdapterListener(adapterListener:EmployeesListingAdapterListener){
        this.adapterListener=adapterListener
    }
}