package ru.sphera.core.role

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.H2)
interface AccessObjectRepository : CrudRepository <AccessObjectEntity, Long> {

    open fun findByIds(ids: Set<Long>):Set<AccessObjectEntity>;
}