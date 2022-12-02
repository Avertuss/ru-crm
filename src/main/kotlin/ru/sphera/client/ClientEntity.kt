package ru.sphera.client

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity("client")
data class ClientEntity(
    @field:Id
    @field:GeneratedValue(GeneratedValue.Type.AUTO) var id: Long?,
    val name: String,
    val firsName: String,
    val lastName: String,
);