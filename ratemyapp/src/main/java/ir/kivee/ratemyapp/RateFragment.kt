package ir.kivee.ratemyapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_rate.*


private const val ARG_TITLE = "title"
private const val ARG_MESSAGE = "message"
private const val ARG_YES_TEXT = "yesBtn"
private const val ARG_NO_TEXT = "noBtn"
private const val ARG_CANCEL_TEXT = "cancelBtn"


class RateFragment : BottomSheetDialogFragment() {

    private var title: String? = null
    private var message: String? = null
    private var positiveButtonText: String? = null
    private var negativeButtonText: String? = null
    private var cancelButtonText: String? = null
    private var listener: OnRatingClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            message = it.getString(ARG_MESSAGE)
            positiveButtonText = it.getString(ARG_YES_TEXT)
            negativeButtonText = it.getString(ARG_NO_TEXT)
            cancelButtonText = it.getString(ARG_CANCEL_TEXT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_rate, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("p4yam",title)
        rateHeader.text=title
        rateMessage.text=message
        ratePositive.text=positiveButtonText
        rateNegative.text=negativeButtonText
        rateCancel.text=cancelButtonText

        ratePositive.setOnClickListener {
            listener?.onPositiveClick()
            dismiss()
        }

        rateNegative.setOnClickListener {
            listener?.onNegativeClick()
            dismiss()
        }

        rateCancel.setOnClickListener {
            listener?.onCancelClick()
            dismiss()
        }

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnRatingClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRatingClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnRatingClickListener {
        fun onPositiveClick()
        fun onNegativeClick()
        fun onCancelClick()
    }

    companion object {

        @JvmStatic
        fun newInstance(
            title: String
            , message: String
            , positiveButtonText: String
            , negativeButtonText: String
            , cancelButtonText: String
        ) =
            RateFragment().apply {

                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_MESSAGE, message)
                    putString(ARG_YES_TEXT, positiveButtonText)
                    putString(ARG_NO_TEXT, negativeButtonText)
                    putString(ARG_CANCEL_TEXT, cancelButtonText)

                }
            }
    }
}
