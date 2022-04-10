package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.repository.UserRepository
import dev.vitorvidal.inventory.api.domain.vo.user.UserLoginVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserSignupVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserVO
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {
    fun getUserById(userId: UUID): UserVO {
        TODO("Not yet implemented")
    }

    fun userSignup(userSignupVO: UserSignupVO): UserVO {
        TODO("Not yet implemented")
    }

    fun userLogin(userLoginVO: UserLoginVO): UserVO {
        TODO("Not yet implemented")
    }

    fun deleteUserById(userId: UUID) {
        TODO("Not yet implemented")
    }

    fun changeUserPassword(userId: UUID): UserVO {
        TODO("Not yet implemented")
    }
}