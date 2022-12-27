package ru.sphera.client

import io.micronaut.data.model.Page
import io.micronaut.http.MediaType
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import javax.validation.Valid

@Controller("/client")
@Secured(SecurityRule.IS_AUTHENTICATED)
open class ClientController(var service: ClientService) {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(*["CLIENT_R", "CLIENT_E","CLIENT_D"])
    open fun getAll(@Valid pageable: Pageable): Page<ClientResponse> {
        return service.getAll(pageable);
    }

    @Get("/{id}")
    @Secured(*["CLIENT_R", "CLIENT_E","CLIENT_D"])
    open fun getById(id: Long,  authentication: Authentication): ClientResponse {
        println(authentication);
        return service.getById(id);
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(*["CLIENT_E","CLIENT_D"])
    open fun save(@Body client: ClientRequest): ClientResponse {
        return service.save(client);
    }

    @Secured(*["CLIENT_E","CLIENT_D"])
    @Patch
    open fun update(id: Long, @Body client: ClientRequest):ClientResponse{
        return service.update(id,client);
    }

    @Secured("CLIENT_D")
    @Delete
    open fun delete(id: Long):ClientResponse{
        return service.delete(id);
    }
}