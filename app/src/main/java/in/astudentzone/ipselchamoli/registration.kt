package `in`.astudentzone.ipselchamoli

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import com.android.volley.*

import com.android.volley.toolbox.StringRequest

import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.UnsupportedEncodingException
import java.util.*
import kotlin.collections.HashMap

class registration : AppCompatActivity() {

    private val mYear:Int = 0
    private val mMonth:Int = 0
    private val mDay:Int = 0
    private val mHour:Int = 0
    private val mMinute:Int = 0

    lateinit var valuee: Text
    lateinit var spinner: Spinner
    lateinit var spinner2: Spinner
    lateinit var dob: EditText

    //private val TAG: String = registration::class.java.simpleName
    private val TAG: String = "hello"

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        dob = findViewById<EditText>(R.id.editTextTextPersonName4)




        spinner = findViewById(R.id.spinner)
        // spinner.onItemSelectedListener = this
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }



        spinner2 = findViewById(R.id.spinner2)
        // spinner.onItemSelectedListener = this
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.gender,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
        }


        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                valuee = parent.getItemAtPosition(pos) as Text
                Log.d(TAG, valuee.toString())
                val text: String = spinner.getSelectedItem().toString()
//                val text2: String = spinner2.getSelectedItem().toString()
//                Log.d("whatisthis", text2)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        val button: Button = findViewById(R.id.btn_date)
        //        var dobb = findViewById<Button>(R.id.btn_date)
        button.setOnClickListener {
            var msg: String
            var sol = String()
            val c = Calendar.getInstance()
            var mYear = c.get(Calendar.YEAR)
            var mMonth = c.get(Calendar.MONTH)
            var mDay = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                val month = monthOfYear + 1
                msg = "$year-$month-$dayOfMonth"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                //   dobb.setText(dayOfMonth)
                dob.setText(msg)
            }, mYear, mMonth, mDay)

            datePickerDialog.show()

        }
    }


    fun data(view: View) {
        val name: EditText = findViewById<EditText>(R.id.editTextTextPersonName2)
        val fathersName: EditText = findViewById<EditText>(R.id.editTextTextPersonName3)
        val address = findViewById<EditText>(R.id.editTextTextMultiLine)
       // val aadhar = findViewById<EditText>(R.id.editTextNumber)
        val phone = findViewById<EditText>(R.id.editTextPhone)
        //val dob = findViewById<Button>(R.id.btn_date)
      dob = findViewById<EditText>(R.id.editTextTextPersonName4)
        val email = findViewById<EditText>(R.id.editTextTextPersonName6)
        val gender = spinner2.getSelectedItem().toString()
        val pincode = findViewById<EditText>(R.id.editTextTextPersonName7)
        val activity_work = findViewById<EditText>(R.id.editTextTextPersonName8)
        val activity_desc = findViewById<EditText>(R.id.editTextTextPersonName9)
        val text: String = spinner.getSelectedItem().toString()
        // Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        Log.d("testingh", dob.text.toString())

        sendData(
            name.text.toString(),
            fathersName.text.toString(),
            address.text.toString(),
          //  aadhar.text.toString(),
            phone.text.toString(),
            dob.text.toString(),
            email.text.toString(),
            gender,
            pincode.text.toString(),
            activity_work.text.toString(),
            activity_desc.text.toString(),
            text
        )

    }


    private fun sendData(
        name: String,
        fathersName: String,
        address: String,
        //aadhar: String,
        phone: String,
        dob: String,
        email: String,
        gender: String,
        pincode: String,
        activity_work: String,
        activity_desc: String,
        options: String
    ) {
        // Instantiate the RequestQueue.

        val queue = MySingleton.getInstance(this.applicationContext).requestQueue
        val url = "http://192.168.43.66/ipsel/index.php"

        //creating volley string request
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)


                     var lengthTest= obj.getString("message").length.toString()
                    //intent to main page (particularly is successful)
                    if (lengthTest=="32"){
                        //place a intent here
                        Toast.makeText(applicationContext, obj.getString("message") +" and forwarded to concern department", Toast.LENGTH_LONG)
                            .show()
                        val intentForMain = Intent(this, MainActivity::class.java)
                        startActivity(intentForMain)
                    }
                    else{
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG)
                        .show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val postData = HashMap<String, String>()
              //  postData.put("aadhar_no", aadhar)
                postData.put("contact_no", phone)
                postData.put("name", name)
                postData.put("fathers_name", fathersName)
                postData.put("address", address)
                postData.put("options", options)
                postData.put("date_of_birth", dob)
                postData.put("email_id", email)
                postData.put("gender", gender)
                postData.put("pincode", pincode)
                postData.put("activity_work", activity_work)
                postData.put("activity_desc", activity_desc)
                Log.d("test", postData.toString())
                return postData
            }
        }

        //adding request to queue
        MySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }

//    fun datepick(view: View) {
//        // Get Current Date
//        var msg: String
//        var dobb = findViewById<Button>(R.id.btn_date)
//        var sol=String()
//        val c = Calendar.getInstance()
//       var mYear = c.get(Calendar.YEAR)
//       var mMonth = c.get(Calendar.MONTH)
//       var  mDay = c.get(Calendar.DAY_OF_MONTH)
//        val datePickerDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
//                val month = monthOfYear + 1
//                msg = "$year-$monthOfYear-$dayOfMonth"
//                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//             dobb.setText(dayOfMonth)
//
//            }, mYear, mMonth, mDay)
//
//        datePickerDialog.show()
//
//
//
//    }


}

    






