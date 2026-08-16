package com.ilkwon.spring.front.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class PageController {

    @GetMapping("/", "/posts")
    fun boardList(): String = "posts/list"

    @GetMapping("/posts/new")
    fun boardCreate(): String = "posts/form"

    @GetMapping("/posts/{uuid}")
    fun boardDetail(
        @PathVariable uuid: String,
        model: Model
    ): String {
        model.addAttribute("boardUuid", uuid)
        return "posts/detail"
    }

    @GetMapping("/posts/{uuid}/edit")
    fun boardEdit(
        @PathVariable uuid: String,
        model: Model
    ): String {
        model.addAttribute("boardUuid", uuid)
        return "posts/edit"
    }
}
