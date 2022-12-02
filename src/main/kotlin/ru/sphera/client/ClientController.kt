package ru.sphera.client

import io.micronaut.http.MediaType
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import javax.validation.Valid
@Controller("/client")
open class ClientController( var service: ClientService) {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    open fun getAll(@Valid pageable: Pageable):ClientDto
    {
        return ClientDto(null,"","","");
    }
    @Get("/{id}")
    open fun getById(id: Long):ClientDto
    {
        return service.getById(id);
    }
    @Post
    @Produces(MediaType.APPLICATION_JSON)
    open fun save(@Body client : ClientDto): ClientDto
    {
        return service.save(client);
    }
}