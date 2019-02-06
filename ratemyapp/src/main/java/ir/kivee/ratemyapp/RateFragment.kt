package ir.kivee.ratemyapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_rate.*


class RateFragment : BottomSheetDialogFragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rate, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rateHeader.text = arguments?.getString("title")
        rateMessage.text = arguments?.getString("message")
        ratePositive.text = arguments?.getString("positiveButtonText")
        rateNegative.text = arguments?.getString("negativeButtonText")
        rateCancel.text = arguments?.getString("cancelButtonText")
        rateButtonContainer.layoutDirection = View.LAYOUT_DIRECTION_RTL

        ratePositive.setOnClickListener {
            rateApp(context!!)
            dontShowAgain()
            dismiss()
        }

        rateNegative.setOnClickListener {
            dontShowAgain()
            dismiss()
        }


        rateCancel.setOnClickListener {
            showLater()
            dismiss()
        }

    }


    private fun rateApp(ctx: Context) {
        val uri = Uri.parse("market://details?id=${ctx.applicationContext.packageName}")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)

        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            ctx.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            ctx.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=${ctx.applicationContext.packageName}")))
        }
    }


    fun dontShowAgain() {
        val prefs = context!!.getSharedPreferences("ratemyapp", 0)
        val editor = prefs.edit()
        editor.clear()
        editor.putBoolean("dontshowagain", true)
        editor.apply()
    }

    fun showLater() {
        val prefs = context!!.getSharedPreferences("ratemyapp", 0)
        val editor = prefs.edit()
        editor.clear()
        editor.putLong("launch_count", 0)
        editor.putLong("date_firstlaunch", 0)
        editor.apply()
    }
}
