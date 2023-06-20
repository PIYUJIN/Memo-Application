package com.test.android_memoprogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memoprogram.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {

    lateinit var activityMemoBinding: ActivityMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(activityMemoBinding.root)

        activityMemoBinding.run {

        }
    }
}