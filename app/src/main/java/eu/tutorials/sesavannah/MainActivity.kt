package eu.tutorials.sesavannah

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup clickable link in the TextView
        setupClickableLink()
    }

    private fun setupClickableLink() {
        val textView: TextView = findViewById(R.id.your_textview_id)
        val url = "https://www.seoyoneh.com/eng/brand/intro"
        val spannableString = SpannableString(url)
        spannableString.setSpan(URLSpan(url), 0, url.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}
