package eu.tutorials.sesavannah
import okhttp3.MultipartBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.app.AlertDialog
import android.util.Log
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import okhttp3.RequestBody
import java.io.File

    class ApplicationView : AppCompatActivity() {
        private val viewModel = ApplicantsViewModel()
    companion object {
        private const val REQUEST_CODE_PICK_PDF = 1001
        //The requestCode gets the value of 1001 (or REQUEST_CODE_PICK_PDF) not automatically,
    // but because you explicitly set it when starting the PDF picker activity using startActivityForResult().
    }
    // Class variable to hold the selected PDF's URI
    private var selectedPdfUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        val firstnameEditText = findViewById<EditText>(R.id.firstnameEditText)
        val lastnameEditText = findViewById<EditText>(R.id.lastnameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val attachPdfButton = findViewById<Button>(R.id.attachPdfButton)
        val departmentSpinner = findViewById<Spinner>(R.id.spinner3)


        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.departments_array, // Use the departments_array you defined in arrays.xml
            android.R.layout.simple_spinner_item
        )
        // Fetch the selected department from the Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        departmentSpinner.adapter = adapter


        submitButton.bringToFront()

        submitButton.requestLayout()


        attachPdfButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            //creates a new intent with the action to get content. This is a standard action for Android to open a file picker.
            intent.type = "application/pdf"
            //restricts the type of files to be shown in the file picker to only PDF files.
            startActivityForResult(intent, REQUEST_CODE_PICK_PDF)
            //startActivityForResult(intent, REQUEST_CODE_PICK_PDF)  in this activity, the intent is set to requestCode which is in  super.onActivityResult(requestCode, resultCode, data)
        }

        submitButton.setOnClickListener {
            val firstName = firstnameEditText.text.toString()
            val lastName = lastnameEditText.text.toString()
            val email = emailEditText.text.toString()
            val selectedDepartment = departmentSpinner.selectedItem?.toString() ?: ""

            val filePath = FileUtil.getPathFromUri(this, selectedPdfUri)  // You need a utility method for this


            //val requestFile = File(filePath).asRequestBody("application/pdf".toMediaTypeOrNull())
            val mediaType = "application/pdf".toMediaTypeOrNull()
            val requestFile = RequestBody.create(mediaType, File(filePath))


            val resumePart = MultipartBody.Part.createFormData("resume", File(filePath).name, requestFile)

            viewModel.createApplicant(firstName, lastName, email, selectedDepartment, resumePart)


            // Construct the message
            var message = "First Name: $firstName\n" +
                    "Last Name: $lastName\n" +
                    "Email: $email\n" +
                    "Department: $selectedDepartment\n"

            // Append the selected PDF Uri if available
            if (selectedPdfUri != null) {
                message += "Selected PDF: ${selectedPdfUri.toString()}"
            }



        //    Log.d("ApplicationView", "First Name: $firstName, Last Name: $lastName")
            val builder = AlertDialog.Builder(
                this@ApplicationView,
                android.R.style.Theme_Material_Dialog_Alert
            )

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || selectedDepartment.isEmpty()
            ) {
                builder.setTitle("Error")
                var message = "Please provide the following details:\n"
                if (firstName.isEmpty()) message += "- First name\n"
                if (lastName.isEmpty()) message += "- Last name\n"
                if (email.isEmpty()) message += "- Last name\n"
                if (selectedDepartment.isEmpty()) message += "- selectedDepartment\n"
                builder.setMessage(message.trim())
                builder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
            } else {
                builder.setMessage(message)  // Fixed here
                builder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
            }
            builder.show()

            // Continue with sending the data to the server using Retrofit/OkHttp.

        }



        departmentSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                val selectedDepartment = parentView.getItemAtPosition(position).toString()
                // Handle the selected department here. For now, we'll just log it.
                Log.d("ApplicationView", "Selected Department: $selectedDepartment")
            }


            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Handle the case where no department is selected, if necessary
            }


        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_PDF && resultCode == Activity.RESULT_OK && data != null) {
            this.selectedPdfUri = data.data

            // Now you can use the selectedPdfUri to process or upload the PDF
        }
    }

}
