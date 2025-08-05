package com.school.restController

import com.school.usecase.LessonProgressService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/lesson")
class StudentLessonProgressRestController(
    private val lessonProgressService: LessonProgressService
) {

    @PostMapping("/lesson/{lessonId}/student/{studentId}/{newPercentageClass}")
    fun postProgress(
        @PathVariable lessonId: Int,
        @PathVariable studentId: Int,
        @PathVariable newPercentageClass: Int
    ): ResponseEntity<String> {
        lessonProgressService.registerLessonCompletion(studentId, lessonId, newPercentageClass)
        return ResponseEntity.ok("Lesson completion registered")
    }


    //@ResponseStatus(HttpStatus.CREATED)
}