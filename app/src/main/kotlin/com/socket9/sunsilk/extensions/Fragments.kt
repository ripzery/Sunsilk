package com.socket9.thetsl.extensions

import android.support.v4.app.Fragment
import android.widget.Toast
import com.socket9.sunsilk.MainActivity
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.startActivity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Euro (ripzery@gmail.com) on 4/10/16 AD.
 */

fun Fragment.toast(msg: String) {
    toaster?.cancel()
    toaster = Toast.makeText(activity, msg, Toast.LENGTH_SHORT)
    toaster?.show()
}

fun Fragment.replaceFragment(fragmentContainer:Int , fragment: Fragment) {
    childFragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment)
            .commit()
}

fun Fragment.loadingTwoSecThen(title: String = "Please wait", message: String = "Loading...",body: () -> Unit){
   val dialog = indeterminateProgressDialog(message, title)
    dialog.dismiss()
    dialog.setCancelable(false)
    dialog.show()
    Observable.just(1)
            .delay(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                dialog.dismiss()
                body()
            }
}

fun Fragment.showDialog(title: String, message: String){
    alert(message, title).show()
}