package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/v1/user")
class UserController(val userService: UserService) {

}