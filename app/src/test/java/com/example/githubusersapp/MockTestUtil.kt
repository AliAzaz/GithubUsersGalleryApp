package com.example.githubusersapp

import com.example.githubusersapp.model.*
import com.example.githubusersapp.model.repo.Owner
import com.example.githubusersapp.model.repo.UserRepoResult
import com.example.githubusersapp.model.repo.UserRepoResultItem

/**
 * @author AliAzazAlam on 2/13/2022.
 */
class MockTestUtil {
    companion object {
        fun createZeroUsers(): UsersResult {
            return UsersResult(
                incompleteResults = false,
                totalCount = 0,
                usersInfo = listOf()
            )
        }

        fun createUsers(): UsersResult {
            return UsersResult(
                incompleteResults = false,
                totalCount = 1,
                usersInfo = createUsersResultsList()
            )
        }

        fun createRepos(): UserRepoResult {
            return UserRepoResult().apply {
                this.addAll(createUserReposList())
            }
        }


        fun createSingleUser():UserData{
            return UserData(
                    login= "alimohamadi17",
                    id= 72912588,
                    nodeId= "MDQ6VXNlcjcyOTEyNTg4",
                    avatarUrl= "https://avatars.githubusercontent.com/u/72912588?v=4",
                    gravatarId= "",
                    url= "https://api.github.com/users/alimohamadi17",
                    htmlUrl= "https://github.com/alimohamadi17",
                    followersUrl= "https://api.github.com/users/alimohamadi17/followers",
                    followingUrl= "https://api.github.com/users/alimohamadi17/following{/other_user}",
                    gistsUrl= "https://api.github.com/users/alimohamadi17/gists{/gist_id}",
                    starredUrl= "https://api.github.com/users/alimohamadi17/starred{/owner}{/repo}",
                    subscriptionsUrl= "https://api.github.com/users/alimohamadi17/subscriptions",
                    organizationsUrl= "https://api.github.com/users/alimohamadi17/orgs",
                    reposUrl= "https://api.github.com/users/alimohamadi17/repos",
                    eventsUrl= "https://api.github.com/users/alimohamadi17/events{/privacy}",
                    receivedEventsUrl= "https://api.github.com/users/alimohamadi17/received_events",
                    type= "User",
                    siteAdmin= false,
                    name= "Ali",
                    company= null,
                    blog= "",
                    location= "",
                    email= null,
                    hireable= null,
                    bio= null,
                    twitterUsername= null,
                    publicRepos= 16,
                    publicGists= 0,
                    followers= 398,
                    following= 702,
                    createdAt= "2020-10-15T08:06:19Z",
                    updatedAt= "2021-09-26T20:14:21Z"
            )
        }

        private fun createUserReposList() = arrayListOf(
            UserRepoResultItem(
                    id= 404312754,
                    nodeId= "MDEwOlJlcG9zaXRvcnk0MDQzMTI3NTQ=",
                    name= "alimohamadi17",
                    fullName= "alimohamadi17/alimohamadi17",
                    private= false,
                    owner= null,
                    htmlUrl= "https://github.com/alimohamadi17/alimohamadi17",
                    description= "Frontend Developer",
                    fork= false,
                    url= "https://api.github.com/repos/alimohamadi17/alimohamadi17",
                    forksUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/forks",
                    keysUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/keys{/key_id}",
                    collaboratorsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/collaborators{/collaborator}",
                    teamsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/teams",
                    hooksUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/hooks",
                    issueEventsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/issues/events{/number}",
                    eventsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/events",
                    assigneesUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/assignees{/user}",
                    branchesUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/branches{/branch}",
                    tagsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/tags",
                    blobsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/git/blobs{/sha}",
                    gitTagsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/git/tags{/sha}",
                    gitRefsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/git/refs{/sha}",
                    treesUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/git/trees{/sha}",
                    statusesUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/statuses/{sha}",
                    languagesUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/languages",
                    stargazersUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/stargazers",
                    contributorsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/contributors",
                    subscribersUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/subscribers",
                    subscriptionUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/subscription",
                    commitsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/commits{/sha}",
                    gitCommitsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/git/commits{/sha}",
                    commentsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/comments{/number}",
                    issueCommentUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/issues/comments{/number}",
                    contentsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/contents/{+path}",
                    compareUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/compare/{base}...{head}",
                    mergesUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/merges",
                    archiveUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/{archive_format}{/ref}",
                    downloadsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/downloads",
                    issuesUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/issues{/number}",
                    pullsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/pulls{/number}",
                    milestonesUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/milestones{/number}",
                    notificationsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/notifications{?since,all,participating}",
                    labelsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/labels{/name}",
                    releasesUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/releases{/id}",
                    deploymentsUrl= "https://api.github.com/repos/alimohamadi17/alimohamadi17/deployments",
                    createdAt= "2021-09-08T10:56:52Z",
                    updatedAt= "2021-11-21T02:52:20Z",
                    pushedAt= "2022-01-27T05:18:40Z",
                    gitUrl= "git://github.com/alimohamadi17/alimohamadi17.git",
                    sshUrl= "git@github.com:alimohamadi17/alimohamadi17.git",
                    cloneUrl= "https://github.com/alimohamadi17/alimohamadi17.git",
                    svnUrl= "https://github.com/alimohamadi17/alimohamadi17",
                    homepage= null,
                    size= 5,
                    stargazersCount= 1,
                    watchersCount= 1,
                    language= null,
                    hasIssues= true,
                    hasProjects= true,
                    hasDownloads= true,
                    hasWiki= true,
                    hasPages= true,
                    forksCount= 0,
                    mirrorUrl= null,
                    archived= false,
                    disabled= false,
                    openIssuesCount= 0,
                    allowForking= true,
                    isTemplate= false,
                    topics= listOf(),
                    visibility= "public",
                    forks= 0,
                    openIssues= 0,
                    watchers= 1,
                    defaultBranch= "main"
            )
        )

        fun fetchUsers(): FetchDataModel {
            return FetchDataModel(
                page = 1,
                usersInfo = createUsersResultsList()
            )
        }

        private fun createUsersResultsList() = listOf(
            UsersInfo(
                login = "alimate",
                id = 696139,
                nodeId = "MDQ6VXNlcjY5NjEzOQ==",
                avatarUrl = "https://avatars.githubusercontent.com/u/696139?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/alimate",
                htmlUrl = "https://github.com/alimate",
                followersUrl = "https://api.github.com/users/alimate/followers",
                followingUrl = "https://api.github.com/users/alimate/following{/other_user}",
                gistsUrl = "https://api.github.com/users/alimate/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/alimate/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/alimate/subscriptions",
                organizationsUrl = "https://api.github.com/users/alimate/orgs",
                reposUrl = "https://api.github.com/users/alimate/repos",
                eventsUrl = "https://api.github.com/users/alimate/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/alimate/received_events",
                type = "User",
                siteAdmin = false,
                score = 1.0
            )
        )
    }
}
