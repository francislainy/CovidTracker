package com.example.covidtracker.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_upload_id.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class UploadIdFragment : Fragment(R.layout.fragment_upload_id) {

    private var list: List<EditText>? = null
    // todo: have dynamic random not hardcoded pin. 15/08/20
    private val correctPin = listOf("1", "2", "3", "4", "5", "6")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Upload your Random IDs"

        list = listOf(et1, et2, et3, et4, et5, et6)

        et1.addTextChangedListener(textWatcher)
        et2.addTextChangedListener(textWatcher)
        et3.addTextChangedListener(textWatcher)
        et4.addTextChangedListener(textWatcher)
        et5.addTextChangedListener(textWatcher)
        et6.addTextChangedListener(textWatcher)
    }


    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable) {

            if (hasEmpty()) {

                tvPinFeedback.text = "Please fill all fields."

            } else {

                if (isCorrectPin()) {
                    tvPinFeedback.text = "Correct. Pin matches random ID."
                } else {

                    if (tvPinFeedback.text != "Incorrect Pin. Please try again.") { // To avoid text to be redrawn we only rewrite it if it's not already there.

                        tvPinFeedback.text = "Incorrect Pin. Please try again."
                    }
                }

            }
        }
    }


    private fun isCorrectPin(): Boolean {

        var itemsMatch = true
        val textList = listOf(
            et1.text.toString(),
            et2.text.toString(),
            et3.text.toString(),
            et4.text.toString(),
            et5.text.toString(),
            et6.text.toString()
        )

        for ((index, value) in textList.withIndex()) {

            if (correctPin[index] != value) {
                itemsMatch = false
                break
            }

        }

        return itemsMatch

    }


    private fun hasEmpty(): Boolean {


        var hasEmpty = false

        for (i in list!!) {

            if (i.text.isNullOrEmpty()) {
                hasEmpty = true
                break
            }
        }

        return hasEmpty
    }

}