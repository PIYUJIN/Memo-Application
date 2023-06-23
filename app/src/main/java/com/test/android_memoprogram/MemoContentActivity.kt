package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memoprogram.DataClass.Companion.categoryMemoList
import com.test.android_memoprogram.DataClass.Companion.memoList
import com.test.android_memoprogram.DataClass.Companion.memoPosition
import com.test.android_memoprogram.databinding.ActivityMemoContentBinding

class MemoContentActivity : AppCompatActivity() {

    lateinit var activityMemoContentBinding: ActivityMemoContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoContentBinding = ActivityMemoContentBinding.inflate(layoutInflater)
        setContentView(activityMemoContentBinding.root)

        activityMemoContentBinding.run {
            // 메모 리스트에서 메모 제목 / 내용 추출하여 출력
            if(categoryMemoList[memoPosition].category== DataClass.category) {
                textViewShowMemoName.text = "메모 제목 : ${categoryMemoList[memoPosition].name}"
                textViewShowMemoContent.text = "메모 내용 : ${categoryMemoList[memoPosition].content}"
            }

            // '메모 목록보기' 클릭시 동작
            buttonReturn.run {
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}