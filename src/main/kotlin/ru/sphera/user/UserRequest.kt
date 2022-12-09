package ru.sphera.user

import io.micronaut.serde.annotation.Serdeable
import java.time.OffsetDateTime
@Serdeable
data class UserRequest(var username: String,
                       var password: String,
                       var isEnabled: Boolean,
                       var roleIds: Set<Long>)
{
    open fun toUserEntity():UserEntity
    {
        return UserEntity(null,null, OffsetDateTime.now(),username,password,isEnabled,null)
    }

}
