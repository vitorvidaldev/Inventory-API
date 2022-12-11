package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.User
import dev.vitorvidal.inventory.api.domain.repository.UserRepository
import dev.vitorvidal.inventory.api.domain.vo.user.ChangePasswordVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserLoginVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserSignupVO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.*

@ExtendWith(SpringExtension::class)
internal class UserServiceTest {
    @InjectMocks
    lateinit var userService: UserService

    @Mock
    lateinit var userRepository: UserRepository

    @Test
    fun shouldGetUserByIdCorrectly() {
        val userIdMock = UUID.randomUUID()
        val userMock = mock(User::class.java)
        val emailMock = "test@gmail.com"
        val creationDateMock = LocalDateTime.now()

        `when`(userRepository.findById(userIdMock)).thenReturn(Optional.of(userMock))
        `when`(userMock.userId).thenReturn(userIdMock)
        `when`(userMock.email).thenReturn(emailMock)
        `when`(userMock.creationDate).thenReturn(creationDateMock)

        val userVo = userService.getUserById(userIdMock)

        assertNotNull(userVo)

        verify(userRepository).findById(userIdMock)
        verify(userMock).userId
        verify(userMock).email
        verify(userMock).creationDate
    }

    @Test
    fun shouldThrowNotFoundExceptionGettingUserById() {
        val userIdMock = UUID.randomUUID()

        `when`(userRepository.findById(userIdMock)).thenReturn(Optional.empty())

        val exception = assertThrows(ResponseStatusException::class.java) { userService.getUserById(userIdMock) }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("User not found", exception.reason)

        verify(userRepository).findById(userIdMock)
    }

    @Test
    fun shouldSignupUserCorrectly() {
        val userSignupVOMock: UserSignupVO = mock(UserSignupVO::class.java)
        val userMock: User = mock(User::class.java)
        val emailMock = "email mock"
        val passwordMock = "password"
        val userIdMock = UUID.randomUUID()
        val creationDateMock = LocalDateTime.now()

        `when`(userRepository.save(any<User>())).thenReturn(userMock)
        `when`(userSignupVOMock.email).thenReturn(emailMock)
        `when`(userSignupVOMock.password).thenReturn(passwordMock)

        `when`(userMock.userId).thenReturn(userIdMock)
        `when`(userMock.email).thenReturn(emailMock)
        `when`(userMock.creationDate).thenReturn(creationDateMock)

        val userSignup = userService.userSignup(userSignupVOMock)

        assertNotNull(userSignup)
        assertEquals(emailMock, userSignup.email)
        assertEquals(userIdMock, userSignup.userId)
        assertEquals(creationDateMock, userSignup.creationDate)

        verify(userRepository).save(any<User>())
    }

    @Test
    fun shouldThrowConflictExceptionDuringUserSignup() {
        val userSignupVOMock: UserSignupVO = mock(UserSignupVO::class.java)
        val emailMock = "email mock"
        val userMock: User = mock(User::class.java)

        `when`(userSignupVOMock.email).thenReturn(emailMock)
        `when`(userRepository.findUserEntityByEmail(emailMock)).thenReturn(Optional.of(userMock))

        val exception = assertThrows(ResponseStatusException::class.java) {
            userService.userSignup(userSignupVOMock)
        }

        assertNotNull(exception)
        assertEquals(HttpStatus.CONFLICT, exception.statusCode)
        assertEquals("User already exists", exception.reason)

        verify(userRepository).findUserEntityByEmail(emailMock)
    }

    @Test
    fun shouldDeleteUserCorrectly() {
        val userIdMock = UUID.randomUUID()

        doNothing().`when`(userRepository).deleteById(userIdMock)

        userService.deleteUserById(userIdMock)

        verify(userRepository).deleteById(userIdMock)
    }

    @Test
    fun shouldDeleteUserMultipleTimesCorrectly() {
        val userIdMock = UUID.randomUUID()

        doThrow(EmptyResultDataAccessException::class.java).`when`(userRepository).deleteById(userIdMock)

        userService.deleteUserById(userIdMock)

        verify(userRepository).deleteById(userIdMock)
    }

