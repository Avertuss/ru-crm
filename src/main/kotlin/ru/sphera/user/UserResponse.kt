package ru.sphera.user

import io.micronaut.serde.annotation.Serdeable
import java.time.OffsetDateTime
@Serdeable
data class UserResponse(
    var id: Long?,
    var createdOn: OffsetDateTime?,
    var updatedOn: OffsetDateTime?,
    val username: String,
    val password: String,
    var isEnabled: Boolean,
    val roles: Set<RoleEntity>
)
