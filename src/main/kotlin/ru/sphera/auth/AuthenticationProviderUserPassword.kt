package ru.sphera.auth

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import ru.sphera.user.UserService

@Singleton
class AuthenticationProviderUserPassword : AuthenticationProvider {

    @Inject
    lateinit var userService: UserService
    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>
    ): Publisher<AuthenticationResponse> {
        return Flux.create({ emitter: FluxSink<AuthenticationResponse> ->
            var password = authenticationRequest.secret as String
            var optionUserEntity = userService.getByLoginAndPassword(authenticationRequest.identity as String, password)
            if (optionUserEntity.isEmpty) {
                emitter.error(AuthenticationResponse.exception())
            } else {
                var userEntity = optionUserEntity.get();
                var roles = userEntity.role?.map { it.name };
                emitter.next(AuthenticationResponse.success(authenticationRequest.identity as String, roles))
                emitter.complete()
            }
        }, FluxSink.OverflowStrategy.ERROR)
    }
}