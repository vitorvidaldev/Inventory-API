package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.vo.user.ChangePasswordVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserLoginVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserSignupVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserVO
import org.springframework.http.ResponseEntity
import java.util.*

interface UserService {
    fun getUserById(userId: UUID): UserVO
    fun userSignup(userSignupVO: UserSignupVO): UserVO
    fun userLogin(userLoginVO: UserLoginVO): UserVO
    fun changeUserPassword(changePasswordVO: ChangePasswordVO): UserVO
    fun deleteUserById(userId: UUID): ResponseEntity<Void>
}