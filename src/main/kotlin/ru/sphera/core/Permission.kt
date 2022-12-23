package ru.sphera.core


@kotlin.annotation.Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Repeatable
@MustBeDocumented
annotation class Permission(val value: Permission.LVL = Permission.LVL.DENY)
{
    enum class LVL(var lvl : Int) {

        ALL(0),
        EDIT(1),
        READ(2),
        DENY(3);

    }
}
