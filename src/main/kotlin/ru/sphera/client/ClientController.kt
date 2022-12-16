package ru.sphera.client

import io.micronaut.data.model.Page
import io.micronaut.http.MediaType
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import javax.validation.Valid

@Controller("/client")
@Secured(SecurityRule.IS_AUTHENTICATED)
open class ClientController(var service: ClientService) {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    open fun getAll(@Valid pageable: Pageable): Page<ClientResponse> {
        return service.getAll(pageable);
    }

    @Get("/{id}")
    open fun getById(id: Long): ClientResponse {
        return service.getById(id);
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)

    open fun save(@Body client: ClientRequest): ClientResponse {
        return service.save(client);
    }
    @Put("/{id}")
    open fun update(id: Long, @Body client: ClientRequest):ClientResponse{

        return service.update(id,client);
    }
}