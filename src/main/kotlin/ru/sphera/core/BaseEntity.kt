package ru.sphera.core

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import jakarta.persistence.Column
import java.time.OffsetDateTime

data class BaseEntity(
    @field:Id
    @field:GeneratedValue var id: Long?,
    @field:GeneratedValue
    @Column(updatable = false)
    var createdOn: OffsetDateTime?,
    @field:GeneratedValue
    var updatedOn: OffsetDateTime?,
)
{
    constructor() : this(null,null,null)
}
