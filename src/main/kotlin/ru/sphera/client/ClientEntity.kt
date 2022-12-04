package ru.sphera.client

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.ColumnTransformer
import io.micronaut.data.jdbc.annotation.JoinColumn
import io.micronaut.data.jdbc.annotation.JoinTable
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import ru.sphera.core.BaseEntity
import ru.sphera.dictionary.client.ClientStatusEntity
import java.time.OffsetDateTime

@Serdeable
@MappedEntity("client")
data class ClientEntity(
    @field:Id
    @field:GeneratedValue var id: Long?,
    @field:GeneratedValue
    @Column(updatable = false)
    var createdOn: OffsetDateTime?,
    @field:GeneratedValue
    var updatedOn: OffsetDateTime?,
    var firstName: String,
    var lastName: String,
    var phoneCode: String,
    var phone: String,
    var email: String,
    @Relation(Relation.Kind.MANY_TO_ONE)
    var status: ClientStatusEntity
) {
    open fun toClientReponse():ClientResponse
    {
        return ClientResponse(id,createdOn,updatedOn!!, firstName,lastName,phoneCode,phone,email, status.toClientStatusDto())
    }
}