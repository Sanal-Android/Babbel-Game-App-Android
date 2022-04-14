package com.team.smileapplication.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.team.smileapplication.AppExecutors
import com.team.smileapplication.R
import com.team.smileapplication.data.model.User
import com.team.smileapplication.databinding.FragmentCallBinding
import com.team.smileapplication.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_call.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class CallFragment : BaseFragment<FragmentCallBinding>() {


    @Inject
    lateinit var appExecutors: AppExecutors
    private val URL = "https://my-api.plantnet.org/v2/identify/all?api-key=2b10r0mzuIBhpLrU42ymS0L4u"


    lateinit var mViewModel: HomeViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_call;
    }

    var list: ArrayList<User>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.exo_white)
        mViewModel = getViewModel(HomeViewModel::class.java)

        txtBack?.setOnClickListener {
            activity?.finish()
        }

        val backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        Log.e("DATA",arguments?.getString("mark").toString())

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)

        if (arguments?.getString("mark").toString() == "0"){
            txtNoData?.text = "Sorry, You didn't score any mark yet try again"
        }else{
            txtNoData?.text = "Congratulations , You are scored "+arguments?.getString("mark").toString()+ " Points"
        }






    }




}

