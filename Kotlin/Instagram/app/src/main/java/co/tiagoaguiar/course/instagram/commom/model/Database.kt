package co.tiagoaguiar.course.instagram.commom.model

import java.util.UUID

object Database {
    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feeds = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, MutableSet<UserAuth>>()
    var sessionAuth : UserAuth? = null
    init {
        val userA = UserAuth(UUID.randomUUID().toString(), "Yhan Christian", "yhan.christian@live.com", "12345678")
        val userB = UserAuth(UUID.randomUUID().toString(), "Camila Costa","camila_braz_costa@outlook.com", "12345678")
        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        followers[userB.uuid] = hashSetOf()
        posts[userB.uuid] = hashSetOf()
        feeds[userB.uuid] = hashSetOf()

        sessionAuth = usersAuth.first()
    }
}