    @Test
    fun shouldLoginUserCorrectly() {
        val userLoginVOMock: UserLoginVO = mock(UserLoginVO::class.java)
        val userMock: User = mock(User::class.java)

        val emailMock = "email"
        val passwordMock = "password"
        val userIdMock = UUID.randomUUID()
        val creationDateMock = LocalDateTime.now()

        `when`(userRepository.findUserEntityByEmail(emailMock)).thenReturn(Optional.of(userMock))
        `when`(userLoginVOMock.email).thenReturn(emailMock)
        `when`(userLoginVOMock.password).thenReturn(passwordMock)

        `when`(userMock.userId).thenReturn(userIdMock)
        `when`(userMock.email).thenReturn(emailMock)
        `when`(userMock.password).thenReturn(passwordMock)
        `when`(userMock.creationDate).thenReturn(creationDateMock)

        val userLogin = userService.userLogin(userLoginVOMock)

        assertNotNull(userLogin)
        assertEquals(emailMock, userLogin.email)

        verify(userRepository).findUserEntityByEmail(emailMock)
    }

    @Test
    fun shouldThrowNotFoundExceptionDuringUserLogin() {
        val userLoginVOMock: UserLoginVO = mock(UserLoginVO::class.java)
        val emailMock = "email"

        `when`(userRepository.findUserEntityByEmail(emailMock)).thenReturn(Optional.empty())
        `when`(userLoginVOMock.email).thenReturn(emailMock)

        val exception = assertThrows(ResponseStatusException::class.java) {
            userService.userLogin(userLoginVOMock)
        }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("Could not log in", exception.reason)

        verify(userRepository).findUserEntityByEmail(emailMock)
    }

    @Test
    fun shouldChangeUserPasswordCorrectly() {
        val changePasswordVOMock: ChangePasswordVO = mock(ChangePasswordVO::class.java)
        val userMock: User = mock(User::class.java)
        val userIdMock = UUID.randomUUID()
        val emailMock = "email"
        val passwordMock = "password"
        val creationDateMock = LocalDateTime.now()

        `when`(userRepository.findById(userIdMock)).thenReturn(Optional.of(userMock))

        `when`(changePasswordVOMock.userId).thenReturn(userIdMock)
        `when`(changePasswordVOMock.email).thenReturn(emailMock)
        `when`(changePasswordVOMock.oldPassword).thenReturn(passwordMock)
        `when`(changePasswordVOMock.newPassword).thenReturn(passwordMock)

        `when`(userMock.userId).thenReturn(userIdMock)
        `when`(userMock.email).thenReturn(emailMock)
        `when`(userMock.password).thenReturn(passwordMock)
        `when`(userMock.lastUpdateDate).thenReturn(creationDateMock)
        `when`(userMock.creationDate).thenReturn(creationDateMock)

        val changeUserPassword = userService.changeUserPassword(changePasswordVOMock)

        assertNotNull(changePasswordVOMock)
        assertEquals(userIdMock, changeUserPassword.userId)
        assertEquals(emailMock, changeUserPassword.email)
        assertEquals(creationDateMock, changeUserPassword.creationDate)

        verify(userRepository).findById(userIdMock)
        verify(userRepository).save(userMock)
    }

    @Test
    fun shouldThrowNotFoundExceptionChnagingPassword() {
        val changePasswordVOMock: ChangePasswordVO = mock(ChangePasswordVO::class.java)
        val userIdMock = UUID.randomUUID()

        `when`(userRepository.findById(userIdMock)).thenReturn(Optional.empty())

        `when`(changePasswordVOMock.userId).thenReturn(userIdMock)

        val exception = assertThrows(ResponseStatusException::class.java) {
            userService.changeUserPassword(changePasswordVOMock)
        }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("User not found", exception.reason)

        verify(userRepository).findById(userIdMock)
    }
}