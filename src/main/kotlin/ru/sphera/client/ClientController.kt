package ru.sphera.client

import io.micronaut.data.model.Page
import io.micronaut.http.MediaType
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import jakarta.inject.Inject
import ru.sphera.core.Permission
import javax.validation.Valid

@Controller("/client")
open class ClientController(var service: ClientService) {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @Permission( value = Permission.LVL.READ)
    open fun getAll(@Valid pageable: Pageable): Page<ClientResponse> {
        return service.getAll(pageable);
    }

    @Get("/{id}")
    @Permission( value = Permission.LVL.READ)
    open fun getById(id: Long,  authentication: Authentication): ClientResponse {
        println(authentication);
        return service.getById(id);
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    @Permission( value = Permission.LVL.EDIT)
    open fun save(@Body client: ClientRequest): ClientResponse {
        return service.save(client);
    }
    @Permission( value = Permission.LVL.EDIT)
    open fun update(id: Long, @Body client: ClientRequest):ClientResponse{

        return service.update(id,client);
    }
}