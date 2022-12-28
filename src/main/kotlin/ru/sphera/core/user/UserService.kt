package ru.sphera.core.user

import jakarta.inject.Inject
import ru.sphera.core.UserManager
import ru.sphera.core.role.RoleEntity
import ru.sphera.core.role.RoleRepository
import java.time.OffsetDateTime
import java.util.*

class UserService {
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var roleRepository: RoleRepository

    @Inject
    lateinit var userManager: UserManager

    fun changePassword(login : String, request : PasswordRequest)
    {
        var user: UserEntity = userRepository.find(login,request.oldPassword).get();
            userRepository.updatePasswordById(user.id!!, request.newPassword)
    }
    fun getAll(): List<UserResponse> {
        return userManager.getAll().values.map { it.toUserResponse() }
    }

    fun getById(id: Long): UserResponse {
        var user: UserEntity = userRepository.findById(id).get();
        return user.toUserResponse();
    }

    fun getByLoginAndPassword(login: String, password: String): Optional<UserEntity> {
        return userRepository.find(login, password);
        /*   var user: Optional<UserEntity> =
           return user.get()*/
    }

    fun delete(id: Long): UserResponse {
        var userEntity = userRepository.findById(id).get()
        userEntity.isEnabled = false;
        userEntity.createdOn = OffsetDateTime.now();
        userEntity = userRepository.update(userEntity);
        return userEntity.toUserResponse();
    }

    fun save(user: UserRequest): UserResponse {
        var userEntity: UserEntity = user.toUserEntity();
        userEntity.createdOn = OffsetDateTime.now();

        var roles: Iterable<RoleEntity> = roleRepository.findAll()
        userEntity.role = roles.filter {
            user.roleIds.contains(it.id)
        }.toSet();
        userEntity = userRepository.save(userEntity)
        roleRepository.saveRelationRole(userEntity.id!!, user.roleIds);
        return userEntity.toUserResponse();
    }

    fun update(id: Long, user: UserRequest): UserResponse {
        var userOld: UserEntity = userRepository.findById(id).get();
        userOld.updatedOn = OffsetDateTime.now();
        userOld.username = user.username;
        userOld.isEnabled = user.isEnabled;
        if (user.roleIds.isNotEmpty()) {
            roleRepository.deleteRelationRoleByUserId(id);
            roleRepository.saveRelationRole(id, user.roleIds);
        }
        var roles: Iterable<RoleEntity> = roleRepository.findAll();
        userOld.role = roles.filter {
            user.roleIds.contains(it.id)
        }.toSet();
        var userNew: UserEntity = userRepository.update(userOld);
        return userNew.toUserResponse();
    }
}