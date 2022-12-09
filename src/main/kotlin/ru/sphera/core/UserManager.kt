package ru.sphera.core

import io.micronaut.data.repository.CrudRepository
import io.micronaut.transaction.SynchronousTransactionManager
import jakarta.inject.Singleton
import jakarta.persistence.Column
import ru.sphera.user.RoleEntity
import ru.sphera.user.UserEntity
import ru.sphera.user.UserRequest
import java.sql.Array
import java.sql.Connection
import java.sql.ResultSet
import java.time.OffsetDateTime
import kotlin.streams.toList

@Singleton

class UserManager(
    private val connection: Connection,
    private val transactionManager: SynchronousTransactionManager<Connection>
)  {
    private val SAVE_USER_IN_ROLE: String =
        "INSERT INTO USER_IN_ROLE(USER_ID,ROLE_ID) SELECT ? AS USER_ID, ID FROM ROLE WHERE ID = ANY(?) ";
    private val SELECT_ROLE_BY_ID: String = "SELECT ID,CREATED_ON,UPDATED_ON,NAME FROM ROLE WHERE ID = ANY(?)";
    private val INSERT_INTO_USER: String =
        "INSERT INTO USER(UPDATED_ON,USERNAME,PASSWORD,IS_ENABLED) VALUES(NULL,?,?,?)";
    private val SELECT_USER: String = "SELECT U.ID AS U_ID, U.CREATED_ON AS U_CREATED_ON, U.UPDATED_ON AS U_UPDATED_ON, "+
            "U.USERNAME AS U_USERNAME, U.PASSWORD AS U_PASSWORD, U.IS_ENABLED AS U_IS_ENABLED, "+
            "R.ID AS R_ID, R.CREATED_ON AS R_CREATED_ON, R.UPDATED_ON AS R_UPDATED_ON, R.NAME AS R_NAME FROM USER U "+
            "LEFT JOIN USER_IN_ROLE UIR ON UIR.USER_ID = U.ID "+
            "LEFT JOIN ROLE R ON R.ID = UIR.ROLE_ID; "
    private fun bindRoleEntity(rs: ResultSet, prefix: String?=""): RoleEntity {
        return RoleEntity(
            id = rs.getLong("${prefix}ID"),
            createdOn = rs.getObject("${prefix}CREATED_ON", OffsetDateTime::class.java),
            updatedOn = rs.getObject("${prefix}UPDATED_ON", OffsetDateTime::class.java),
            name = rs.getString("${prefix}NAME")
        )
    }

    private fun bindUserEntity(rs: ResultSet, prefix: String?=""): UserEntity {


        return UserEntity(
            id = rs.getLong("${prefix}ID"),
            createdOn = rs.getObject("${prefix}CREATED_ON", OffsetDateTime::class.java),
            updatedOn = rs.getObject("${prefix}UPDATED_ON", OffsetDateTime::class.java),
            username = rs.getString("${prefix}USERNAME"),
            password = rs.getString("${prefix}PASSWORD"),
            isEnabled = rs.getBoolean("${prefix}IS_ENABLED"),
            role = HashSet<RoleEntity>()
        )
    }

    private fun saveUserInRole(userId: Long, rolesIds: Set<Long>) {
        val array: Array = connection.createArrayOf("BIGINT", rolesIds.toTypedArray());
        connection.prepareStatement(SAVE_USER_IN_ROLE).use { p ->
            p.setLong(1, userId);
            p.setArray(2, array);
            p.executeUpdate();
        }
    }

    fun getRoleByIDs(iDs: Set<Long>): Set<RoleEntity> {
        return transactionManager.executeRead {
            val array: Array = connection.createArrayOf("BIGINT", iDs.toTypedArray());
            connection.prepareStatement(SELECT_ROLE_BY_ID).use { p ->
                p.setArray(1, array);
                p.executeQuery().use { rs ->
                    var roles = HashSet<RoleEntity>();
                    while (rs.next()) {
                        roles.add(bindRoleEntity(rs));
                    }
                    return@executeRead roles
                }
            }
        }
    }
    fun getAll():Map<Long,UserEntity>
    {
        return transactionManager.executeRead{
           var users: MutableMap<Long,UserEntity> = HashMap<Long,UserEntity>();
            connection.prepareStatement(SELECT_USER).use {
                var rs : ResultSet =  it.executeQuery();
                var roles : HashMap<Long,ArrayList<RoleEntity>> =HashMap();
                while  (rs.next()) {
                    var userId = rs.getLong("U_ID");
                    if(!users.containsKey(userId))
                    {
                        users[userId] = bindUserEntity(rs,"U_");
                        roles[userId] = ArrayList<RoleEntity>();
                    }
                    roles[userId]?.add(bindRoleEntity(rs,"R_"));
                }
                users.values.forEach{ e ->
                    e.role = roles[e.id!!]?.toSet();
                }
            }
           users;
        }
    }
    fun save(user: UserRequest): UserEntity {
        return transactionManager.executeWrite {
            connection.prepareStatement(INSERT_INTO_USER,
                arrayOf("ID", "CREATED_ON", "UPDATED_ON", "USERNAME", "PASSWORD", "IS_ENABLED")
            ).use {
                it.setString(1, user.username);
                it.setString(2, user.password);
                it.setBoolean(3, user.isEnabled);
                it.executeUpdate()
                var rs = it.generatedKeys;
                if (rs.next()) {
                    return@executeWrite bindUserEntity(rs).apply {
                        role = getRoleByIDs(user.roleIds);
                        if (role != null && role!!.isNotEmpty())
                            saveUserInRole(this.id!!, role!!.map {e->e.id}.toSet() as Set<Long>);
                    };
                } else {
                    throw NullPointerException();
                }

            }
        }
        /*  return transactionManager.executeWrite {
               connection.prepareStatement("insert into product (name, manufacturer_id) values (?, ?)").use { ps ->
                   ps.setString(1, name)
                   ps.setLong(2, manufacturer.id!!)
                   ps.execute()
               }
               product
           }*/
    }

}