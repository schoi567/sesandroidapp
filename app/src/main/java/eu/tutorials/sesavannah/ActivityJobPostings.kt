package eu.tutorials.sesavannah

import android.view.View
import android.widget.AdapterView
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity






class ActivityJobPostings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(eu.tutorials.sesavannah.R.layout.activity_jobpostings)

        val spinner = findViewById<Spinner>(eu.tutorials.sesavannah.R.id.spinner2)
        val adapter = ArrayAdapter.createFromResource(
            this,
            eu.tutorials.sesavannah.R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Assuming position is defined somewhere or you can specify a direct index
        val position = 0
        spinner.setSelection(position)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                // Your code here for when an item is selected
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Your code here for when no item is selected
            }
        }
    }
}


/*
   val spinner = findViewById<Spinner>(R.id.spinner2)
    val adapter = ArrayAdapter.createFromResource(
        this,
        R.array.spinner_items,
        R.layout.simple_spinner_item
    )
    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter



Spinner spinner = findViewById(R.id.spinner2);
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinner.setAdapter(adapter);



spinner.setSelection(position); // where 'position' is the index of the item you want to select

spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        // Your code here for when an item is selected
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // Your code here for when no item is selected
    }
});
*/



