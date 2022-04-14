package com.team.smileapplication.ui.home


import android.R.attr.level
import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.team.smileapplication.AppExecutors
import com.team.smileapplication.R
import com.team.smileapplication.data.api.Status
import com.team.smileapplication.data.model.Result
import com.team.smileapplication.data.model.User
import com.team.smileapplication.databinding.FragmentHomeBinding
import com.team.smileapplication.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_call.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    @Inject
    lateinit var appExecutors: AppExecutors
    lateinit var mViewModel: HomeViewModel

    var listLan :List<Result>?=null
    var ranCount : Int?=0
    var ranContQ : Int?=0

    var markCount : Int?=0

    private var poolBand : Int ?=10




    override fun getLayoutId(): Int {
        return R.layout.fragment_home;
    }

    var list: ArrayList<User>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.exo_white)
        mViewModel = getViewModel(HomeViewModel::class.java)

        val anim = ObjectAnimator.ofFloat(btnTest, "translationY", 800f).apply {
            duration = 10000
            start()

        }

        poolBand = activity?.intent?.getStringExtra("pool")?.toInt()
        anim.addListener(object :  Animator.AnimatorListener {


            override fun onAnimationStart(p0: Animator?) {

                Log.e("Ran Q",ranContQ.toString())
                Log.e("Ran A",ranCount.toString())
               //start
            }

            override fun onAnimationEnd(p0: Animator?) {
                imgN?.performClick()
            }

            override fun onAnimationCancel(p0: Animator?) {
                //cancel
            }

            override fun onAnimationRepeat(p0: Animator?) {
                //repeat
            }
        })


        imgY?.setOnClickListener {



            if (ranContQ==ranCount) {
                Toast.makeText(activity, "Correct! Well done", Toast.LENGTH_SHORT).show()
                markCount= markCount?.plus(1)
                ranContQ = (0 until poolBand!!).random()
                btnQ.text = listLan?.get(ranContQ!!)?.txt_eng ?: ""
                Log.e("Q-R",ranContQ.toString())
                Log.e("A-R",ranCount.toString())
            }else{
                Toast.makeText(activity, "Sorry! Your are wrong!", Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                bundle.putString("mark",markCount.toString())
                findNavController().navigate(R.id.action_home_to_call,bundle)
            }


         //  anim.reverse()

        }
        imgN?.setOnClickListener {



            if (ranContQ==ranCount) {
                Toast.makeText(activity, "Sorry!, This is correct!", Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                bundle.putString("mark",markCount.toString())
                findNavController().navigate(R.id.action_home_to_call,bundle)


            }else{
                Toast.makeText(activity, "Wrong! Well done", Toast.LENGTH_SHORT).show()
                markCount= markCount?.plus(1)
                anim.apply {
                    duration = 500
                    startDelay = 200
                    reverse()
                }
                anim.apply {
                    duration = 10000

                    start()
                }

                ranCount = (0 until poolBand!!).random()
                // ranContQ = (0 until 10).random()
                // btnQ.text = listLan?.get(ranContQ!!)?.txt_eng ?: ""
                btnTest.text = listLan?.get(ranCount!!)?.txt_spa ?: ""

                Log.e("Ran Q",ranContQ.toString())
                Log.e("Ran A",ranCount.toString())
            }


        }


        val data = HashMap<String, String>()
        mViewModel.getInformation(data)

        mViewModel.getInfoRepo.removeObservers(this)
        mViewModel.getInfoRepo.observe(this, Observer { response ->

            if (response == null || response.status == Status.LOADING) {
                return@Observer
            }


            listLan = response.data

            ranCount = (0 until poolBand!!).random()
           ranContQ = (0 until poolBand!!).random()
            btnQ.text = response.data?.get(ranContQ!!)?.txt_eng ?: ""
            btnTest.text = response.data?.get(ranCount!!)?.txt_spa ?: ""

            Log.e("Ran Q",ranContQ.toString())
            Log.e("Ran A",ranCount.toString())
        })

    }





}
