document.getElementById('boardForm').addEventListener('submit', async function (event) {
    event.preventDefault();
    const button = event.submitter;
    button.disabled = true;
    try {
        await api('/api/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'x-note-account': writerId(),
                'Idempotency-Key': crypto.randomUUID()
            },
            body: JSON.stringify({
                title: document.getElementById('title').value.trim(),
                content: document.getElementById('content').value.trim()
            })
        });
        location.href = '/posts';
    } catch (error) {
        showMessage(error.message, true);
        button.disabled = false;
    }
});
