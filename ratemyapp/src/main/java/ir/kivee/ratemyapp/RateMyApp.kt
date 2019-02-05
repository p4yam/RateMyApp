package ir.kivee.ratemyapp

import android.app.Activity
import androidx.fragment.app.FragmentActivity


class RateMyApp(
    private val ctx: Activity
) {

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
            RateFragment.newInstance(
                title
                , message
                , positiveButtonText
                , negativeButtonText
                , cancelButtonText
            )
        bottomSheet.show(fragmentManager, "rateMyApp")
    }


    fun dontShowAgain() {
        val prefs = ctx.getSharedPreferences("ratemyapp", 0)
        val editor = prefs.edit()
        editor.clear()
        editor.putBoolean("dontshowagain", true)
        editor.apply()
    }

    fun showLater() {
        val prefs = ctx.getSharedPreferences("ratemyapp", 0)
        val editor = prefs.edit()
        editor.clear()
        editor.putLong("launch_count", 0)
        editor.putLong("date_firstlaunch", 0)
        editor.apply()
    }
}