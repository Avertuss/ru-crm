package ru.sphera.user

import io.micronaut.serde.annotation.Serdeable
import java.time.OffsetDateTime
@Serdeable
data class UserRequest(var id: Long?,
                       val username: String,
                       val password: String,
                       var isEnabled: Boolean,
                       val roles: Set<RoleEntity>)
{
    open fun toUserEntity():UserEntity
    {
        return UserEntity(id,null, OffsetDateTime.now(),username,password,isEnabled,roles)
    }

}
