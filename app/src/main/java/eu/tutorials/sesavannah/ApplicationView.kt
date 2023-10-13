package eu.tutorials.sesavannah

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ApplicationView : AppCompatActivity() {

    companion object {
        private const val TAG = "ApplicationView"
        private const val REQUEST_CODE_PICK_PDF = 1001
        private const val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1002
    }

    private val viewModel = ApplicantsViewModel()
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

        setupSpinner(departmentSpinner)
        setupSubmitButton(firstnameEditText, lastnameEditText, emailEditText, departmentSpinner, submitButton)
        setupAttachButton(attachPdfButton)
    }

    private fun setupSpinner(departmentSpinner: Spinner) {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.departments_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        departmentSpinner.adapter = adapter

        departmentSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                val selectedDepartment = parentView.getItemAtPosition(position).toString()
                Log.d(TAG, "Selected Department: $selectedDepartment")
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Handle the case where no department is selected, if necessary
            }
        }
    }

    private fun setupSubmitButton(firstnameEditText: EditText, lastnameEditText: EditText, emailEditText: EditText, departmentSpinner: Spinner, submitButton: Button) {
        submitButton.setOnClickListener {
            val mediaType = "application/pdf".toMediaTypeOrNull()
            val firstName = firstnameEditText.text.toString()
            val lastName = lastnameEditText.text.toString()
            val email = emailEditText.text.toString()
            val selectedDepartment = departmentSpinner.selectedItem?.toString() ?: ""
            val filePath = FileUtil.getPathFromUri(this, selectedPdfUri)
            filePath?.let {
                val requestFile = RequestBody.create(mediaType, File(it))
                val resumePart = MultipartBody.Part.createFormData("resume", File(it).name, requestFile)
                viewModel.createApplicant(firstName, lastName, email, selectedDepartment, resumePart)
                Log.d(TAG, "Selected URI: $selectedPdfUri")
            } ?: Log.e(TAG, "Error obtaining file path from URI")
        }
    }

    private fun setupAttachButton(attachPdfButton: Button) {
        attachPdfButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_REQUEST_CODE)
            } else {
                openFilePicker()
            }
        }
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/pdf"
        }
        startActivityForResult(intent, REQUEST_CODE_PICK_PDF)
    }

    private fun onFileSelected(uri: Uri) {
        try {
            contentResolver.openInputStream(uri)?.use { inputStream ->
                // Process the input stream as required
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error reading from URI", e)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_PDF && resultCode == Activity.RESULT_OK && data != null) {
            selectedPdfUri = data.data
            selectedPdfUri?.let { onFileSelected(it) }
        }
    }
}
