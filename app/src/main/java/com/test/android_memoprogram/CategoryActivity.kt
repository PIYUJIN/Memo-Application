package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memoprogram.DataClass.Companion.categoryList
import com.test.android_memoprogram.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    lateinit var activityCategoryBinding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCategoryBinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(activityCategoryBinding.root)

        activityCategoryBinding.run {
            // 카테고리 '추가' 버튼 클릭시 동작
            buttonAdd.run {
                setOnClickListener {
                    var name = editTextCategoryName.text.toString()
                    categoryList.add(name)
                    // 해당 activity 종료
                    finish()
                }
            }
            // '취소' 버튼 클릭시 동작
            buttonCancel.run {
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}