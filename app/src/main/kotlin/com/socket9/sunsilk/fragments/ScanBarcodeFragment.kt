package com.socket9.sunsilk.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.sunsilk.R
import com.socket9.sunsilk.activities.ScannerActivity
import com.socket9.sunsilk.adapter.HowToScanAdapter
import com.socket9.sunsilk.managers.SharePref
import com.socket9.thetsl.extensions.toast
import kotlinx.android.synthetic.main.fragment_scan_barcode.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class ScanBarcodeFragment : Fragment() {

    /** Variable zone **/
    lateinit var param1: String


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): ScanBarcodeFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val templateFragment: ScanBarcodeFragment = ScanBarcodeFragment()
            templateFragment.arguments = bundle
            return templateFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            param1 = arguments.getString(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_scan_barcode, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            val point = (Math.random() * 10 + 1).toInt()
            val dialog = alert("ยินดีด้วย ") {
                positiveButton("OK") {
                    toast("เย่ เย่")
                    SharePref.savePoint(point + SharePref.getPoint())
                }
            }
            dialog.cancellable(false)
            dialog.message("คุณได้รับ $point คะแนน")
            dialog.show()
        }
    }
    /** Method zone **/

    private fun initInstance() {
        viewPagerScan.adapter = HowToScanAdapter(childFragmentManager, context)

        btnScan.setOnClickListener{
            startActivityForResult(Intent(context, ScannerActivity::class.java), 100)
        }
    }
}