package com.socket9.sunsilk.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.Result
import com.socket9.sunsilk.R
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ScannerActivity : AppCompatActivity(), AnkoLogger, ZXingScannerView.ResultHandler {

    lateinit private var mScannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
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
