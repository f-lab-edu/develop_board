let currentPage = 1;
const size = 30;

async function loadBoards() {
    const tbody = document.getElementById('boardList');
    tbody.innerHTML = '<tr><td colspan="5" class="empty">불러오는 중...</td></tr>';
    try {
        const sort = document.getElementById('sort').value;
        const boards = await api(`/api/posts?page=${currentPage}&size=${size}&sort=${sort}`);
        document.getElementById('pageNumber').textContent = `${currentPage} 페이지`;
        document.getElementById('prevButton').disabled = currentPage === 1;
        document.getElementById('nextButton').disabled = boards.length < size;
        if (!boards.length) {
            tbody.innerHTML = '<tr><td colspan="5" class="empty">게시글이 없습니다.</td></tr>';
            return;
        }
        tbody.innerHTML = boards.map(board => `
            <tr>
                <td><a class="post-link" href="/posts/${encodeURIComponent(board.uuid)}">${escapeHtml(board.title)}</a></td>
                <td>${escapeHtml(board.writerId)}</td>
                <td>${escapeHtml(board.views)}</td>
                <td>${formatDate(board.updatedAt)}</td>
                <td><span class="tag">${escapeHtml(board.tag)}</span></td>
            </tr>`).join('');
    } catch (error) {
        tbody.innerHTML = '<tr><td colspan="5" class="empty">목록을 불러오지 못했습니다.</td></tr>';
        showMessage(error.message, true);
    }
}

document.getElementById('sort').addEventListener('change', () => { currentPage = 1; loadBoards(); });
document.getElementById('prevButton').addEventListener('click', () => { if (currentPage > 1) { currentPage--; loadBoards(); } });
document.getElementById('nextButton').addEventListener('click', () => { currentPage++; loadBoards(); });
loadBoards();
