package ru.sphera.core.role

import jakarta.inject.Inject
import ru.sphera.client.ClientRepository

class RoleService{
    @Inject
    lateinit var roleRepository: RoleRepository
    @Inject
    lateinit var accessObjectRepository:AccessObjectRepository

    open fun getAll():Set<RoleResponse>
    {
      var entityRoles =  roleRepository.findAll();
      return entityRoles.map { it.toRoleResponse() }.toSet()
    }
    open fun getById(id: Long):RoleResponse
    {
       var roleEntity: RoleEntity = roleRepository.findById(id).get();
       return roleEntity.toRoleResponse();
    }
    open fun save(request: RoleRequest):RoleResponse
    {
        if(request.access?.isNotEmpty() == true)
        {
           var  accessObjectEntity =  accessObjectRepository.findByIds( request.access!!);
        }

        var roleEntity = request.toRoleEntity();
        roleEntity = roleRepository.save(roleEntity);
        return roleEntity.toRoleResponse();
    }
    open fun delete(id: Long):RoleResponse
    {
        var roleEntity = roleRepository.findById(id).get();
        roleRepository.deleteById(id);
        return roleEntity.toRoleResponse();
    }
    open fun update(id: Long):RoleResponse
    {
        TODO()
    }
}