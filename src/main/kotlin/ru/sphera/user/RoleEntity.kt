package ru.sphera.user

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.serde.annotation.Serdeable
import java.time.OffsetDateTime

@Serdeable

@MappedEntity("ROLE")
data class RoleEntity(
    @field:Id
    @field:GeneratedValue
    var id: Long?,
    @field:GeneratedValue
    var createdOn: OffsetDateTime?,
    @field:GeneratedValue
    var updatedOn: OffsetDateTime?,
    var name: String,
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "role")
    var access: Set<AccessObjectEntity>?
)
