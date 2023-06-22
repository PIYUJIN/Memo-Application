package com.test.android_memoprogram

import android.app.ActionBar.LayoutParams
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_memoprogram.DataClass.Companion.categoryList
import com.test.android_memoprogram.databinding.ActivityMainBinding
import com.test.android_memoprogram.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            recyclerViewCategory.run {
                // recyclerView를 나타내기 위한 layoutManager
                adapter = RecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            // view에 context menu 등록
            registerForContextMenu(recyclerViewCategory)
        }

    }

    override fun onResume() {
        super.onResume()

        var adapter = activityMainBinding.recyclerViewCategory.adapter as MainActivity.RecyclerAdapter
        adapter.notifyDataSetChanged()
    }

    // option menu 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    // option menu 클릭시 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // menu item별로 분기하여 작성
        when(item.itemId) {
            // '추가' menu 클릭한 경우
            R.id.addCategoryItem -> {
                // 카테고리 등록 화면으로 전환
                var categoryRegisterIntent = Intent(this@MainActivity,CategoryActivity::class.java)
                startActivity(categoryRegisterIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>(){
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {

            var textViewCategory: TextView

            init {
                textViewCategory = rowBinding.textViewCategoryName

                // context menu 생성
                rowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    menuInflater.inflate(R.menu.category_menu,menu)

                    // context menu 클릭시 동작

                    // '카테고리 수정' menu 클릭시 동작
                    menu[0].setOnMenuItemClickListener {
                        // 카테고리 수정 화면으로 전환
                        var categoryEditIntent = Intent(this@MainActivity,CategoryEditActivity::class.java)
                        startActivity(categoryEditIntent)

                        false
                    }

                    // '카테고리 삭제' menu 클릭시 동작
                    menu[1].setOnMenuItemClickListener {
                        // 해당 항목의 카테고리 등록 내역 삭제
                        // adapterPosition : 해당 항목의 순서(위치)
                        categoryList.removeAt(adapterPosition)
                        false
                    }
                }

                // recyclerVIew의 1개 항목 클릭시 동작
                rowBinding.root.setOnClickListener{
                    var memoIntent = Intent(this@MainActivity,MemoActivity::class.java)
                    startActivity(memoIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            // binding 객체 생성
            val rowBinding = RowBinding.inflate(layoutInflater)
            // ViewHolder 객체 생성
            val ViewHolder = ViewHolderClass(rowBinding)

            // recyclerView 한 항목의 가로, 세로 크기 설정 b/c 해당 항목의 끝을 클릭해서도 선택 가능하도록 하기 위함이다.
            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            rowBinding.root.layoutParams = params


            return ViewHolder
        }

        override fun getItemCount(): Int {
            return categoryList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            // recyclerView 항목 : 등록한 카테고리 이름
            holder.textViewCategory.text = categoryList[position]
        }
    }
}
