package ir.kivee.sampleapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.kivee.ratemyapp.RateFragment
import ir.kivee.ratemyapp.RateMyApp

class MainActivity : AppCompatActivity(), RateFragment.OnRatingClickListener {


    override fun onPositiveClick() {
        Toast.makeText(this, "yes!", Toast.LENGTH_SHORT).show()
    }

    override fun onNegativeClick() {
        RateMyApp(this).dontShowAgain()
    }

    override fun onCancelClick() {
        RateMyApp(this).showLater()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RateMyApp(this).showNow(
            "امتیاز به ما",
            "همین حالا به اپ تیگو ۵ ستاره دهید و با دریافت ۴۰۰ امتیاز شانس خود را برای دریافت جایزه افزایش دهید",
            "باشه",
            "نه",
            "بعدا"
        )
    }
}
