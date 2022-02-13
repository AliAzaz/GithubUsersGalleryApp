package com.example.githubusersapp.model.repo


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.Expose

@Parcelize
data class UserRepoResultItem(
    @SerializedName("allow_forking")
    @Expose
    var allowForking: Boolean,
    @SerializedName("archive_url")
    @Expose
    var archiveUrl: String,
    @SerializedName("archived")
    @Expose
    var archived: Boolean,
    @SerializedName("assignees_url")
    @Expose
    var assigneesUrl: String,
    @SerializedName("blobs_url")
    @Expose
    var blobsUrl: String,
    @SerializedName("branches_url")
    @Expose
    var branchesUrl: String,
    @SerializedName("clone_url")
    @Expose
    var cloneUrl: String,
    @SerializedName("collaborators_url")
    @Expose
    var collaboratorsUrl: String,
    @SerializedName("comments_url")
    @Expose
    var commentsUrl: String,
    @SerializedName("commits_url")
    @Expose
    var commitsUrl: String,
    @SerializedName("compare_url")
    @Expose
    var compareUrl: String,
    @SerializedName("contents_url")
    @Expose
    var contentsUrl: String,
    @SerializedName("contributors_url")
    @Expose
    var contributorsUrl: String,
    @SerializedName("created_at")
    @Expose
    var createdAt: String,
    @SerializedName("default_branch")
    @Expose
    var defaultBranch: String,
    @SerializedName("deployments_url")
    @Expose
    var deploymentsUrl: String,
    @SerializedName("description")
    @Expose
    var description: String?,
    @SerializedName("disabled")
    @Expose
    var disabled: Boolean,
    @SerializedName("downloads_url")
    @Expose
    var downloadsUrl: String,
    @SerializedName("events_url")
    @Expose
    var eventsUrl: String,
    @SerializedName("fork")
    @Expose
    var fork: Boolean,
    @SerializedName("forks")
    @Expose
    var forks: Int,
    @SerializedName("forks_count")
    @Expose
    var forksCount: Int,
    @SerializedName("forks_url")
    @Expose
    var forksUrl: String,
    @SerializedName("full_name")
    @Expose
    var fullName: String,
    @SerializedName("git_commits_url")
    @Expose
    var gitCommitsUrl: String,
    @SerializedName("git_refs_url")
    @Expose
    var gitRefsUrl: String,
    @SerializedName("git_tags_url")
    @Expose
    var gitTagsUrl: String,
    @SerializedName("git_url")
    @Expose
    var gitUrl: String,
    @SerializedName("has_downloads")
    @Expose
    var hasDownloads: Boolean,
    @SerializedName("has_issues")
    @Expose
    var hasIssues: Boolean,
    @SerializedName("has_pages")
    @Expose
    var hasPages: Boolean,
    @SerializedName("has_projects")
    @Expose
    var hasProjects: Boolean,
    @SerializedName("has_wiki")
    @Expose
    var hasWiki: Boolean,
    @SerializedName("homepage")
    @Expose
    var homepage: String?,
    @SerializedName("hooks_url")
    @Expose
    var hooksUrl: String,
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String,
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("is_template")
    @Expose
    var isTemplate: Boolean,
    @SerializedName("issue_comment_url")
    @Expose
    var issueCommentUrl: String,
    @SerializedName("issue_events_url")
    @Expose
    var issueEventsUrl: String,
    @SerializedName("issues_url")
    @Expose
    var issuesUrl: String,
    @SerializedName("keys_url")
    @Expose
    var keysUrl: String,
    @SerializedName("labels_url")
    @Expose
    var labelsUrl: String,
    @SerializedName("language")
    @Expose
    var language: String?,
    @SerializedName("languages_url")
    @Expose
    var languagesUrl: String,
    @SerializedName("merges_url")
    @Expose
    var mergesUrl: String,
    @SerializedName("milestones_url")
    @Expose
    var milestonesUrl: String,
    @SerializedName("mirror_url")
    @Expose
    var mirrorUrl: String?,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("node_id")
    @Expose
    var nodeId: String,
    @SerializedName("notifications_url")
    @Expose
    var notificationsUrl: String,
    @SerializedName("open_issues")
    @Expose
    var openIssues: Int,
    @SerializedName("open_issues_count")
    @Expose
    var openIssuesCount: Int,
    @SerializedName("owner")
    @Expose
    var owner: Owner,
    @SerializedName("private")
    @Expose
    var `private`: Boolean,
    @SerializedName("pulls_url")
    @Expose
    var pullsUrl: String,
    @SerializedName("pushed_at")
    @Expose
    var pushedAt: String,
    @SerializedName("releases_url")
    @Expose
    var releasesUrl: String,
    @SerializedName("size")
    @Expose
    var size: Int,
    @SerializedName("ssh_url")
    @Expose
    var sshUrl: String,
    @SerializedName("stargazers_count")
    @Expose
    var stargazersCount: Int,
    @SerializedName("stargazers_url")
    @Expose
    var stargazersUrl: String,
    @SerializedName("statuses_url")
    @Expose
    var statusesUrl: String,
    @SerializedName("subscribers_url")
    @Expose
    var subscribersUrl: String,
    @SerializedName("subscription_url")
    @Expose
    var subscriptionUrl: String,
    @SerializedName("svn_url")
    @Expose
    var svnUrl: String,
    @SerializedName("tags_url")
    @Expose
    var tagsUrl: String,
    @SerializedName("teams_url")
    @Expose
    var teamsUrl: String,
    @SerializedName("topics")
    @Expose
    var topics: List<String>,
    @SerializedName("trees_url")
    @Expose
    var treesUrl: String,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("visibility")
    @Expose
    var visibility: String,
    @SerializedName("watchers")
    @Expose
    var watchers: Int,
    @SerializedName("watchers_count")
    @Expose
    var watchersCount: Int
) : Parcelable