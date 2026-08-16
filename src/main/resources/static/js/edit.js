const uuid = document.body.dataset.boardUuid;
document.getElementById('cancelLink').href = `/posts/${encodeURIComponent(uuid)}`;

async function loadBoard() {
    try {
        const board = await api(`/api/posts/${encodeURIComponent(uuid)}`);
        document.getElementById('title').value = board.title;
        document.getElementById('content').value = board.content;
    } catch (error) {
        showMessage(error.message, true);
    }
}

document.getElementById('editForm').addEventListener('submit', async function (event) {
    event.preventDefault();
    try {
        await api(`/api/posts/${encodeURIComponent(uuid)}`, {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json', 'x-note-account': writerId()},
            body: JSON.stringify({title: document.getElementById('title').value.trim(), content: document.getElementById('content').value.trim()})
        });
        location.href = `/posts/${encodeURIComponent(uuid)}`;
    } catch (error) {
        showMessage(error.message, true);
    }
});
loadBoard();
