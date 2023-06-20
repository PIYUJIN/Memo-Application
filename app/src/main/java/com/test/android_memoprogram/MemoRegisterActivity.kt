package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.android_memoprogram.databinding.ActivityMemoRegisterBinding

class MemoRegisterActivity : AppCompatActivity() {

    lateinit var activityMemoRegisterBinding: ActivityMemoRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoRegisterBinding = ActivityMemoRegisterBinding.inflate(layoutInflater)
        setContentView(activityMemoRegisterBinding.root)

        activityMemoRegisterBinding.run {
            buttonAddMemo.run {
                setOnClickListener {

                    val resultMemoIntent = Intent()
                    resultMemoIntent.putExtra("memoName","${editTextMemoNameRegister.text}")
                    resultMemoIntent.putExtra("memoContent","${editTextMemoContentRegister.text}")

                    Log.d("lion","${editTextMemoNameRegister.text}")

                    setResult(RESULT_OK,resultMemoIntent)
                    finish()
                }
            }

            buttonCancelMemoRegister.run {
                setOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }
    }
}