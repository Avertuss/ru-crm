package ru.sphera.core.user

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class PasswordRequest(var oldPassword: String, var newPassword: String)
