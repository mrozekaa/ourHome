package com.mrozeka.naszdom.ui.homeproject

import android.app.ActionBar.LayoutParams
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mrozeka.naszdom.R
import com.mrozeka.naszdom.databinding.FragmentHomeProjectBinding


class HomeProjectFragment : Fragment() {

    private var _binding: FragmentHomeProjectBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeProjectViewModel: HomeProjectViewModel by viewModels {
            HomeProjectViewModelFactory(
                arguments
            )
        }
        _binding = FragmentHomeProjectBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val tagsLayout = binding.fragmentHomeTagsLayout
        homeProjectViewModel.tags.observe(viewLifecycleOwner) {

            for (tag in it) {
                val tv = TextView(context)
                tv.id = View.generateViewId()
                tv.text = tag
                tv.textSize = 20f
                tv.setBackgroundResource(R.drawable.rounded_corner)
                tagsLayout.addView(tv)
            }
        }

        val webView: WebView = binding.fragmentHomeProjectWebview
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
        homeProjectViewModel.url.observe(viewLifecycleOwner) {
            webView.loadUrl(it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}