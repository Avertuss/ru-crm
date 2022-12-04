package ru.sphera.user

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.jdbc.annotation.JoinColumn
import io.micronaut.data.jdbc.annotation.JoinTable
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import java.time.OffsetDateTime

@Serdeable
@MappedEntity("ROLE")
data class RoleEntity(
    @field:Id
    @field:GeneratedValue(GeneratedValue.Type.AUTO) var id: Long?,
    @field:GeneratedValue
    @Column(updatable = false)
    var createdOn: OffsetDateTime?,
    @field:GeneratedValue
    var updatedOn: OffsetDateTime?,
    var name: String,
    @JoinTable(
        name = "USER_IN_ROLE",
        joinColumns = [JoinColumn(name = "ROLE_ID")],
        inverseJoinColumns = [JoinColumn(name = "USER_ID")])
    var users : Set<UserEntity>
)
