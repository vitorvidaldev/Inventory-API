package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.UserEntity
import dev.vitorvidal.inventory.api.domain.repository.UserRepository
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
        val userEntityMock = mock(UserEntity::class.java)
        val emailMock = "test@gmail.com"
        val creationDateMock = LocalDateTime.now()

        `when`(userRepository.findById(userIdMock)).thenReturn(Optional.of(userEntityMock))
        `when`(userEntityMock.userId).thenReturn(userIdMock)
        `when`(userEntityMock.email).thenReturn(emailMock)
        `when`(userEntityMock.creationDate).thenReturn(creationDateMock)

        val userVo = userService.getUserById(userIdMock)

        assertNotNull(userVo)

        verify(userRepository).findById(userIdMock)
        verify(userEntityMock).userId
        verify(userEntityMock).email
        verify(userEntityMock).creationDate
    }

    @Test
    fun shouldThrowNotFoundExceptionGettingUserById() {
        val userIdMock = UUID.randomUUID()

        `when`(userRepository.findById(userIdMock)).thenReturn(Optional.empty())

        val exception = assertThrows(ResponseStatusException::class.java) { userService.getUserById(userIdMock) }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
        assertEquals("User not found", exception.reason)

        verify(userRepository).findById(userIdMock)
    }

    @Test
    fun shouldGetUserByEmailCorrectly() {
        val userIdMock = UUID.randomUUID()
        val userEntityMock = mock(UserEntity::class.java)
        val emailMock = "test@gmail.com"
        val creationDateMock = LocalDateTime.now()

        `when`(userRepository.findUserEntityByEmail(emailMock)).thenReturn(Optional.of(userEntityMock))
        `when`(userEntityMock.userId).thenReturn(userIdMock)
        `when`(userEntityMock.email).thenReturn(emailMock)
        `when`(userEntityMock.creationDate).thenReturn(creationDateMock)

        val userVo = userService.getUserByEmail(emailMock)

        assertNotNull(userVo)

        verify(userRepository).findUserEntityByEmail(emailMock)
        verify(userEntityMock).userId
        verify(userEntityMock).email
        verify(userEntityMock).creationDate
    }

    @Test
    fun shouldThrowNotFoundExceptionGettingUserByEmail() {
        val emailMock = "test@gmail.com"

        `when`(userRepository.findUserEntityByEmail(emailMock)).thenReturn(Optional.empty())

        val exception = assertThrows(ResponseStatusException::class.java) { userService.getUserByEmail(emailMock) }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
        assertEquals("User not found", exception.reason)

        verify(userRepository).findUserEntityByEmail(emailMock)
    }

    @Test
    fun shouldSignupUserCorrectly() {
        val userSignupVOMock: UserSignupVO = mock(UserSignupVO::class.java)
        val userEntityMock: UserEntity = mock(UserEntity::class.java)
        val emailMock = "email mock"
        val passwordMock = "password"
        val userIdMock = UUID.randomUUID()
        val creationDateMock = LocalDateTime.now()

        `when`(userRepository.save(any<UserEntity>())).thenReturn(userEntityMock)
        `when`(userSignupVOMock.email).thenReturn(emailMock)
        `when`(userSignupVOMock.password).thenReturn(passwordMock)

        `when`(userEntityMock.userId).thenReturn(userIdMock)
        `when`(userEntityMock.email).thenReturn(emailMock)
        `when`(userEntityMock.creationDate).thenReturn(creationDateMock)

        val userSignup = userService.userSignup(userSignupVOMock)

        assertNotNull(userSignup)
        assertEquals(emailMock, userSignup.email)
        assertEquals(userIdMock, userSignup.userId)
        assertEquals(creationDateMock, userSignup.creationDate)

        verify(userRepository).save(any<UserEntity>())
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
}