package eu.tutorials.sesavannah

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer

//import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
 //   private val viewModel by viewModels<ApplicantsViewModel>()
     private val viewModel = ApplicantsViewModel()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView3: TextView = findViewById(R.id.textView3)
        val text = "<b>Note</b>\nThis application is for job posting\n <br><br> In order to reach our main website, please refer to <a href='https://www.seoyoneh.com/eng/brand/intro'>https://www.seoyoneh.com/eng/brand/intro</a>"
        textView3.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
        textView3.movementMethod = LinkMovementMethod.getInstance() // Makes the link clickable


        viewModel.adminDataLiveData.observe(this, Observer { dataObjects ->
            val message: String = if (dataObjects != null && dataObjects.isNotEmpty()) {
          //    val firstData = dataObjects[0]
          //      "Field1: ${firstData.id}, Field2: ${firstData.adminid}, Field3: ${firstData.password}"

                  dataObjects.toString()


           }

            else {
                "No data available."
            }

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage(message)

            val alertDialog = builder.create()
            alertDialog.show()
        })




        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.fetchAdminData()
        }




    }




}










/*

        findViewById<Button>(R.id.button).setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            val dataObject = viewModel.fetchAdminData()  // Assuming fetchAdminData() returns a String, otherwise you need to convert it to a String.
            val data = "Field1: ${dataObject.id}, Field2: ${dataObject.adminid}, Field3: ${dataObject.password}"
            builder.setMessage(data)// Adjust this based on the fields of your object
            builder.setMessage(data)

            val alertDialog = builder.create()
            alertDialog.show() // This is necessary to actually display the AlertDialog to the user.
        }


private fun setupClickableLink() {
        val textView: TextView = findViewById(R.id.your_textview_id)
        val url = "https://www.seoyoneh.com/eng/brand/intro"
        val spannableString = SpannableString(url)
        spannableString.setSpan(URLSpan(url), 0, url.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance() }


         Setup clickable link in the TextView
        setupClickableLink()

        //

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


        */