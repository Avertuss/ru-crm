package ru.sphera.client

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ClientDto(val id: Long?, val name: String, var firstName: String, var lastName: String)
