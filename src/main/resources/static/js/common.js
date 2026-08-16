(function () {
    const writerInput = document.getElementById('writerId');
    if (writerInput) {
        writerInput.value = localStorage.getItem('writerId') || '1';
        writerInput.addEventListener('change', function () {
            localStorage.setItem('writerId', writerInput.value || '1');
        });
    }
})();

function writerId() {
    const input = document.getElementById('writerId');
    return input && input.value ? input.value : '1';
}

function escapeHtml(value) {
    return String(value ?? '')
        .replaceAll('&', '&amp;').replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;').replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}

function formatDate(value) {
    if (!value) return '-';
    return new Date(value).toLocaleString('ko-KR');
}

function showMessage(text, error) {
    const element = document.getElementById('message');
    if (!element) return;
    element.textContent = text;
    element.className = 'message ' + (error ? 'error' : 'success');
}

async function api(url, options) {
    const response = await fetch(url, options);
    const contentType = response.headers.get('content-type') || '';
    const body = contentType.includes('application/json') ? await response.json() : await response.text();
    if (!response.ok) {
        const message = body && typeof body === 'object' ? (body.message || JSON.stringify(body)) : body;
        throw new Error(message || '요청 처리에 실패했습니다.');
    }
    return body;
}
