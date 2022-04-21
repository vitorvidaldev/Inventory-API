package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.UserEntity
import dev.vitorvidal.inventory.api.domain.repository.UserRepository
import dev.vitorvidal.inventory.api.domain.vo.user.UserLoginVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserSignupVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserVO
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {
    fun getUserById(userId: UUID): UserVO {
        val userEntity = userRepository.findById(userId)

        if (userEntity.isPresent) {
            return UserVO(
                userEntity.get().userId,
                userEntity.get().email,
                userEntity.get().creationDate
            )
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    }

    fun getUserByEmail(email: String): UserVO {
        val userEntity = userRepository.findUserEntityByEmail(email)

        if (userEntity.isPresent) {
            return UserVO(
                userEntity.get().userId,
                userEntity.get().email,
                userEntity.get().creationDate
            )
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    }

    fun userSignup(userSignupVO: UserSignupVO): UserVO {
        val userEntity = UserEntity(userSignupVO.email, userSignupVO.password)
        val createdUser = userRepository.save(userEntity)

        return UserVO(createdUser.userId, createdUser.email, createdUser.creationDate)
    }

    fun userLogin(userLoginVO: UserLoginVO): UserVO {
        TODO("Not yet implemented")
    }

    fun changeUserPassword(userId: UUID): UserVO {
        TODO("Not yet implemented")
    }

    fun deleteUserById(userId: UUID) {
        userRepository.deleteById(userId)
    }
}