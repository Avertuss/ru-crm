package ru.sphera.core.role

import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller("/role")
@Secured(SecurityRule.IS_AUTHENTICATED)
class RoleController(var service: RoleService) {

    @Get
    @Secured(*["ROLE_R", "ROLE_E", "ROLE_D", "SYS_ADMIN"])
    fun getAll(): Set<RoleResponse> {
        return service.getAll();
    }

    @Get("/{id}")
    @Secured(*["ROLE_R", "ROLE_RE", "ROLE_RED", "SYS_ADMIN"])
    fun getById(id: Long): RoleResponse {
        return service.getById(id);
    }

    @Post
    @Secured(*["ROLE_RE", "ROLE_RED", "SYS_ADMIN"])
    fun save(request: RoleRequest): RoleResponse {
        return service.save(request);
    }

    @Put("/{id}")
    @Secured(*["ROLE_RE", "ROLE_RED", "SYS_ADMIN"])
    fun update(id: Long, request: RoleRequest): RoleResponse {
        return service.update(id, request);
    }

    @Delete("/{id}")
    @Secured(*["ROLE_RED", "SYS_ADMIN"])
    fun delete(id: Long): RoleResponse {
        return service.delete(id);
    }
}