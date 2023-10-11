package eu.tutorials.sesavannah

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class ApplicationView : AppCompatActivity() {


    private fun onFileSelected(uri: Uri) {
        try {
            val contentResolver = contentResolver
            val inputStream = contentResolver.openInputStream(uri)

            // Now you can read the file using this inputStream
            // Process the input stream as required
            inputStream!!.close()
        } catch (e: Exception) {
            Log.e(TAG, "Error reading from URI", e)
        }
    }


        private val viewModel = ApplicantsViewModel()
    companion object {
        private const val TAG = "ApplicationView"
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

        submitButton.setOnClickListener {
            val mediaType = "application/pdf".toMediaTypeOrNull()
            val firstName = firstnameEditText.text.toString()
            val lastName = lastnameEditText.text.toString()
            val email = emailEditText.text.toString()
            val selectedDepartment = departmentSpinner.selectedItem?.toString() ?: ""
            Log.d("ApplicationView", "Selected URI: $selectedPdfUri")




            val filePath = FileUtil.getPathFromUri(this, selectedPdfUri)
            if (filePath != null) {
                val requestFile = RequestBody.create(mediaType, File(filePath))
                val resumePart = MultipartBody.Part.createFormData("resume", File(filePath).name, requestFile)
                viewModel.createApplicant(firstName, lastName, email, selectedDepartment, resumePart)
                Log.d("ApplicationView", "Selected URI: $selectedPdfUri")

            } else {
                Log.e("ApplicationView", "Error obtaining file path from URI")
            }


            //val requestFile = File(filePath).asRequestBody("application/pdf".toMediaTypeOrNull())






        }



        attachPdfButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            //creates a new intent with the action to get content. This is a standard action for Android to open a file picker.
            intent.type = "application/pdf"
            //restricts the type of files to be shown in the file picker to only PDF files.
            startActivityForResult(intent, REQUEST_CODE_PICK_PDF)
            //startActivityForResult(intent, REQUEST_CODE_PICK_PDF)  in this activity, the intent is set to requestCode which is in  super.onActivityResult(requestCode, resultCode, data)
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

            // Call the onFileSelected method here
            selectedPdfUri?.let { onFileSelected(it) }

            // Now you can use the selectedPdfUri to process or upload the PDF
        }
    }





}
