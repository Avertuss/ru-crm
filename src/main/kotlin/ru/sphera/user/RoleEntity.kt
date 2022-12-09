package ru.sphera.user

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
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
  //  @Relation(value = Relation.Kind.MANY_TO_MANY, mappedBy = "role")
  //  var user : Set<UserEntity>
)
