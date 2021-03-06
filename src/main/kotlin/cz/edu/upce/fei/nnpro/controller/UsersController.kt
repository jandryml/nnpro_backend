package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UsersController(
    @Autowired val userService: UserService
) {
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun getAllUsers(): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.getAllUsers())
    }
}
