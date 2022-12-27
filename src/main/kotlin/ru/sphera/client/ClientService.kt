package ru.sphera.client

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.inject.Inject
import ru.sphera.dictionary.client.ClientStatusRepository
import java.time.OffsetDateTime

class ClientService {
    @Inject
    lateinit var clientRepository: ClientRepository
    @Inject
    lateinit var clientStatusRepository: ClientStatusRepository

    open fun getAll(pageable: Pageable): Page<ClientResponse>
    {
       var page: Page<ClientEntity>  = clientRepository.findAll(pageable);
       return page.map{it.toClientReponse()};
    }
    open fun getById(id: Long):ClientResponse
    {
       var entity: ClientEntity =  clientRepository.findById(id).get();
        return entity.toClientReponse();
    }
    open fun save(clientDto: ClientRequest):ClientResponse
    {
        var entityStatus = clientStatusRepository.findById(clientDto.status)
        var entity: ClientEntity =  clientDto.toClientEntity(entityStatus.get());
        entity = clientRepository.save(entity);
        return entity.toClientReponse()
    }
    open fun delete(id:Long):ClientResponse
    {
        var clientEntity: ClientEntity =  clientRepository.findById(id).get();
        clientRepository.deleteById(id);
        return clientEntity.toClientReponse();
    }
    open fun update(id:Long,clientDto: ClientRequest):ClientResponse
    {
        var entityStatus = clientStatusRepository.findById(clientDto.status)
        var entityClientNew: ClientEntity =  clientDto.toClientEntity(entityStatus.get());
        var entityClientOld: ClientEntity =  clientRepository.findById(id).get();
        entityClientOld.firstName = entityClientNew.firstName;
        entityClientOld.lastName = entityClientNew.lastName
        entityClientOld.status  = entityClientNew.status
        entityClientOld.email = entityClientNew.email
        entityClientOld.phone = entityClientNew.phone
        entityClientOld.phoneCode = entityClientNew.phoneCode
        entityClientOld.updatedOn = OffsetDateTime.now()
        return clientRepository.update(entityClientOld).toClientReponse()
    }
}