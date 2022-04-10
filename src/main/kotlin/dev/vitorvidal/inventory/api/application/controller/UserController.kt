package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.UserService
import dev.vitorvidal.inventory.api.domain.vo.user.UserLoginVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserSignupVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserVO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/rest/v1/user")
class UserController(val userService: UserService) {

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable(value = "userId") userId: UUID): ResponseEntity<UserVO> {
        val userVO: UserVO = userService.getUserById(userId)
        return ResponseEntity.ok().body(userVO)
    }

    @PostMapping("/signup")
    fun userSignup(@RequestBody @Valid userSignupVO: UserSignupVO): ResponseEntity<UserVO> {
        val userVO: UserVO = userService.userSignup(userSignupVO)
        return ResponseEntity.status(HttpStatus.CREATED).body(userVO)
    }

    @PostMapping("/login")
    fun userLogin(@RequestBody @Valid userLoginVO: UserLoginVO): ResponseEntity<UserVO> {
        val userVO: UserVO = userService.userLogin(userLoginVO)
        return ResponseEntity.status(HttpStatus.CREATED).body(userVO)
    }

    @PutMapping("/{userId}")
    fun changeUserPassword(@PathVariable(value = "userId") userId: UUID): ResponseEntity<UserVO> {
        val userVO: UserVO = userService.changeUserPassword(userId)
        return ResponseEntity.ok().body(userVO);
    }

    @DeleteMapping("/{userId}")
    fun deleteUserById(@PathVariable(value = "userId") userId: UUID): ResponseEntity<Void> {
        userService.deleteUserById(userId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}