package ru.sphera.client

import io.micronaut.data.annotation.Id
import io.micronaut.data.exceptions.DataAccessException
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank


@JdbcRepository(dialect = Dialect.H2)
abstract class ClientRepository : PageableRepository<ClientEntity, Long> {

 /*  // abstract fun save(@NotBlank name: String): ClientEntity

//    @Transactional
//    open fun saveWithException(@NotBlank name: String): ClientEntity {
        save(name)
        throw DataAccessException("test exception")
    }

  //  abstract fun update(@Id id: Long, @NotBlank name: String) : Long

  */
}