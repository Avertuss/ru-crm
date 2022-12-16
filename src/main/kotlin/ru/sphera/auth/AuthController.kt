package ru.sphera.auth

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.rules.SecurityRule


@Secured(SecurityRule.IS_ANONYMOUS)
class AuthController( var service : AuthService) {

    @Post
    open fun login(credentials: UsernamePasswordCredentials)
    {
            service.login(credentials);
    }
}