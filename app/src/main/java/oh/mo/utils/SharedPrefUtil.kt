package oh.mo.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import oh.mo.App

object SharedPrefUtil {

    private const val SEARCH_HISTORY = "search_history"
    private const val SEARCH_KEYWORD = "search_keyword"
    private const val SEARCH_HISTORY_MODE = "search_history_mode"

    //검색목록 저장
    fun storeSearchHistoryList(sList: MutableList<String>){
        val sp = App.instance.getSharedPreferences(SEARCH_HISTORY, MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(SEARCH_HISTORY, sList.toString())
        editor.apply()
    }

    //검색 목록 불러오기
    fun getSearchHistoryList() : MutableList<String>{
        val sp = App.instance.getSharedPreferences(SEARCH_HISTORY, Context.MODE_PRIVATE)
        val kListString = sp.getString(SEARCH_KEYWORD, "")!!
        lateinit var kList : ArrayList<String>

        //콤마로 구분된 목록들 다 뜯어서 list로 만들기
        if(kListString.isNotEmpty()){
            kList = kListString.split(",") as ArrayList<String>
        }

        return kList
    }

    //검색 목록 제거
    fun removeSearchHistory(){
        val sp = App.instance.getSharedPreferences(SEARCH_HISTORY, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }
}