package com.test.android_memoprogram

// 정적 멤버로 선언하여 어떠한 activity에서도 사용 가능하도록 하기 위함이다.
class DataClass {

    companion object {
        // memo list
        var memoList = mutableListOf<MemoInfo>()

        // category list
        var categoryList = mutableListOf<String>()

        // 선택된 카테고리의 memo list
        var categoryMemoList = mutableListOf<MemoInfo>()

        var categoryPosition = 0
        var memoPosition = 0
        var category = ""
    }
}

// memo info 관리하는 class
class MemoInfo(var category:String?, var name:String, var content:String)

