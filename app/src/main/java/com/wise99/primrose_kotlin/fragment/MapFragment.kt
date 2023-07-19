package com.wise99.primrose_kotlin.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wise99.primrose_kotlin.R
import com.wise99.primrose_kotlin.databinding.FragmentMapBinding
import java.net.URISyntaxException

class MapFragment : Fragment() {

    lateinit var fragmentMapBinding : FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMapBinding = FragmentMapBinding.inflate(inflater)

        fragmentMapBinding.run {
            webView.run {
                settings.javaScriptEnabled = true // 자바스크립트 허용

                // intent 호출 시 net::ERR_UNKNOWN_URL_SCHEME 에러 해결을 위해
                webViewClient = object : WebViewClient(){
                    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
                        if (url.startsWith("intent://")) {
                            val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                            if (intent != null) {
                                val fallbackUrl = intent.getStringExtra("browser_fallback_url")
                                return if (fallbackUrl != null) {
                                    webView.loadUrl(fallbackUrl)
                                    true
                                } else {
                                    false
                                }
                            }
                        }
                        else if (url.startsWith("tel:")){
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = Uri.parse(url)
                            startActivity(intent)
                            return true
                        }
                        else if (url.startsWith("mailto:")) {
                            val intent = Intent(Intent.ACTION_VIEW)
                            val data = Uri.parse(
                                url + Uri.encode("subject") + "&body=" + Uri.encode(
                                    "body"
                                )
                            )
                            intent.data = data
                            startActivity(intent)
                            return true
                        }
                        return false
                    }
                }

                loadUrl("https://map.naver.com/v5/search/%EA%B7%BC%EC%B2%98%20%EA%BD%83%EC%A7%91")
            }
        }

        return fragmentMapBinding.root
    }
}