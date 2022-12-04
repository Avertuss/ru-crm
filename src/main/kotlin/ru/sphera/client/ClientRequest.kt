package ru.sphera.client

import io.micronaut.serde.annotation.Serdeable
import ru.sphera.dictionary.client.ClientStatusEntity
import java.time.OffsetDateTime

@Serdeable
data class ClientRequest(
    var id: Long?,
    var firstName: String,
    var lastName: String,
    var phoneCode: String,
    var phone: String,
    var email: String,
    var status: Long
) {
    open fun toClientEntity(st: ClientStatusEntity): ClientEntity {
        return ClientEntity(id, null, OffsetDateTime.now(), firstName, lastName, phoneCode, phone, email, st);
    }
}
