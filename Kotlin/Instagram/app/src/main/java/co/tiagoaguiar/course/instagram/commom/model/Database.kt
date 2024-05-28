package co.tiagoaguiar.course.instagram.commom.model

import android.net.Uri
import java.io.File
import java.util.UUID
object Database {
    val usersAuth = mutableListOf<UserAuth>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feeds = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, Set<String>>()
    var sessionAuth: UserAuth? = null

    init {
        val userA = UserAuth(
            UUID.randomUUID().toString(),
            "Yhan Christian",
            "yhan.christian@live.com",
            "12345678",
            Uri.fromFile(
                File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2024-05-04-20-31-42-585.jpg")
            )
        )
        val userB = UserAuth(
            UUID.randomUUID().toString(),
            "Camila Costa",
            "camila_braz_costa@outlook.com",
            "12345678",
            Uri.fromFile(
                File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2024-05-04-20-31-42-585.jpg")
            )
        )
        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        followers[userB.uuid] = hashSetOf()
        posts[userB.uuid] = hashSetOf()
        feeds[userB.uuid] = hashSetOf()

        feeds[userA.uuid]?.addAll(
            arrayListOf(
                Post(
                    UUID.randomUUID().toString(),
                    Uri.fromFile(
                        File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2024-05-04-20-31-42-585.jpg")
                    ),
                    "Description 1",
                    System.currentTimeMillis(), userA
                ),
                Post(
                    UUID.randomUUID().toString(),
                    Uri.fromFile(
                        File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2024-05-04-20-31-42-585.jpg")
                    ),
                    "Description 2",
                    System.currentTimeMillis(), userA
                ),
                Post(
                    UUID.randomUUID().toString(),
                    Uri.fromFile(
                        File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2024-05-04-20-31-42-585.jpg")
                    ),
                    "Description 3",
                    System.currentTimeMillis(), userA
                ),
                Post(
                    UUID.randomUUID().toString(),
                    Uri.fromFile(
                        File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2024-05-04-20-31-42-585.jpg")
                    ),
                    "Description 4",
                    System.currentTimeMillis(), userA
                ),
            )
        )
        feeds[userA.uuid]?.toList()?.let {
            feeds[userB.uuid]?.addAll(it)
        }

        for(i in 0..30) {
            val user = UserAuth(UUID.randomUUID().toString(), "User$i", "user$i@gmail.com", "12345678", null)
            usersAuth.add(user)
        }

       sessionAuth = usersAuth.first()
    }
}