package `in`.astudentzone.ipselchamoli

import retrofit2.Call
import retrofit2.Retrofit
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


private const val BASE_URL = "http://192.168.43.66/ipsel/"

// val queue = MySingleton3.getInstance(this).requestQueue
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
    @FormUrlEncoded
    @POST("returnf.php")
    fun getProperties(@Field("contact_no") contact_no: String):
            Call<DataFromAPIFormat>

    @FormUrlEncoded
    @POST("signin.php")
    fun getLogin(@Field("date_of_birth") date_of_birth: String ,@Field("contact_no") contact_no: String):
            Call<DataFromAPIFormat>

}

public object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

}
