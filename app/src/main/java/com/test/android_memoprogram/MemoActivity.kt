package com.test.android_memoprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_memoprogram.DataClass.Companion.category
import com.test.android_memoprogram.DataClass.Companion.categoryList
import com.test.android_memoprogram.DataClass.Companion.categoryMemoList
import com.test.android_memoprogram.DataClass.Companion.categoryPosition
import com.test.android_memoprogram.DataClass.Companion.memoList
import com.test.android_memoprogram.DataClass.Companion.memoPosition
import com.test.android_memoprogram.databinding.ActivityMemoBinding
import com.test.android_memoprogram.databinding.MemoBinding
import com.test.android_memoprogram.databinding.RowBinding

class MemoActivity : AppCompatActivity() {

    lateinit var activityMemoBinding: ActivityMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(activityMemoBinding.root)

        activityMemoBinding.run {

            // context menu 특정 view에 설정
            registerForContextMenu(recyclerViewMemo)

            recyclerViewMemo.run {
                adapter = RecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MemoActivity)
            }
        }
    }

    var memoPositionList = mutableListOf<Int>()
    // 화면 전환 후 돌아왔을 때 실행
    override fun onResume() {
        super.onResume()

        categoryMemoList.clear()

        for(i in 0 until memoList.size) {

            if (memoList[i].category == category) {
                categoryMemoList.add(memoList[i])
                memoPositionList.add(i)
            }
        }

        for(i in 0 until memoPositionList.size) {
            Log.d("lion","memoList position : ${memoPositionList[i]}")
        }

        val adapter = activityMemoBinding.recyclerViewMemo.adapter as MemoActivity.RecyclerAdapter
        adapter.notifyDataSetChanged()
    }

    // option menu 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.memo_main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    // option menu 클릭시 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // menu item ID로 분기
        when(item.itemId) {
            R.id.addMemoItem -> {
                var memoRegisterIntent = Intent(this@MemoActivity,MemoRegisterActivity::class.java)
                startActivity(memoRegisterIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    // memo list recyclerView 설정
    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(memoBinding: MemoBinding) : RecyclerView.ViewHolder(memoBinding.root) {

            var textMemoName: TextView

            init {
                textMemoName = memoBinding.textViewMemo

                memoBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    menuInflater.inflate(R.menu.memo_menu,menu)

                    // '메모 수정' 클릭시 동작
                    menu[0].setOnMenuItemClickListener {
                        var memoEditIntent = Intent(this@MemoActivity,MemoEditActivity::class.java)
                        // 선택한 메모의 position 전달
                        DataClass.memoPosition = adapterPosition
                        startActivity(memoEditIntent)
                        false
                    }

                    // '메모 삭제' 클릭시 동작
                    menu[1].setOnMenuItemClickListener {
                        // 해당 메모 삭제
                        categoryMemoList.removeAt(adapterPosition)

                        var memoListPosition = memoPositionList[adapterPosition+1]
                        Log.d("lion","삭제되는 position : ${memoListPosition}")

                        memoList.removeAt(memoListPosition)

                        // recyclerView 갱신
                        val adapter = activityMemoBinding.recyclerViewMemo.adapter as MemoActivity.RecyclerAdapter
                        adapter.notifyDataSetChanged()

                        false
                    }
                }

                memoBinding.root.setOnClickListener {
                    var memoShowIntent = Intent(this@MemoActivity,MemoContentActivity::class.java)
                    memoPosition = adapterPosition
                    startActivity(memoShowIntent)
                }
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
            return categoryMemoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

            holder.textMemoName.text = categoryMemoList[position].name
        }
    }
}