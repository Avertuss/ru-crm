package ru.sphera.client

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import io.micronaut.security.rules.SecurityRuleResult
import io.micronaut.web.router.RouteMatch
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux

@Singleton
class Security: SecurityRule {
    override fun check(
        request: HttpRequest<*>?,
        routeMatch: RouteMatch<*>?,
        authentication: Authentication?
    ): Publisher<SecurityRuleResult> {
        println("request,authentication");

        return Flux.just(SecurityRuleResult.UNKNOWN);
    }
}