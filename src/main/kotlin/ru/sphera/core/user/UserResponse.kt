package ru.sphera.core.user

import io.micronaut.serde.annotation.Serdeable
import ru.sphera.core.role.RoleEntity
import java.time.OffsetDateTime
@Serdeable
data class UserResponse(
    var id: Long?,
    var createdOn: OffsetDateTime?,
    var updatedOn: OffsetDateTime?,
    val username: String,
    var isEnabled: Boolean,
    val roles: Set<RoleEntity>
)
