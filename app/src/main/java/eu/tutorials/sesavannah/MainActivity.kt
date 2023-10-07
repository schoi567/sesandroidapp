package eu.tutorials.sesavannah



import androidx.core.text.HtmlCompat
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.lifecycle.Observer
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
   // private val viewModel by viewModels<ApplicantsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.fetchAdminData()
        }

        viewModel.adminDataLiveData.observe(this, Observer { data ->
            displayDataInMessageBox(data)
        })
*/



        val textView3: TextView = findViewById(R.id.textView3)
        val text = "<b>Note</b>\nThis application is for job posting\n <br><br> In order to reach our main website, please refer to <a href='https://www.seoyoneh.com/eng/brand/intro'>https://www.seoyoneh.com/eng/brand/intro</a>"
        textView3.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
        textView3.movementMethod = LinkMovementMethod.getInstance() // Makes the link clickable
    }

    private fun displayDataInMessageBox(data: List<String>) {
        val dataString = data.joinToString("\n")
        AlertDialog.Builder(this)
            .setTitle("Admin Data")
            .setMessage(dataString)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}



/*    private fun setupClickableLink() {
        val textView: TextView = findViewById(R.id.your_textview_id)
        val url = "https://www.seoyoneh.com/eng/brand/intro"
        val spannableString = SpannableString(url)
        spannableString.setSpan(URLSpan(url), 0, url.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance() }


         Setup clickable link in the TextView
        setupClickableLink()
        */