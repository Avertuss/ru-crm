package ru.sphera.core.user

import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.rules.SecurityRule


@Controller("/user")
@Secured(SecurityRule.IS_AUTHENTICATED)
class UserController(var service: UserService) {


    @Post("/password")
    open fun changePassword(@Body request : PasswordRequest,  authentication: Authentication) {

        return service.changePassword(authentication.name,request );
    }
    @Get
    @Secured(*["USER_R","USER_RED","USER_RE","SYS_ADMIN"])
    open fun getAll(): List<UserResponse> {
        return service.getAll();
    }

    @Post
    @Secured(*["USER_RE","USER_RED","SYS_ADMIN"])
    open fun save(user: UserRequest): UserResponse {
        return service.save(user);
    }

    @Put("/{id}")
    @Secured(*["USER_RE","USER_RED","SYS_ADMIN"])
    open fun update(id: Long, user: UserRequest): UserResponse {
        return service.update(id, user);
    }
    @Delete("/{id}")
    @Secured(*["USER_RED","SYS_ADMIN"])
    open fun delete(id: Long): UserResponse {
        return service.delete(id);
    }
}