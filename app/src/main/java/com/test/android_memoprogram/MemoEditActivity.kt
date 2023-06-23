package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memoprogram.DataClass.Companion.categoryMemoList
import com.test.android_memoprogram.DataClass.Companion.memoList
import com.test.android_memoprogram.DataClass.Companion.memoPosition
import com.test.android_memoprogram.databinding.ActivityCategoryEditBinding
import com.test.android_memoprogram.databinding.ActivityMemoEditBinding

class MemoEditActivity : AppCompatActivity() {

    lateinit var activityMemoEditBinding: ActivityMemoEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoEditBinding = ActivityMemoEditBinding.inflate(layoutInflater)
        setContentView(activityMemoEditBinding.root)

        activityMemoEditBinding.run {
            // 기존 메모 제목 / 내용 보여주기
            textViewMemoName.text = categoryMemoList[memoPosition].name
            textViewMemoContent.text = categoryMemoList[memoPosition].content

            // 메모 추가 '확인' 클릭시 동작
            buttonEditContentApply.run {
                setOnClickListener {
                    var editMemoName = editTextMemoName.text.toString()
                    var editMemoContent = editTextMemoContent.text.toString()
                    var category = categoryMemoList[memoPosition].category

                    val memoListPosition = intent.getIntExtra("memoListPosition",0)

                    memoList[memoListPosition] = MemoInfo(category,editMemoName,editMemoContent)

                    finish()
                }
            }

            // '취소' 클릭시 동작
            buttonEditContentCancel.run {
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}