package ru.sphera.core.role


import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.model.naming.NamingStrategies
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column

@Serdeable

@MappedEntity("ACCESS_OBJECT")
data class AccessObjectEntity(
    @field:Id
    @field:GeneratedValue
    var id: Long?,
    var name :String
)
{
    open fun toAccessObjectResponse():AccessObjectResponse
    {
        return AccessObjectResponse(id!!,name)
    }
}
