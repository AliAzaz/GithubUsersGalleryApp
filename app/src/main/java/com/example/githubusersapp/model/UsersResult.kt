package com.example.githubusersapp.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.Expose

@Parcelize
data class UsersResult(
    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean,
    @SerializedName("items")
    @Expose
    var usersInfo: List<UsersInfo>,
    @SerializedName("total_count")
    @Expose
    var totalCount: Int
) : Parcelable