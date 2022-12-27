package ru.sphera.auth

import io.micronaut.security.authentication.UsernamePasswordCredentials
import ru.sphera.core.user.UserService

class AuthService(var userService: UserService) {

    open fun login(credentials: UsernamePasswordCredentials) {

    }
}