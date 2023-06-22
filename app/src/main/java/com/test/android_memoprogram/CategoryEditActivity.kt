package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memoprogram.DataClass.Companion.categoryList
import com.test.android_memoprogram.DataClass.Companion.position
import com.test.android_memoprogram.databinding.ActivityCategoryEditBinding

class CategoryEditActivity : AppCompatActivity() {

    lateinit var activityCategoryEditBinding: ActivityCategoryEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCategoryEditBinding = ActivityCategoryEditBinding.inflate(layoutInflater)
        setContentView(activityCategoryEditBinding.root)

        activityCategoryEditBinding.run {
            // 기존 카테고리 이름 보여주기
            textViewCategory.text = categoryList[DataClass.position]

            // 카테고리 수정 '확인' 버튼 클릭시 동작
            buttoneditCategoryApply.run {
                setOnClickListener {
                    var editCategory = editTextEditCategory.text.toString()
                    categoryList[DataClass.position] = editCategory
                    finish()
                }
            }
            // '취소' 버튼 클릭시 동작
            buttonCategoryEditCancel.run {
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}