package ru.sphera.core.role

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import ru.sphera.core.user.UserRequest
import ru.sphera.core.user.UserResponse

@Controller("/role")
@Secured(SecurityRule.IS_AUTHENTICATED)
class RoleController(var service: RoleService) {

    @Get
    @Secured(*["ROLE_R","ROLE_E","ROLE_D"])
    fun getAll(): Set<RoleResponse> {
        return service.getAll();
    }

    @Get("/{id}")
    @Secured(*["ROLE_R","ROLE_E","ROLE_D"])
    fun getById(id: Long): RoleResponse {
        return service.getById(id);
    }
    @Post
    @Secured(*["ROLE_E","ROLE_D"])
    fun save(request: RoleRequest): RoleResponse
    {
        return service.save(request);
    }
    @Delete
    fun delete (id: Long):RoleResponse
    {
        TODO()
    }
}