package oh.mo.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import oh.mo.App

object SharedPrefUtil {

    private const val SEARCH_HISTORY = "search_history"
    private const val SEARCH_KEYWORD = "search_keyword"

    private const val PREF_LOGIN = "pref_login"
    private const val PREF_ACCESS_TOKEN = "pref_access_token"

    private var pref_login = App.instance.getSharedPreferences(PREF_LOGIN, MODE_PRIVATE)
    private var pref_login_editor = pref_login.edit()

    fun setSearchHistoryList(sList: MutableList<String>) {
        val sp = App.instance.getSharedPreferences(SEARCH_HISTORY, MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(SEARCH_KEYWORD, sList.joinToString(","))
        editor.apply()
    }

    fun getSearchHistoryList(): MutableList<String> {
        val sp = App.instance.getSharedPreferences(SEARCH_HISTORY, MODE_PRIVATE)
        val kListString = sp.getString(SEARCH_KEYWORD, "")!!
        var kList = ArrayList<String>()

        if (kListString.isNotEmpty()) {
            kList = kListString.split(",") as ArrayList<String>
        }

        return kList
    }

    fun getAccessToken(): String {
        return pref_login.getString(PREF_ACCESS_TOKEN, "") ?: ""
    }

    fun setAccessToken(token: String) {
        pref_login_editor.putString(PREF_ACCESS_TOKEN, token)
        pref_login_editor.apply()
    }

}