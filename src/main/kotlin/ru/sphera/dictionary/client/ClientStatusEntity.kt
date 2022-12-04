package ru.sphera.dictionary.client

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable


@Serdeable
@MappedEntity("client_status")
data class ClientStatusEntity(
    @field:Id
    @field:GeneratedValue(GeneratedValue.Type.AUTO) var id: Long?, var name: String
) {
    open fun toClientStatusDto(): ClientStatusDto {
        return ClientStatusDto(id, name);
    }
}