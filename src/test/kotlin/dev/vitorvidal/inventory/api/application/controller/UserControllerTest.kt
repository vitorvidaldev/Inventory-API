package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.UserService
import dev.vitorvidal.inventory.api.domain.vo.user.ChangePasswordVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserLoginVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserSignupVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserVO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
internal class UserControllerTest {
    @InjectMocks
    lateinit var userController: UserController

    @Mock
    lateinit var userService: UserService

    @Test
    fun shouldGetUserByIdCorrectly() {
        val userIdMock: UUID = UUID.randomUUID()
        val userVOMock: UserVO = mock(UserVO::class.java)

        `when`(userService.getUserById(userIdMock)).thenReturn(userVOMock)

        val response = userController.getUserById(userIdMock)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(userVOMock, response.body)

        verify(userService).getUserById(userIdMock)
    }

    @Test
    fun shouldSignupUserCorrectly() {
        val userSignupVOMock: UserSignupVO = mock(UserSignupVO::class.java)
        val userVO: UserVO = mock(UserVO::class.java)

        `when`(userService.userSignup(userSignupVOMock)).thenReturn(userVO)

        val response = userController.userSignup(userSignupVOMock)

        assertNotNull(response)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(userVO, response.body)

        verify(userService).userSignup(userSignupVOMock)
    }

    @Test
    fun shouldUserLoginCorrectly() {
        val userLoginVO: UserLoginVO = mock(UserLoginVO::class.java)
        val userVO: UserVO = mock(UserVO::class.java)

        `when`(userService.userLogin(userLoginVO)).thenReturn(userVO)

        val response = userController.userLogin(userLoginVO)

        assertNotNull(response)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(userVO, response.body)

        verify(userService).userLogin(userLoginVO)
    }

    @Test
    fun shouldChangeUserPasswordCorrectly() {
        val userVOMock: UserVO = mock(UserVO::class.java)
        val changePasswordVOMock: ChangePasswordVO = mock(ChangePasswordVO::class.java)

        `when`(userService.changeUserPassword(changePasswordVOMock)).thenReturn(userVOMock)

        val response = userController.changeUserPassword(changePasswordVOMock)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(userVOMock, response.body)

        verify(userService).changeUserPassword(changePasswordVOMock)
    }

    @Test
    fun shouldDeleteUserByIdCorrectly() {
        val userIdMock: UUID = UUID.randomUUID()

        `when`(userService.deleteUserById(userIdMock)).thenReturn(ResponseEntity.noContent().build())

        val response = userController.deleteUserById(userIdMock)

        assertNotNull(response)
        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        assertNull(response.body)

        verify(userService).deleteUserById(userIdMock)
    }

    @Test
    fun shouldDeleteUserByIdMultipleTimesCorrectly() {
        val userIdMock: UUID = UUID.randomUUID()

        `when`(userService.deleteUserById(userIdMock)).thenReturn(ResponseEntity.notFound().build())

        val response = userController.deleteUserById(userIdMock)

        assertNotNull(response)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)

        verify(userService).deleteUserById(userIdMock)
    }
}