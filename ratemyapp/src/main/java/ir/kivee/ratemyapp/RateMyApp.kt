package ir.kivee.ratemyapp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity


class RateMyApp(private val ctx: Activity){



    fun showWhenReady(
            title: String,
            message: String,
            positiveButtonText: String = "Rate Now",
            negativeButtonText: String = "No thanks",
            cancelButtonText: String = "Maybe later",
            launchInterval: Int = 5,
            dayInterval: Int = 5
    ) {
        val prefs = ctx.getSharedPreferences("ratemyapp", 0)
        if (prefs.getBoolean("dontshowagain", false)) {
            return
        }

        val editor = prefs.edit()


        val launchCount = prefs.getLong("launch_count", 0) + 1
        editor.putLong("launch_count", launchCount)

        var dateFirstLaunch = prefs.getLong("date_firstlaunch", 0)
        if (dateFirstLaunch == 0.toLong()) {
            dateFirstLaunch = System.currentTimeMillis()
            editor.putLong("date_firstlaunch", dateFirstLaunch)
        }


        if (launchCount >= launchInterval) {
            if (System.currentTimeMillis() >= dateFirstLaunch +
                    (dayInterval * 24 * 60 * 60 * 1000)
            ) {

                showNow(title, message, positiveButtonText, negativeButtonText, cancelButtonText)
            }
        }

        editor.apply()
    }


    fun showNow(
            title: String,
            message: String,
            positiveButtonText: String,
            negativeButtonText: String,
            cancelButtonText: String
    ) {
        val fragmentManager = (ctx as FragmentActivity).supportFragmentManager
        val bottomSheet =
                RateFragment()
        val bundle = Bundle()
        with(bundle) {
            putString("packageName", ctx.applicationContext.packageName)
            putString("title", title)
            putString("message", message)
            putString("positiveButtonText", positiveButtonText)
            putString("negativeButtonText", negativeButtonText)
            putString("cancelButtonText", cancelButtonText)
        }
        bottomSheet.arguments = bundle
        bottomSheet.show(fragmentManager, "rateMyApp")
    }





}