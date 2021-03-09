package `in`.astudentzone.ipselchamoli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signin= findViewById<Button>(R.id.button2)

    }

    fun signIna(view: View) {

        //if login sucessful sign In
        //how to check if user exist
        val contact_no = findViewById<EditText>(R.id.editTextTextPersonName)
        val date_of_birth= findViewById<EditText>(R.id.editTextNumberPassword)
        Log.d("wahbhai", date_of_birth.text.toString())
        loginUser(date_of_birth.text.toString(), contact_no.text.toString())


    }
    //intent for registration of the user
    fun registration(view: View) {
        val intent2 = Intent(this, registration::class.java)
        startActivity(intent2)
    }

    //loginuser check for signin intent to work

    private fun loginUser(date_of_birth: String, contact_no: String): Boolean {

        // Instantiate the RequestQueue.

        var newsdata:Boolean= false
        MarsApi.retrofitService.getLogin(date_of_birth , contact_no).enqueue(object :
            Callback<DataFromAPIFormat> {
            override fun onResponse(
                call: Call<DataFromAPIFormat>,
                response: retrofit2.Response<DataFromAPIFormat>
            ) {

                val data: DataFromAPIFormat? = response.body()
                val display= data?.message
               Toast.makeText(this@MainActivity, display, Toast.LENGTH_SHORT).show()

                if ((data?.success)=="1"){
                    Log.d("wahh", "yess")
                     val intent = Intent(this@MainActivity , signInactivity::class.java)
                     intent.putExtra("contact_no",contact_no)
                     startActivity(intent)
                }
            }

            override fun onFailure(call: Call<DataFromAPIFormat>, t: Throwable) {
                Log.d("wahbhai", "ek aur error lo")
            }

        })
        return newsdata
    }



}