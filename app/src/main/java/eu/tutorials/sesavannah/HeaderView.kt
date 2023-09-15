package eu.tutorials.sesavannah

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout

class HeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        // Get custom attributes
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderView)
        val layoutResId = typedArray.getResourceId(R.styleable.HeaderView_header_layout, R.layout.activity_header)

        // Inflate the layout
        LayoutInflater.from(context).inflate(layoutResId, this, true)
        typedArray.recycle()

        // Set up the spinner
        val spinner: Spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(
            context,
            R.array.spinner_values,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Add the onItemSelectedListener for the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(parent.getItemAtPosition(position).toString()) {
                    "Company Overview" -> {
                        val intent = Intent(context, CompanyOverviewActivity::class.java)
                        context.startActivity(intent)
                    }



                    // Handle other spinner selections if needed
                    // "Home" -> { /* Navigate to Home */}
                    // "Contacts" -> { /* Navigate to Contacts */}
                    // etc.
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No action needed here
            }
        }
    }
}
