package ru.sphera.client

import io.micronaut.data.annotation.Join
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import java.awt.print.Book
import java.util.*


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
     @Join(value = "status", type = Join.Type.FETCH)
    abstract override fun findAll(): List<ClientEntity>

    @Join(value = "status", type = Join.Type.FETCH)
    abstract override fun findAll(pageable: Pageable): Page<ClientEntity>

    @Join(value = "status", type = Join.Type.FETCH)
     abstract override fun findById(id: Long): Optional<ClientEntity>
}