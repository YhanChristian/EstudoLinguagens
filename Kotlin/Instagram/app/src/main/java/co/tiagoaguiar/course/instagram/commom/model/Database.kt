package co.tiagoaguiar.course.instagram.commom.model

import java.util.UUID

object Database {
    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    var sessionAuth : UserAuth? = null
    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "Yhan Christian", "yhan.christian@live.com", "12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "Camila Costa","camila_braz_costa@outlook.com", "12345678"))
        sessionAuth = usersAuth.first()
    }
}