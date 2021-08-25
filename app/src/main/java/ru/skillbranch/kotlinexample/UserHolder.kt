package ru.skillbranch.kotlinexample

import androidx.annotation.VisibleForTesting

object UserHolder {
    private val map = mutableMapOf<String, User>()

    fun registerUser(
        fullName: String,
        email: String,
        password: String
    ): User = User.makeUser(fullName, email = email, password = password)
        .let { user ->
            if (map.contains(user.login))
                throw IllegalArgumentException("A user with this email already exists")
            map[user.login] = user
            user
        }

    fun loginUser(login: String, password: String): String? =
        map[login.trim()]?.let {
            if (it.checkPassword(password) || it.accessCode == password) it.userInfo
            else null
        } ?: map[login.replace("""[^+\d]""".toRegex(), "")]?.let {
            if (it.checkPassword(password) || it.accessCode == password) it.userInfo
            else null
        }

    fun registerUserByPhone(fullName: String, phone: String): User =
        User.makeUser(fullName, phone = phone).let { user ->
            if (map.contains(user.login))
                throw IllegalArgumentException("A user with this phone already exists")
            if (!user.phone?.matches("""^([+])([0-9]{11})$""".toRegex())!!)
                throw IllegalArgumentException("Enter a valid phone number starting with a + and containing 11 digits")
            map[user.login] = user
            user
        }


    fun requestAccessCode(login: String) {
        map[login.replace("""[^+\d]""".toRegex(), "")]?.let { user ->
            val newCode = user.generateAccessCode()
            user.changePassword(user.accessCode!!, newCode)
            user.accessCode = newCode
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }
}
