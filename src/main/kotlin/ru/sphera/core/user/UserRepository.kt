package ru.sphera.core.user

import io.micronaut.data.annotation.Join
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import java.util.*


@JdbcRepository(dialect = Dialect.H2)
interface UserRepository : CrudRepository<UserEntity, Long> {
    @Join(value = "role", type = Join.Type.FETCH)
    override fun findAll(): List<UserEntity>

    @Join(value = "role", type = Join.Type.FETCH)
    override fun findById(id: Long): Optional<UserEntity>

    @Join(value = "role", type = Join.Type.FETCH)
    @Join(value = "role.access", type = Join.Type.FETCH)
    fun find(username: String, password: String): Optional<UserEntity>
}