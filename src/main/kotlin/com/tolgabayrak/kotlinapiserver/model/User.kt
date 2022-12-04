package com.tolgabayrak.kotlinapiserver.model

import jakarta.persistence.*
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Entity
@Table(name = "tb_users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val email: String,
        val password: String


) {
        fun comparePassword(password: String): Boolean{
                return BCryptPasswordEncoder().matches(password, password)
        }
}


