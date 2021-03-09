package `in`.astudentzone.ipselchamoli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.TextView


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class signInactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_inactivity)

        val box=findViewById<TextView>(R.id.textView3)

        //if value return from the server is true display that
        //else display rejected with message
        //  if (accepted): congratulation you have been selected and your interview is scheduled on : data
        //											else : application rejected : reason



        //getting the aadhar_no
        val aadhar_no = intent.getStringExtra("contact_no").toString()
        //send the request to the server and it should return the value
        val data =sendData(aadhar_no)

        box.text= data



    }
    fun sendData(contact_no: String): String {
        // Instantiate the RequestQueue.
        var box = findViewById<TextView>(R.id.textView3)
        var newsdata: String = ""
        MarsApi.retrofitService.getProperties(contact_no).enqueue(object : Callback<DataFromAPIFormat> {
            override fun onResponse(
                call: Call<DataFromAPIFormat>,
                response: Response<DataFromAPIFormat>
            ) {

                val data: DataFromAPIFormat? = response.body()
                val display= data?.message
                box.text=display
            }

            override fun onFailure(call: Call<DataFromAPIFormat>, t: Throwable) {
                Log.d("wahbhai", "ek aur error lo")
            }

        })
        return newsdata
    }


}