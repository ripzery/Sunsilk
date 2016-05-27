package com.socket9.sunsilk.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socket9.sunsilk.R
import com.socket9.sunsilk.fragments.LoginFragment
import com.socket9.thetsl.extensions.replaceFragment

class LoginActivity : AppCompatActivity() {

    lateinit private var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initInstance()
        initListener()

    }

    private fun initListener() {

    }


    private fun initInstance() {

        /* Init instance */
        loginFragment = LoginFragment.newInstance(1)

        /* replace fragment  */
        replaceFragment(fragment = loginFragment)



    }
}
