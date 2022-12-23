package ru.sphera.user

import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import java.util.*

@JdbcRepository(dialect = Dialect.H2)
interface RoleRepository : CrudRepository<RoleEntity, Long> {

    @Query("INSERT INTO USER_IN_ROLE SELECT :userId, ID FROM ROLE WHERE ID IN (:roleIds) ")
    fun saveRelationRole(userId : Long, roleIds : Set<Long>)

    @Query("DELETE FROM USER_IN_ROLE WHERE USER_ID = :userId")
    fun deleteRelationRoleByUserId(userId : Long)

  //  @Join(value = "access", type = Join.Type.FETCH)
  //  override fun findById(id: Long): Optional<RoleEntity>
}