package com.socket9.sunsilk.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ScannerActivity : AppCompatActivity(), AnkoLogger, ZXingScannerView.ResultHandler {

    lateinit private var mScannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        } else return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume

    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera();           // Stop camera on pause
    }

    override fun handleResult(result: Result?) {
        info(result?.text)
        info(result?.barcodeFormat.toString())
        setResult(RESULT_OK)
        finish()
    }

}
