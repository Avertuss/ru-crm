package ru.sphera.core.role

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class AccessObjectResponse (   var id: Long,
                                    var name :String)