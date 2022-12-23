package ru.sphera.client

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import io.micronaut.security.rules.SecurityRuleResult
import io.micronaut.web.router.MethodBasedRouteMatch
import io.micronaut.web.router.RouteMatch
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.sphera.core.Permission

@Singleton
class Security : SecurityRule {

   /* @Inject
    lateinit var roleService: r

    */

    override fun check(
        request: HttpRequest<*>?,
        routeMatch: RouteMatch<*>?,
        authentication: Authentication?
    ): Publisher<SecurityRuleResult> {
        if (routeMatch is MethodBasedRouteMatch<*, *>) {
            val methodRoute = routeMatch as MethodBasedRouteMatch<*, *>
            if (methodRoute.hasAnnotation(Permission::class.java)) {
                val optionalValue = methodRoute.getValue(Permission::class.java)
                if (optionalValue.isPresent) {
                   val lvl : Permission.LVL = optionalValue.get().let { Permission.LVL.valueOf(it as String);}
                    return when( lvl)
                    {
                        Permission.LVL.READ -> Mono.just(SecurityRuleResult.ALLOWED);
                        else -> Mono.just(SecurityRuleResult.UNKNOWN);
                    }

                    //  val values = Arrays.asList<String>(*optionalValue.get())
                    //  return compareRoles(values, getRoles(authentication))
                }
            }
        }
        return Flux.just(SecurityRuleResult.UNKNOWN);
    }
}