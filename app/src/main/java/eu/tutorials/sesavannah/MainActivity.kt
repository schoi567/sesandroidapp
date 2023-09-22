package eu.tutorials.sesavannah
import androidx.core.text.HtmlCompat
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

        val textView3: TextView = findViewById(R.id.textView3)
        val text = "<b>Note</b>\nThis application is for job posting\n <br><br> In order to reach our main website, please refer to <a href='https://www.seoyoneh.com/eng/brand/intro'>https://www.seoyoneh.com/eng/brand/intro</a>"
        textView3.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)

        // Setup clickable link in the TextView
      //  setupClickableLink()
    }


/*    private fun setupClickableLink() {
        val textView: TextView = findViewById(R.id.your_textview_id)
        val url = "https://www.seoyoneh.com/eng/brand/intro"
        val spannableString = SpannableString(url)
        spannableString.setSpan(URLSpan(url), 0, url.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance() } */






}
