package com.socket9.sunsilk.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.MainActivity
import com.socket9.sunsilk.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.startActivity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class LoginFragment : Fragment(), AnkoLogger {

    /** Variable zone **/
    var param1: Int = 0
    private var progressDialog: ProgressDialog? = null


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: Int): LoginFragment {
            var bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, param1)
            val templateFragment: LoginFragment = LoginFragment()
            templateFragment.arguments = bundle
            return templateFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            param1 = arguments.getInt(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_login, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    /** Method zone **/

    private fun initInstance() {
        btnLogin.setOnClickListener {
            progressDialog = indeterminateProgressDialog("Logging In", "Please wait...")
            progressDialog?.setCancelable(false)
            Observable.just(1)
                    .delay(3, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        progressDialog?.dismiss()
                        startActivity<MainActivity>()
                    }
        }
    }
}