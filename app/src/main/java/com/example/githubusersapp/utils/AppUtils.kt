package com.example.githubusersapp.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import org.apache.commons.lang3.StringUtils
import java.util.*


fun String.convertStringToUpperCase(): String {
    /*
     * Program that first convert all uper case into lower case then
     * convert fist letter into uppercase
     */
    val calStr = this.split(" ").map { it.toLowerCase(Locale.ENGLISH).capitalize(Locale.ENGLISH) }
    return calStr.joinToString(separator = " ")
}

fun String.shortStringLength(length: Int): String {
    /*
     * Program that first convert all uper case into lower case then
     * convert fist letter into uppercase
     */
    var calStr = this
    if (this.length > length)
        calStr = this.substring(0, length).plus("...")
    return calStr
}