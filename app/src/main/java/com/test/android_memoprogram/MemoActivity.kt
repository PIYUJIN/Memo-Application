package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_memoprogram.databinding.ActivityMemoBinding
import com.test.android_memoprogram.databinding.MemoBinding
import com.test.android_memoprogram.databinding.RowBinding

class MemoActivity : AppCompatActivity() {

    lateinit var activityMemoBinding: ActivityMemoBinding

    var memoList = mutableListOf<MemoInfo>()

    val c4 = ActivityResultContracts.StartActivityForResult()
    val memoRegisterLauncher = registerForActivityResult(c4) {
        when(it.resultCode) {
            RESULT_OK -> {
                var category = intent?.getStringExtra("category")
                val name = it.data?.getStringExtra("memoName").toString()
                val content = it.data?.getStringExtra("memoContent").toString()

                Log.d("lion","name : $name")

                Log.d("lion","category : $category")
                memoList.add(MemoInfo(category,name,content))
            }
            RESULT_CANCELED -> {
            }
        }

        val adpater = activityMemoBinding.recyclerViewMemo.adapter as MemoActivity.RecyclerAdapter
        adpater.notifyDataSetChanged()
    }

    val c5 = ActivityResultContracts.StartActivityForResult()
    val memoContentLauncher = registerForActivityResult(c5) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(activityMemoBinding.root)


        activityMemoBinding.run {
            recyclerViewMemo.run {
                adapter = RecyclerAdapter()

                layoutManager = LinearLayoutManager(this@MemoActivity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.memo_main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.addMemoItem -> {
                val memoRegisterIntent = Intent(this@MemoActivity,MemoRegisterActivity::class.java)
                memoRegisterLauncher.launch(memoRegisterIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(memoBinding: MemoBinding) : RecyclerView.ViewHolder(memoBinding.root) {

            var memoNameText: TextView

            init {
                memoNameText = memoBinding.textViewMemo
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val memoBinding = MemoBinding.inflate(layoutInflater)
            val ViewHolder = ViewHolderClass(memoBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            memoBinding.root.layoutParams = params

            return ViewHolder
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

            var categoryHold = intent?.getStringExtra("category")

            Log.d("lion","category in hold class : $categoryHold")

            if(memoList[position].category == categoryHold) {
                holder.memoNameText.text = memoList[position].name
                Log.d("lion", "holder : ${holder.memoNameText.text}")
            }

            holder.itemView.setOnClickListener {
                val memoContentIntent = Intent(this@MemoActivity,MemoContentActivity::class.java)
                memoContentIntent.putExtra("memoName","${memoList[position].name}")
                memoContentIntent.putExtra("memoContent","${memoList[position].content}")
                memoContentLauncher.launch(memoContentIntent)
            }
        }
    }
}

data class MemoInfo(var category:String?, var name:String, var content:String)