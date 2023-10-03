package eu.tutorials.sesavannah
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

class HeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var spinnerInitialized = false

    init {
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
        val activityContext = context as? Activity
        val selectedPosition = activityContext?.intent?.getIntExtra("selectedPosition", 0) ?: 0
        spinner.setSelection(selectedPosition)
        // Get the selected position from the intent and set it
      /*  val selectedPosition = (context as? Activity)?.intent?.getIntExtra("selectedPosition", 0) ?: 0
        spinner.setSelection(selectedPosition) */

        // Add the onItemSelectedListener for the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (!spinnerInitialized) {
                    spinnerInitialized = true
                    return
                }
                when(parent.getItemAtPosition(position).toString()) {
                    "Company Overview" -> {
                        if (context !is CompanyOverviewActivity) {
                            val intent = Intent(context, CompanyOverviewActivity::class.java)
                            intent.putExtra("selectedPosition", position)
                            context.startActivity(intent)
                        }
                    }
                    "Home" -> {
                        if (context !is MainActivity) {
                            val intent = Intent(context, MainActivity::class.java)
                            intent.putExtra("selectedPosition", position)
                            context.startActivity(intent)
                        }
                    }

                    "Contacts" -> {
                        if (context !is ContactsView) {
                            val intent = Intent(context, ContactsView::class.java)
                            intent.putExtra("selectedPosition", position)
                            context.startActivity(intent)
                        }
                    }

                    "Job Postings" -> {
                        if (context !is ActivityJobPostings) {
                            val intent = Intent(context, ActivityJobPostings::class.java)
                            intent.putExtra("selectedPosition", position)
                            context.startActivity(intent)
                        }
                    }
                    "Events" -> {
                        if (context !is EventsView) {
                            val intent = Intent(context, EventsView::class.java)
                            intent.putExtra("selectedPosition", position)
                            context.startActivity(intent)
                        }
                    }






                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }


    fun setSelectedPosition(position: Int) {
        findViewById<Spinner>(R.id.spinner).setSelection(position)
    }
}
