package com.team.smileapplication.ui.login


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.team.smileapplication.AppExecutors
import com.team.smileapplication.R
import com.team.smileapplication.data.model.User
import com.team.smileapplication.databinding.FragmentLoginBinding
import com.team.smileapplication.ui.BaseFragment
import com.team.smileapplication.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


private const val TAG: String = "LoginFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    @Inject
    lateinit var appExecutors: AppExecutors



    override fun getLayoutId(): Int {
        return R.layout.fragment_login;
    }

    var list: ArrayList<User>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.exo_white)




        btnTakePic?.setOnClickListener {
            val b: AlertDialog.Builder = AlertDialog.Builder(activity)
            b.setTitle("You can choose the pool strength from below")
            val types = arrayOf("10", "50", "100", "200")
            b.setItems(types) { dialog, which ->
                dialog.dismiss()
                Log.e("@@@", types[which])
                val home = Intent(requireContext(), HomeActivity::class.java)
                home.putExtra("pool",types[which])
                startActivity(home)
            }

            b.show()
        }

    }

}
