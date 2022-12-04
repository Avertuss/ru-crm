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
@MappedEntity("USER")
data class UserEntity (
    @field:Id
    @field:GeneratedValue(GeneratedValue.Type.AUTO) var id: Long?,
    @field:GeneratedValue
    @Column(updatable = false)
    var createdOn: OffsetDateTime?,
    @field:GeneratedValue
    var updatedOn: OffsetDateTime?,
    val username: String,
    val password: String,
    var isEnabled: Boolean,
    @Relation(value = Relation.Kind.MANY_TO_MANY, cascade = [Relation.Cascade.PERSIST])
    @JoinTable(
    name = "USER_IN_ROLE",
    joinColumns = [JoinColumn(name = "USER_ID")],
    inverseJoinColumns = [JoinColumn(name = "ROLE_ID")])
    val roles: Set<RoleEntity>
)
{
    open fun toUserResponse():UserResponse
    {
        return UserResponse(id,createdOn,updatedOn,username,password,isEnabled,roles)
    }
}
