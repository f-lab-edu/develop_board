package com._ilkwon.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class DevelopBoardApplication

fun main(args: Array<String>) {
	runApplication<DevelopBoardApplication>(*args)
}