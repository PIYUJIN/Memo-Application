package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memoprogram.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    lateinit var activityCategoryBinding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCategoryBinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(activityCategoryBinding.root)

        activityCategoryBinding.run {
            buttonAdd.run {
                setOnClickListener {
                    val categoryName = editTextCategoryName.text

                    val resultCategoryIntent = Intent()
                    resultCategoryIntent.putExtra("categoryName","$categoryName")

                    setResult(RESULT_OK,resultCategoryIntent)

                    finish()
                }
            }
            buttonCancel.run {
                setOnClickListener {
                    setResult(RESULT_CANCELED)
                    // activity 종료
                    finish()
                }
            }
        }
    }
}