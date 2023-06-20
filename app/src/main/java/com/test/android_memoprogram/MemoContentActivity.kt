package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memoprogram.databinding.ActivityMemoContentBinding

class MemoContentActivity : AppCompatActivity() {

    lateinit var activityMemoContentBinding: ActivityMemoContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoContentBinding = ActivityMemoContentBinding.inflate(layoutInflater)
        setContentView(activityMemoContentBinding.root)

        val name = intent.getStringExtra("memoName")
        val content = intent.getStringExtra("memoContent")

        activityMemoContentBinding.run {
            textViewShowMemoName.text = "메모 제목 : $name"
            textViewShowMemoContent.text = "메모 내용 : $content"

            buttonReturn.run {
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}