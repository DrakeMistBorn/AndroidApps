package com.bthtraining.myfuelexample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.moshi.responseObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myList: List<PersonItem>
    lateinit var myText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myText = findViewById(R.id.textView) as TextView

        Fuel.get("https://9974e8d2-fc68-4c68-90d2-5d39e1336a32.mock.pstmn.io/persons").responseObject<List<PersonItem>> { _, _, result ->
            myList = result.get()
            myText.text = myList.toString()
        }

    }
}

@JsonClass(generateAdapter = true)
data class PersonItem(

        @Json(name = "country")
        val country: String,
        @Json(name = "email")
        val email: String,
        @Json(name = "first_name")
        val firstName: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "last_name")
        val lastName: String,
        @Json(name = "modified")
        val modified: String,
        @Json(name = "vip")
        val vip: Boolean

)