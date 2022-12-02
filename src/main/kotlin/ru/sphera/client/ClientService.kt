package ru.sphera.client

import jakarta.inject.Inject

class ClientService {
    @Inject
    lateinit var clientRepository: ClientRepository

    open fun getById(id: Long):ClientDto
    {
       var entity: ClientEntity =  clientRepository.findById(id).get();
        return ClientDto(entity.id,entity.name,entity.firsName,entity.lastName);
    }
    open fun save(clientDto: ClientDto):ClientDto
    {
        var entity: ClientEntity =  ClientEntity(null,clientDto.name,clientDto.firstName,clientDto.lastName);
        entity = clientRepository.save(entity);
        return ClientDto(entity.id,entity.name,entity.firsName,entity.lastName);
    }
}