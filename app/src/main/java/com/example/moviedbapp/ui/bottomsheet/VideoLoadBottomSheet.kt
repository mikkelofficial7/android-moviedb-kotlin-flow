package com.example.moviedbapp.ui.bottomsheet

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.moviedbapp.base.baseview.BaseBottomSheet
import com.example.moviedbapp.databinding.BottomsheetLoadVideoBinding


class VideoLoadBottomSheet(val key: String) : BaseBottomSheet<BottomsheetLoadVideoBinding>() {
    override fun getUiBinding(): BottomsheetLoadVideoBinding {
        return BottomsheetLoadVideoBinding.inflate(cloneLayoutInflater)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onFirstLaunch(view: View, savedInstanceState: Bundle?) {
        val frameVideo = "<html><body>Video From YouTube<br><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/'$key'\" frameborder=\"0\" allowfullscreen></iframe></body></html>"
        viewBinding?.webViewYoutube?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }

        val webSettings = viewBinding?.webViewYoutube?.settings
        webSettings?.javaScriptEnabled = true

        viewBinding?.webViewYoutube?.loadData(frameVideo, "text/html", "utf-8")
        viewBinding?.webViewYoutube?.loadUrl("https://www.youtube.com/watch?v=$key")

        viewBinding?.ivBack?.setOnClickListener {
            dismiss()
        }
    }

}