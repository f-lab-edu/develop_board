package com.ilkwon.spring.comment.dto.exception

class CommentNotFoundException(
    commentId: Long
) :
    RuntimeException("댓글을 찾을 수 없습니다. commentId : $commentId")