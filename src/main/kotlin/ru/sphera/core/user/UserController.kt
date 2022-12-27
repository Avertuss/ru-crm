package ru.sphera.core.user

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule


@Controller("/user")
@Secured(SecurityRule.IS_AUTHENTICATED)
class UserController(var service: UserService) {


    @Get
    open fun getAll(): List<UserResponse> {
        return service.getAll();
    }

    @Post
    open fun save(user: UserRequest): UserResponse {
        return service.save(user);
    }

    @Put("/{id}")
    open fun update(id: Long, user: UserRequest): UserResponse {
        return service.update(id, user);
    }
}