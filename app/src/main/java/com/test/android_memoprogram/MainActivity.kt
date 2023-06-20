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
import com.test.android_memoprogram.databinding.ActivityMainBinding
import com.test.android_memoprogram.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
//    lateinit var categoryLauncher: ActivityResultLauncher<Intent>

    var categoryList = mutableListOf<String>()

    val c1 = ActivityResultContracts.StartActivityForResult()
    val categoryLauncher = registerForActivityResult(c1) {

        // Result Code로 분기
        if(it.resultCode == RESULT_OK){
            val mainCateogryName = it.data?.getStringExtra("categoryName")
            if(mainCateogryName!=null) {
                categoryList.add(mainCateogryName)
                Log.d("lion","${mainCateogryName}")
            }
        }
        else if(it.resultCode == RESULT_CANCELED) {
        }

        val adpater = activityMainBinding.recyclerViewCategory.adapter as RecyclerAdapter
        adpater.notifyDataSetChanged()
    }


    var recyclerPosition: Int = 0
    val c2 = ActivityResultContracts.StartActivityForResult()
    val categoryEditLauncher = registerForActivityResult(c2) {
        when(it.resultCode) {
            RESULT_OK -> {
                var editCategoryName = it.data?.getStringExtra("EditCategoryName")
                if(editCategoryName!=null)
                    categoryList[recyclerPosition] = editCategoryName
                    Log.d("lion","${categoryList[recyclerPosition]}")
            }
            RESULT_CANCELED -> {
            }
        }
        val adpater = activityMainBinding.recyclerViewCategory.adapter as RecyclerAdapter
        adpater.notifyDataSetChanged()
    }

    val c3 = ActivityResultContracts.StartActivityForResult()
    val memoLauncher = registerForActivityResult(c3) {

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            recyclerViewCategory.run {
                adapter = RecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            // view에 context menu 등록
            registerForContextMenu(recyclerViewCategory)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.addCategoryMenu -> {
                val categoryIntent = Intent(this@MainActivity, CategoryActivity::class.java)
                categoryLauncher.launch(categoryIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>(){
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {

            var textViewCategory : TextView

            init {
                textViewCategory = rowBinding.textViewCategoryName
                rowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    menu?.setHeaderTitle("category 메뉴")
                    menuInflater.inflate(R.menu.category_menu, menu)

                    menu[0].setOnMenuItemClickListener {
                        val categoryName = categoryList[adapterPosition]

                        var categoryEditIntent = Intent(this@MainActivity,CategoryEditActivity::class.java)
                        categoryEditIntent.putExtra("beforeCategoryName","$categoryName")
                        categoryEditLauncher.launch(categoryEditIntent)

                        recyclerPosition = adapterPosition
                        Log.d("lion","$recyclerPosition")

                        false
                    }
                    menu[1].setOnMenuItemClickListener {
                        categoryList.removeAt(adapterPosition)

                        val adpater = activityMainBinding.recyclerViewCategory.adapter as RecyclerAdapter
                        adpater.notifyDataSetChanged()

                        false
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val ViewHolder = ViewHolderClass(rowBinding)

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
            holder.textViewCategory.text = categoryList[position]

            holder.itemView.setOnClickListener {
                val memoIntent = Intent(this@MainActivity,MemoActivity::class.java)

                memoIntent.putExtra("category","${categoryList[position]}")

                memoLauncher.launch(memoIntent)
            }

            Log.d("lion","${categoryList[position]}")
        }

    }
}

