package com.itbird.onlinepdf.preview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private String pdfOnlineTestUrl = "http://uploadffsq.b0.upaiyun.com/upload/test1509595157.pdf";
    private WebView pdfShowWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfShowWebView = (WebView) findViewById(R.id.kkkk);
        pdfShowWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        WebSettings settings = pdfShowWebView.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setBuiltInZoomControls(true);
        pdfShowWebView.setWebChromeClient(new WebChromeClient());
        if (!TextUtils.isEmpty(pdfOnlineTestUrl)) {
            byte[] bytes = null;
            try {// 获取以字符编码为utf-8的字符
                bytes = pdfOnlineTestUrl.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            if (bytes != null) {
                pdfOnlineTestUrl = new BASE64Encoder().encode(bytes);// BASE64转码
            }
        }
        pdfShowWebView.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file="
                        + pdfOnlineTestUrl);
    }
}
