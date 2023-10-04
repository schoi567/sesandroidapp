package eu.tutorials.sesavannah

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
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
                val descriptionTextView = findViewById<TextView>(eu.tutorials.sesavannah.R.id.job_description)

                when (parentView.getItemAtPosition(position).toString()) {
                    "General Affairs" -> {
                        descriptionTextView.text = """
                            Key Responsibilities:

                            1. Conduct regular walkthroughs to observe and assess plant operations.
                            ...
                            For inquiries, please contact: paul.seoyonehwa@gmail.com and smchoi@seoyoneh.com
                            Send Email?
                        """.trimIndent()
                    }

                    "IT" -> {
                        descriptionTextView.text = """
                            Currently there is no Job Postings

                            For inquiries, please contact: paul.seoyonehwa@gmail.com
                            Send Email
                        """.trimIndent()
                    }

                    "Development" -> {
                        descriptionTextView.text = """
                            Currently there is no Job Postings

                            For inquiries, please contact: paul.seoyonehwa@gmail.com
                            Send Email
                        """.trimIndent()
                    }


                    "Human Resource" -> {
                        descriptionTextView.text = """
                        Essencial Duties & Responsibilities

• Perform all work assignments in accordance with Seoyon E-Hwa of Savannah safety and quality polices practices, and procedures.

• Work closely management and third party staffing agencies to ensure staffing needs are met timely

• Proficiently understand and use HRIS system, ATS systems and benefits systems

• Schedule and conduct interviews with potential new hires

• Use company document management software to record and manage employee files.

• Onboard new hires and ensure that all employee records are maintained.

• Ability to plan proactively with management.

• Ability to quickly respond to employee concerns.

• Ability to use Seoyon E-Hwa software systems.

• Ability to work well as a member of a team and individually.

• Other duties as assigned.


Other Duties & Responsibilities

• Demonstrate capability and aptitude to learn various duties associated with Human Resources.

• Comply with all company policies, practices and standards.

• Comply with all Seoyon E-Hwa of Savannah Quality polices, practices, and standards.

• Participate in proactive continuous improvement initiatives to improve the employee experience, quality and productivity.

• Communicate effectively with coworkers and management at all levels.

• Adhere to Seoyon E-Hwa of Savannah Intellectual Property Policy.

• Maintain the highest degree of honesty and integrity at all times.

• Maintain high levels of personal and professional organization and housekeeping.


Knowledge, Skills & Education

• Education and/or Experience: Bachelors Degree or equivalent from an accredited College or University, and at least 2 years related experience and/or training; or equivalent combination of education and experience related to customer service.

• Language Skills: Ability to read and comprehend written instructions, correspondence, and memos. Ability to write in depth correspondence. Ability to effectively present information in one-on-one and small group situations to company leadership and other employees of the organization.

• Mathematical Skills: Ability to add, subtract, multiply, and divide in all units of measure, using whole numbers, common fractions, and decimals.

• Computer: Ability to use computer programs including MS suite and Seoyon E-Hwa Software.

• Progression: Ability to show progress towards attaining basic skills in the fore mentioned para 2.0 Duties and Responsibilities.


For inquiries, please contact us at: paul.seoyonehwa@gmail.com
Send Email
                        """.trimIndent()
                    }



                    /*
                           <item>Search here for departments</item>
        <item>Assembly</item>
        <item>Development</item>
        <item>Engineering</item>
        <item>Finance</item>
        <item>General Affairs</item>
        <item>Human Resource</item>
        <item>Injection</item>
        <item>IT</item>
        <item>Production</item>
        <item>Production Control</item>
                  */


                    else -> {
                        descriptionTextView.text = "" // Reset or provide default text for other options
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Your code here for when no item is selected
            }
        }
    }
}



/*

spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
        val descriptionTextView = findViewById<TextView>(eu.tutorials.sesavannah.R.id.job_description)

        val selectedJob = parentView.getItemAtPosition(position).toString()
        descriptionTextView.text = jobDescriptions[selectedJob] ?: "No description available."
    }

    override fun onNothingSelected(parentView: AdapterView<*>) {
        // Your code here for when no item is selected
    }
}






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



