package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.UserService
import dev.vitorvidal.inventory.api.domain.vo.user.ChangePasswordVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserLoginVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserSignupVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/rest/v1/users")
class UserController(val userService: UserService) {

    @Operation(summary = "Get user by id")
    @ApiResponse(
        responseCode = "200",
        description = "Retrieve user data"
    )
    @GetMapping("/{userId}")
    fun getUserById(@PathVariable(value = "userId") userId: UUID): ResponseEntity<UserVO> {
        val userVO: UserVO = userService.getUserById(userId)
        return ResponseEntity.ok().body(userVO)
    }

    @Operation(summary = "Create new user")
    @ApiResponse(
        responseCode = "201",
        description = "Register new user"
    )
    @PostMapping("/signup")
    fun userSignup(@RequestBody @Valid userSignupVO: UserSignupVO): ResponseEntity<UserVO> {
        val userVO: UserVO = userService.userSignup(userSignupVO)
        return ResponseEntity.status(HttpStatus.CREATED).body(userVO)
    }

    @Operation(summary = "Login user")
    @PostMapping("/login")
    fun userLogin(@RequestBody @Valid userLoginVO: UserLoginVO): ResponseEntity<UserVO> {
        val userVO: UserVO = userService.userLogin(userLoginVO)
        return ResponseEntity.status(HttpStatus.CREATED).body(userVO)
    }

    @Operation(summary = "Change user password")
    @ApiResponse(
        responseCode = "200",
        description = "Change user password"
    )
    @PutMapping("/{userId}")
    fun changeUserPassword(@RequestBody @Valid changePasswordVO: ChangePasswordVO): ResponseEntity<UserVO> {
        val userVO: UserVO = userService.changeUserPassword(changePasswordVO)
        return ResponseEntity.ok().body(userVO)
    }

    @Operation(summary = "Delete user data")
    @ApiResponse(
        responseCode = "204",
        description = "Delete user data"
    )
    @DeleteMapping("/{userId}")
    fun deleteUserById(@PathVariable(value = "userId") userId: UUID): ResponseEntity<Void> {
        return userService.deleteUserById(userId)
    }
}