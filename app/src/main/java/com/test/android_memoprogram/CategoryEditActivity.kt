package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memoprogram.databinding.ActivityCategoryEditBinding

class CategoryEditActivity : AppCompatActivity() {

    lateinit var activityCategoryEditBinding: ActivityCategoryEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCategoryEditBinding = ActivityCategoryEditBinding.inflate(layoutInflater)
        setContentView(activityCategoryEditBinding.root)

        activityCategoryEditBinding.run {
            textViewCategory.run {
                text = intent.getStringExtra("beforeCategoryName")
            }
            buttoneditCategoryApply.run {
                setOnClickListener {
                    val resultInent = Intent()
                    resultInent.putExtra("EditCategoryName", "${editTextEditCategory.text}")

                    setResult(RESULT_OK, resultInent)

                    finish()
                }
            }
            buttonCategoryEditCancel.run {
                setOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }
    }
}