package ir.kivee.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.kivee.ratemyapp.RateMyApp

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RateMyApp(this).showWhenReady(
            launchInterval = 2,
            dayInterval = 0
        )
    }
}
