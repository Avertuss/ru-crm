package ru.sphera.core.role

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class RoleRequest(var name: String,
                       var access: Set<Long>?)
{
    open fun toRoleEntity():RoleEntity
    {
        return RoleEntity(null,null,null,name,null);
    }
}