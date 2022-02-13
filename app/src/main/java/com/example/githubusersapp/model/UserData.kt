package com.example.githubusersapp.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.Expose

@Parcelize
data class UserData(
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String,
    @SerializedName("bio")
    @Expose
    var bio: String?,
    @SerializedName("blog")
    @Expose
    var blog: String,
    @SerializedName("company")
    @Expose
    var company: String?,
    @SerializedName("created_at")
    @Expose
    var createdAt: String,
    @SerializedName("email")
    @Expose
    var email: String?,
    @SerializedName("events_url")
    @Expose
    var eventsUrl: String,
    @SerializedName("followers")
    @Expose
    var followers: Int,
    @SerializedName("followers_url")
    @Expose
    var followersUrl: String,
    @SerializedName("following")
    @Expose
    var following: Int,
    @SerializedName("following_url")
    @Expose
    var followingUrl: String,
    @SerializedName("gists_url")
    @Expose
    var gistsUrl: String,
    @SerializedName("gravatar_id")
    @Expose
    var gravatarId: String,
    @SerializedName("hireable")
    @Expose
    var hireable: String?,
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String,
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("location")
    @Expose
    var location: String,
    @SerializedName("login")
    @Expose
    var login: String,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("node_id")
    @Expose
    var nodeId: String,
    @SerializedName("organizations_url")
    @Expose
    var organizationsUrl: String,
    @SerializedName("public_gists")
    @Expose
    var publicGists: Int,
    @SerializedName("public_repos")
    @Expose
    var publicRepos: Int,
    @SerializedName("received_events_url")
    @Expose
    var receivedEventsUrl: String,
    @SerializedName("repos_url")
    @Expose
    var reposUrl: String,
    @SerializedName("site_admin")
    @Expose
    var siteAdmin: Boolean,
    @SerializedName("starred_url")
    @Expose
    var starredUrl: String,
    @SerializedName("subscriptions_url")
    @Expose
    var subscriptionsUrl: String,
    @SerializedName("twitter_username")
    @Expose
    var twitterUsername: String?,
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String,
    @SerializedName("url")
    @Expose
    var url: String
) : Parcelable