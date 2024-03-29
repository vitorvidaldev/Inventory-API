package dev.vitorvidal.inventory.api.application.service.impl

import dev.vitorvidal.inventory.api.application.service.UserService
import dev.vitorvidal.inventory.api.domain.entity.User
import dev.vitorvidal.inventory.api.domain.repository.UserRepository
import dev.vitorvidal.inventory.api.domain.vo.user.ChangePasswordVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserLoginVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserSignupVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserVO
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.*

@Slf4j
@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun getUserById(userId: UUID): UserVO {
        val userEntity = userRepository.findById(userId)

        if (userEntity.isPresent) {
            return UserVO(
                userEntity.get().userId!!,
                userEntity.get().email!!,
                userEntity.get().creationDate!!
            )
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    }

    override fun userSignup(userSignupVO: UserSignupVO): UserVO {
        val optionalUser = userRepository.findUserEntityByEmail(userSignupVO.email)

        if (optionalUser.isEmpty) {
            val user = User()
            user.email = userSignupVO.email
            user.password = userSignupVO.password
            user.creationDate = LocalDateTime.now()
            user.lastUpdateDate = LocalDateTime.now()
            // TODO use entity manager

            val createdUser = userRepository.save(user)

            return UserVO(
                createdUser.userId!!,
                createdUser.email!!,
                createdUser.creationDate!!
            )
        }
        throw ResponseStatusException(HttpStatus.CONFLICT, "User already exists")
    }

    override fun userLogin(userLoginVO: UserLoginVO): UserVO {
        val optionalUser = userRepository.findUserEntityByEmail(userLoginVO.email)

        if (optionalUser.isPresent &&
            Objects.equals(optionalUser.get().password, userLoginVO.password)
        ) {
            val userEntity = optionalUser.get()
            return UserVO(
                userEntity.userId!!,
                userEntity.email!!,
                userEntity.creationDate!!
            )
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Could not log in")
    }

    override fun changeUserPassword(changePasswordVO: ChangePasswordVO): UserVO {
        val optionalUser = userRepository.findById(changePasswordVO.userId)

        if (optionalUser.isPresent &&
            Objects.equals(optionalUser.get().password, changePasswordVO.oldPassword) &&
            Objects.equals(optionalUser.get().email, changePasswordVO.email)
        ) {
            val userEntity = optionalUser.get()
            userEntity.password = changePasswordVO.newPassword
            userEntity.lastUpdateDate = LocalDateTime.now()

            userRepository.save(userEntity)

            return UserVO(
                userEntity.userId!!,
                userEntity.email!!,
                userEntity.creationDate!!
            )
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    }

    override fun deleteUserById(userId: UUID): ResponseEntity<Void> {
        return try {
            userRepository.deleteById(userId)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: Exception) {
            log.error("Did not find user to delete")
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}