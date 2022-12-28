package ru.sphera.core.role

import jakarta.inject.Inject
import java.time.OffsetDateTime

class RoleService {
    @Inject
    lateinit var roleRepository: RoleRepository

    @Inject
    lateinit var accessObjectRepository: AccessObjectRepository

    open fun getAll(): Set<RoleResponse> {
        var entityRoles = roleRepository.findAll();
        return entityRoles.map { it.toRoleResponse() }.toSet()
    }

    open fun getById(id: Long): RoleResponse {
        var roleEntity: RoleEntity = roleRepository.findById(id).get();
        return roleEntity.toRoleResponse();
    }

    open fun save(request: RoleRequest): RoleResponse {
        var roleEntity: RoleEntity = request.toRoleEntity();
        roleEntity.createdOn = OffsetDateTime.now();
        if (request.access?.isNotEmpty() == true) {
            roleEntity.access = accessObjectRepository.findByIds(request.access!!);
        }
        this.enrich(roleEntity)
        roleEntity = roleRepository.save(roleEntity);
        return roleEntity.toRoleResponse();
    }

    open fun delete(id: Long): RoleResponse {
        var roleEntity = roleRepository.findById(id).get();
        accessObjectRepository.deleteRelationAccessObjectByRoleId(id);
        roleRepository.deleteRelationRoleByRoleId(id);
        roleRepository.deleteById(id);
        return roleEntity.toRoleResponse();
    }

    open fun update(id: Long, request: RoleRequest): RoleResponse {
        var newRoleEntity: RoleEntity = request.toRoleEntity();

        if (request.access?.isNotEmpty() == true) {
            newRoleEntity.access = accessObjectRepository.findByIds(request.access!!);
        }
        var oldRoleEntity: RoleEntity = roleRepository.findById(id).get();
        accessObjectRepository.deleteRelationAccessObjectByRoleId(id);
        oldRoleEntity.updatedOn = OffsetDateTime.now();
        oldRoleEntity.name = newRoleEntity.name;
        oldRoleEntity.access = newRoleEntity.access;
        this.enrich(oldRoleEntity);
        oldRoleEntity = roleRepository.update(oldRoleEntity);
        return oldRoleEntity.toRoleResponse();
    }
    private fun enrich(role : RoleEntity)
    {
        role.access = role.access?.filter{
            it.name != "SYS_ADMIN"
        }?.toSet()
    }
}