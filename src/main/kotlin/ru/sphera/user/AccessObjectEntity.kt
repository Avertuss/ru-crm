package ru.sphera.user


import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.model.naming.NamingStrategies
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column

@Serdeable

@MappedEntity("ACCESS_OBJECT_IN_ROLE_RIGHT")
data class AccessObjectEntity(
    @field:Id
    @field:GeneratedValue
    var id: Long?,

    @MappedEntity("ROLE_ID")
    var role: RoleEntity,
    @Column(name = "CAN_READ")
    var isRead: Boolean,

    @Column(name = "CAN_EDIT")
    var isEdit: Boolean,

    @Column(name = "CAN_DELETE")
    var isDelete: Boolean
)
