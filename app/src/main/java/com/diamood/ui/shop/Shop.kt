package com.diamood.ui.shop

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Shop() {
    AndroidView(
        factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
            }
        },
        update = {
            it.loadUrl("http://diamood.shop")
        }
    )
}


@Preview
@Composable
fun ShopPreview() {
    Shop()
}
