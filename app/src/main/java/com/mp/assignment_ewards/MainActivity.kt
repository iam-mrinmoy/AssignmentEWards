package com.mp.assignment_ewards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.mp.assignment_ewards.adapter.EmployeesListingAdapter
import com.mp.assignment_ewards.appUtil.isOnline
import com.mp.assignment_ewards.appUtil.showToast
import com.mp.assignment_ewards.databinding.ActivityMainBinding
import com.mp.assignment_ewards.dto.EmployeeDto
import com.mp.assignment_ewards.viewModel.EmployeeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val empList by lazy { ArrayList<EmployeeDto.Emp>() }
    private lateinit var viewModel: EmployeeViewModel
    private var id_list=-1
    private var prevPos=-1

    private val TAG="MainActivityTAG"

    private val mEmployeesListingAdapter by lazy { EmployeesListingAdapter(this,empList) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(EmployeeViewModel::class.java)
        initView()
    }

    private fun initView(){
        binding.rcvEmp.adapter=mEmployeesListingAdapter
        mEmployeesListingAdapter.setOnEmployeesListingAdapterListener(object:
            EmployeesListingAdapter.EmployeesListingAdapterListener{
            override fun onItemClicked(pos: Int) {
                id_list=empList[pos].id

                empList[pos].isSelected=true
                if(prevPos!=-1)
                    empList[prevPos].isSelected=false

                if(id_list!=-1)
                    showToast("Employee id is $id_list")

                prevPos=pos
                mEmployeesListingAdapter.notifyItemChanged(pos)
                Log.d(TAG, "onItemClicked: $id_list")
            }
        })
        getAllEmployees()
    }

    private fun getAllEmployees() {
        if (isOnline()) {
            binding.pBar.visibility = View.VISIBLE
            viewModel.getAllEmployees()?.observe(this, {
                if (it.isSuccessful) {
                    if (it.getResponse()?.asJsonObject?.get("status")?.asString == "success") {
                        loadData(it.getResponse()?.asJsonObject!!)
                    } else
                        showToast(it.getResponse()?.asJsonObject?.get("message")?.asString!!)
                } else {
                    showToast(getString(R.string.bad_api_response_head))
                }
                binding.pBar.visibility = View.GONE
            })
        } else {
            showToast(getString(R.string.no_internet_txt_head))
        }
    }
    private fun loadData(jsonObj: JsonObject){
        val data = Gson().fromJson(jsonObj, EmployeeDto::class.java)
        empList.addAll(data.empList)
        mEmployeesListingAdapter.notifyDataSetChanged()
    }
}