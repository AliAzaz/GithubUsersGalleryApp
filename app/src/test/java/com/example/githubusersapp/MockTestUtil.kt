package com.example.githubusersapp

import com.example.githubusersapp.model.*

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

        fun fetchImages(): FetchDataModel {
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
