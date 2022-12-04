package ru.sphera.user

import jakarta.inject.Inject
import org.h2.engine.User
import java.time.OffsetDateTime

class UserService {
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var roleRepository: RoleRepository

    fun getAll():List<UserResponse>
    {
        return userRepository.findAll().map{
            it.toUserResponse()
        }
    }
    fun save(user : UserRequest): UserResponse
    {
        var userEntity: UserEntity = user.toUserEntity();
        userEntity.createdOn = OffsetDateTime.now();
        return userRepository.save(userEntity).toUserResponse();
    }
}