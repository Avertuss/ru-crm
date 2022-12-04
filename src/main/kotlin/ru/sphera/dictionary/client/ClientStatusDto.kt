package ru.sphera.dictionary.client

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ClientStatusDto(var id: Long?, var name :String)
{

}
