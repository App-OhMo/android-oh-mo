package oh.mo.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import oh.mo.App

object SharedPrefUtil {

    private const val SEARCH_HISTORY = "search_history"
    private const val SEARCH_KEYWORD = "search_keyword"

    fun setSearchHistoryList(sList: MutableList<String>){
        val sp = App.instance.getSharedPreferences(SEARCH_HISTORY, MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(SEARCH_KEYWORD, sList.joinToString(","))
        editor.apply()
    }

    fun getSearchHistoryList() : MutableList<String>{
        val sp = App.instance.getSharedPreferences(SEARCH_HISTORY, MODE_PRIVATE)
        val kListString = sp.getString(SEARCH_KEYWORD, "")!!
        var kList = ArrayList<String>()

        if(kListString.isNotEmpty()){
            kList = kListString.split(",") as ArrayList<String>
        }

        return kList
    }

}