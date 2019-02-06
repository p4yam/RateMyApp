package ir.kivee.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.kivee.ratemyapp.RateMyApp

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RateMyApp(this).showWhenReady(
            "امتیاز به ما",
            "همین حالا به اپ تیگو ۵ ستاره بده و با دریافت ۴۰۰ امتیاز شانس خودت رو برای دریافت جایزه افزایش بده",
            "باشه",
            "نه",
            "بعدا",
            3,
            0
        )
    }
}
