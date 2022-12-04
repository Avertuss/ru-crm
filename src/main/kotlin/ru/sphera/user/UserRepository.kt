package ru.sphera.user

import io.micronaut.data.annotation.Join
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import ru.sphera.client.ClientEntity


@JdbcRepository(dialect = Dialect.H2)
interface UserRepository : CrudRepository<UserEntity, Long> {

    override fun findAll(): List<UserEntity>
}