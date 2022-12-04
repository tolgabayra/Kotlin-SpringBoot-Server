package com.tolgabayrak.kotlinapiserver.repository

import com.tolgabayrak.kotlinapiserver.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

}