package eu.tutorials.sesavannah
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class ContactsView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        // Initialize the WebView
        val webView: WebView = findViewById(R.id.webview)
        webView.settings.javaScriptEnabled = true // If you want to enable JavaScript

        // Set the WebViewClient
        webView.webViewClient = WebViewClient()

        // Load the local HTML page
        webView.loadUrl("file:///android_asset/my_page.html")
    }
}
