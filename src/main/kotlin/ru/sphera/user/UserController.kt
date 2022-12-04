package ru.sphera.user

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import jakarta.inject.Inject


@Controller("/user")
@Secured(SecurityRule.IS_ANONYMOUS)
class UserController(var service: UserService) {


    @Get
    open fun getAll(): List<UserResponse> {
        return service.getAll();
    }
    @Post
    open fun save(user: UserRequest): List<UserResponse> {
        return service.getAll();
    }
}