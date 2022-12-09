package ru.sphera.user

import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.jdbc.annotation.JoinColumn
import io.micronaut.data.jdbc.annotation.JoinTable
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import ru.sphera.client.ClientEntity
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotNull


@JdbcRepository(dialect = Dialect.H2)
interface UserRepository : CrudRepository<UserEntity, Long> {
    @Join(value = "role", type = Join.Type.FETCH)
    override fun findAll(): List<UserEntity>

    @Join(value = "role", type = Join.Type.FETCH)
    override fun findById(id: Long): Optional<UserEntity>
}