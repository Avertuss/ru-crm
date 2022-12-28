package ru.sphera.core.role

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.JoinColumn
import io.micronaut.data.jdbc.annotation.JoinTable
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
    @Relation(value = Relation.Kind.MANY_TO_MANY, cascade =[Relation.Cascade.ALL] )
    @JoinTable(
        name = "ROLE_ACCESS_OBJECT",
        joinColumns = [JoinColumn(name = "ROLE_ID")],
        inverseJoinColumns = [JoinColumn(name = "ACCESS_OBJECT_ID")]
    )
    var access: Set<AccessObjectEntity>?
)
{
    open fun toRoleResponse():RoleResponse
    {
        return RoleResponse(id!!,createdOn!!,updatedOn,name, access?.map{ it.toAccessObjectResponse() }?.toSet())
    }
}
