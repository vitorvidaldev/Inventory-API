package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.domain.vo.user.UserSignupVO
import dev.vitorvidal.inventory.api.domain.vo.user.UserVO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest(
    @Autowired
    val restTemplate: TestRestTemplate,
) {

    @Test
    fun createNewUserTest() {
        // given
        val signupForm = UserSignupVO(email = "vitor@gmail.com", "Test123!")

        val request = HttpEntity<UserSignupVO>(signupForm)

        // when
        val response: ResponseEntity<UserVO> =
            restTemplate.exchange(url = "/rest/v1/users/signup", method = HttpMethod.POST, request, UserVO::class.java)

        // then
        assertEquals(HttpStatus.CREATED, response.statusCode)
    }
}