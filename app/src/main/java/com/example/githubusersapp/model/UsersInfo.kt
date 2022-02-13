package com.example.githubusersapp.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.Expose

@Parcelize
data class UsersInfo(
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String,
    @SerializedName("events_url")
    @Expose
    var eventsUrl: String,
    @SerializedName("followers_url")
    @Expose
    var followersUrl: String,
    @SerializedName("following_url")
    @Expose
    var followingUrl: String,
    @SerializedName("gists_url")
    @Expose
    var gistsUrl: String,
    @SerializedName("gravatar_id")
    @Expose
    var gravatarId: String,
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String,
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("login")
    @Expose
    var login: String,
    @SerializedName("node_id")
    @Expose
    var nodeId: String,
    @SerializedName("organizations_url")
    @Expose
    var organizationsUrl: String,
    @SerializedName("received_events_url")
    @Expose
    var receivedEventsUrl: String,
    @SerializedName("repos_url")
    @Expose
    var reposUrl: String,
    @SerializedName("score")
    @Expose
    var score: Double,
    @SerializedName("site_admin")
    @Expose
    var siteAdmin: Boolean,
    @SerializedName("starred_url")
    @Expose
    var starredUrl: String,
    @SerializedName("subscriptions_url")
    @Expose
    var subscriptionsUrl: String,
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("url")
    @Expose
    var url: String
) : Parcelable