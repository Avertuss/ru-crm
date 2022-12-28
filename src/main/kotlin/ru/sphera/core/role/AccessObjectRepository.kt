package ru.sphera.core.role

import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.H2)
interface AccessObjectRepository : CrudRepository <AccessObjectEntity, Long> {

    @Query("INSERT INTO ROLE_ACCESS_OBJECT SELECT :roleId, ID FROM ACCESS_OBJECT WHERE ID IN (:accessIds) ")
    fun saveRelationRole(roleId : Long, accessIds : Set<Long>)

    @Query("DELETE FROM ROLE_ACCESS_OBJECT WHERE ROLE_ID = :roleId")
    fun deleteRelationAccessObjectByRoleId(roleId : Long)

    open fun findByIds(ids: Set<Long>):Set<AccessObjectEntity>;
}