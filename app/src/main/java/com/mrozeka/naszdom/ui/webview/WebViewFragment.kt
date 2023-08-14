package com.mrozeka.naszdom.ui.webview

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mrozeka.naszdom.databinding.FragmentWebViewBinding


open class WebViewFragment : Fragment() {

    private var _binding: FragmentWebViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val webViewModel: WebViewModel by viewModels { WebViewModelFactory(arguments) }
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val webView: WebView = binding.webview.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }
        webViewModel.url.observe(viewLifecycleOwner) {
            webView.loadUrl(it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}