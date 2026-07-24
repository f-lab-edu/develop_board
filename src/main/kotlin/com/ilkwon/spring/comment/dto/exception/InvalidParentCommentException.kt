package com.ilkwon.spring.comment.dto.exception

class InvalidParentCommentException (
    parentId: Long
) :
    RuntimeException("상위 댓글을 찾을 수 없습니다. parentId : $parentId")