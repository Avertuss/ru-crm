package ru.sphera.client

import io.micronaut.serde.annotation.Serdeable
import ru.sphera.dictionary.client.ClientStatusDto
import java.time.OffsetDateTime

@Serdeable
data class ClientResponse(val id: Long?,
                          val createdOn: OffsetDateTime?,
                          val updatedOn: OffsetDateTime,
                          var firstName: String,
                          var lastName: String,
                          var phoneCode: String,
                          var phone: String,
                          var email: String,
                          var status: ClientStatusDto
)
{

}