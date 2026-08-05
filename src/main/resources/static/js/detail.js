const uuid = document.body.dataset.boardUuid;
document.getElementById('editLink').href = `/posts/${encodeURIComponent(uuid)}/edit`;

async function loadBoard() {
    try {
        const board = await api(`/api/posts/${encodeURIComponent(uuid)}`);
        document.title = board.title;
        document.getElementById('title').textContent = board.title;
        document.getElementById('content').textContent = board.content;
        document.getElementById('tag').textContent = board.tag;
        document.getElementById('meta').textContent = `작성자 ${board.writerId} · 조회 ${board.views} · ${formatDate(board.updatedAt)}`;
    } catch (error) { showMessage(error.message, true); }
}

function commentHtml(comment, depth) {
    const children = (comment.children || []).map(child => commentHtml(child, depth + 1)).join('');
    return `<div class="comment" style="--depth:${Math.min(depth, 6)}">
        <div class="comment-meta">작성자 ${escapeHtml(comment.writerId)} · ${formatDate(comment.createdAt)}</div>
        <div class="comment-content">${escapeHtml(comment.content)}</div>
        <button class="reply-button" type="button" data-comment-id="${comment.id}">답글</button>
        <div class="reply-slot" id="reply-${comment.id}"></div>
        ${children}
    </div>`;
}

async function loadComments() {
    const container = document.getElementById('comments');
    try {
        const comments = await api(`/api/posts/${encodeURIComponent(uuid)}/comments`);
        container.innerHTML = comments.length ? comments.map(comment => commentHtml(comment, 0)).join('') : '<p class="empty">첫 댓글을 작성해보세요.</p>';
    } catch (error) { container.innerHTML = '<p class="empty">댓글을 불러오지 못했습니다.</p>'; showMessage(error.message, true); }
}

async function createComment(content, parentId) {
    await api(`/api/posts/${encodeURIComponent(uuid)}/comments`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'x-note-account': writerId()},
        body: JSON.stringify({content, parentId})
    });
    await loadComments();
}

document.getElementById('commentForm').addEventListener('submit', async function (event) {
    event.preventDefault();
    const textarea = document.getElementById('commentContent');
    try { await createComment(textarea.value.trim(), null); textarea.value = ''; } catch (error) { showMessage(error.message, true); }
});

document.getElementById('comments').addEventListener('click', function (event) {
    const button = event.target.closest('.reply-button');
    if (!button) return;
    const id = button.dataset.commentId;
    const slot = document.getElementById(`reply-${id}`);
    if (slot.innerHTML) { slot.innerHTML = ''; return; }
    slot.innerHTML = `<form class="reply-form"><textarea rows="2" placeholder="답글을 입력하세요" required></textarea><div class="button-row"><button type="button" class="button cancel-reply">취소</button><button type="submit" class="button primary">답글 등록</button></div></form>`;
    slot.querySelector('.cancel-reply').addEventListener('click', () => slot.innerHTML = '');
    slot.querySelector('form').addEventListener('submit', async e => {
        e.preventDefault();
        try { await createComment(slot.querySelector('textarea').value.trim(), Number(id)); } catch (error) { showMessage(error.message, true); }
    });
});

document.getElementById('deleteButton').addEventListener('click', async function () {
    if (!confirm('게시글을 삭제할까요?')) return;
    try { await api(`/api/posts/${encodeURIComponent(uuid)}`, {
        method: 'DELETE',
        headers: {
            'x-note-account': writerId()
        }
    }); location.href = '/posts'; } catch (error) { showMessage(error.message, true); }
});

loadBoard();
loadComments();