package ru.sphera.core.role

import io.micronaut.serde.annotation.Serdeable
import java.time.OffsetDateTime
@Serdeable
data class RoleResponse(
    var id: Long,
    var createdOn: OffsetDateTime,
    var updatedOn: OffsetDateTime?,
    var name: String,
    var access: Set<AccessObjectResponse>?
)