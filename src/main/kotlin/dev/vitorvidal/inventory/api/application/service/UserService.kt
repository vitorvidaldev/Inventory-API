package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository)