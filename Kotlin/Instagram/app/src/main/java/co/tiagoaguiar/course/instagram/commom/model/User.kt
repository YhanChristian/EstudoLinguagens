package co.tiagoaguiar.course.instagram.commom.model

import android.net.Uri

data class UserAuth(
    val uuid: String,
    val name: String,
    val email: String,
    val password: String,
    val photoUri: Uri?,
    val postCount : Int = 0,
    val followingCount : Int = 0,
    val followerCount : Int = 0
)
