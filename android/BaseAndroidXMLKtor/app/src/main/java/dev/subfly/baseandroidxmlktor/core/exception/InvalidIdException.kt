package dev.subfly.baseandroidxmlktor.core.exception

class InvalidIdException : Exception() {
    override val message: String
        get() = "Id of Coin should not be null"
}
