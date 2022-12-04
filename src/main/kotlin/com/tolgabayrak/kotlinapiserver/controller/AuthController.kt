package com.tolgabayrak.kotlinapiserver.controller

import com.tolgabayrak.kotlinapiserver.dto.Message
import com.tolgabayrak.kotlinapiserver.model.User
import com.tolgabayrak.kotlinapiserver.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.tomcat.util.http.parser.Cookie
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RequestMapping("api/v1/auth")
@RestController
class AuthController(private val userRepository: UserRepository) {


     @PostMapping("/register")
     fun register(@RequestBody body: User): ResponseEntity<User> {
         val newUser = User(
                 id = 0,
                 email = body.email,
                 password = body.password
         )
         return ResponseEntity(userRepository.save(newUser), HttpStatus.CREATED)

     }

    @PostMapping("/login")
    fun login(@RequestBody body: User): ResponseEntity<Message> {
        val user = this.userRepository.findByEmail(body.email) ?: return ResponseEntity.badRequest().body(Message("Email Not Found"))

        if(!user.comparePassword(body.password)){
            return ResponseEntity.badRequest().body(Message("Invalid password"))
        }
        val issuer = user.id.toString()

        val jwt = Jwts.builder()
                .setIssuer(issuer)
                .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
                .signWith(SignatureAlgorithm.HS256, "SecretKey").compact()

        val cookie = jakarta.servlet.http.Cookie("access_token", jwt)
        cookie.isHttpOnly = true

        return ResponseEntity.ok(Message("User login is Successfully"))
    }


}