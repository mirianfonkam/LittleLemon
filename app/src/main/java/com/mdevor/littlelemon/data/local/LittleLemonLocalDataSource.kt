package com.mdevor.littlelemon.data.local

interface LittleLemonLocalDataSource {
    fun getFirstName(): String
    fun getLastName(): String
    fun getEmail(): String
    fun setFirstName(firstName: String)
    fun setLastName(lastName: String)
    fun setEmail(email: String)
    fun getIsLogged(): Boolean
    fun setIsLogged(isLogged: Boolean)
}