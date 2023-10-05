package eu.tutorials.sesavannah
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.app.AlertDialog
import android.util.Log
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
class ApplicationView : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        val firstnameEditText = findViewById<EditText>(R.id.firstnameEditText)
        val lastnameEditText = findViewById<EditText>(R.id.lastnameEditText)
        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.bringToFront()
        submitButton.requestLayout()

        submitButton.setOnClickListener {
            val firstName = firstnameEditText.text.toString()
            val lastName = lastnameEditText.text.toString()
            var message1 = firstName + " " + lastName

            Log.d("ApplicationView", "First Name: $firstName, Last Name: $lastName")
            val builder = AlertDialog.Builder(this@ApplicationView, android.R.style.Theme_Material_Dialog_Alert)

            if (firstName.isEmpty() || lastName.isEmpty()) {
                builder.setTitle("Error")
                var message = "Please provide the following details:\n"
                if (firstName.isEmpty()) message += "- First name\n"
                if (lastName.isEmpty()) message += "- Last name\n"
                builder.setMessage(message.trim())
                builder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
            } else {
                builder.setMessage(message1)  // Fixed here
                builder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
            }
            builder.show()

            // Continue with sending the data to the server using Retrofit/OkHttp.
        }
    }


}