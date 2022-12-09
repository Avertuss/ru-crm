package ru.sphera.user

import jakarta.inject.Inject
import ru.sphera.core.UserManager
import java.time.OffsetDateTime
import java.util.*

class UserService {
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var roleRepository: RoleRepository

    @Inject
    lateinit var userManager: UserManager

    fun getAll(): List<UserResponse> {
        /*
        return userRepository.findAll().map {
            it.toUserResponse()
        }*/
        return userManager.getAll().values.map { it.toUserResponse() }
    }

    fun getById(id: Long): UserResponse {
        var user: Optional<UserEntity> = userRepository.findById(id);

        return user.get().toUserResponse();
    }

    fun delete(id: Long) {
        userRepository.deleteById(id);
    }

    fun save(user: UserRequest): UserResponse {
       var userEntity: UserEntity = user.toUserEntity();
       // userEntity =  userManager.save(user);
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
        var userNew: UserEntity = userRepository.save(userOld);
        return userNew.toUserResponse();
    }
}