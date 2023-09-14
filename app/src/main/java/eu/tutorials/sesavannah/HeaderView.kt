package eu.tutorials.sesavannah

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.ArrayAdapter
import android.widget.Spinner

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
    }
}
