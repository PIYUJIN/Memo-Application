package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.android_memoprogram.DataClass.Companion.category
import com.test.android_memoprogram.DataClass.Companion.categoryList
import com.test.android_memoprogram.DataClass.Companion.categoryPosition
import com.test.android_memoprogram.DataClass.Companion.memoList
import com.test.android_memoprogram.databinding.ActivityMemoRegisterBinding

class MemoRegisterActivity : AppCompatActivity() {

    lateinit var activityMemoRegisterBinding: ActivityMemoRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoRegisterBinding = ActivityMemoRegisterBinding.inflate(layoutInflater)
        setContentView(activityMemoRegisterBinding.root)

        activityMemoRegisterBinding.run {
            // 메모 '추가' 클릭시 동작
            buttonAddMemo.run {
                setOnClickListener {
                    var name = editTextMemoNameRegister.text.toString()
                    var content = editTextMemoContentRegister.text.toString()
                    var category = DataClass.category

                    memoList.add(MemoInfo(category,name,content))


                    finish()
                }
            }

            // '취소' 클릭시 동작
            buttonCancelMemoRegister.run {
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}