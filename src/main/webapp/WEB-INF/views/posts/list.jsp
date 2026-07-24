<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Develop Board</title>
    <link rel="stylesheet" href="/css/board.css">
</head>
<body>
<header class="site-header">
    <div class="container header-inner">
        <a class="brand" href="/posts"><span class="brand-mark">D</span><span class="brand-text">Develop Board</span></a>
        <div class="header-actions">
            <label class="writer-label">작성자 ID <input id="writerId" type="number" min="1" value="1"></label>
            <a class="button primary" href="/posts/new">＋ 글쓰기</a>
        </div>
    </div>
</header>
<main class="container page-space">
    <section class="hero">
        <div class="hero-content">
            <span class="eyebrow">Developer Community</span>
            <h1>배우고, 기록하고,<br>함께 성장하는 공간</h1>
            <p>Spring Boot와 Kotlin을 공부하며 마주친 고민과 해결 과정을 자유롭게 나눠보세요.</p>
        </div>
        <div class="hero-actions"><a class="button primary large" href="/posts/new">새 글 작성하기 →</a></div>
    </section>

    <section class="panel">
        <div class="panel-heading">
            <div><h2>최근 게시글</h2><p class="muted small">새로운 개발 이야기를 확인해보세요.</p></div>
            <select id="sort" class="select" aria-label="게시글 정렬">
                <option value="latest">최신순</option><option value="oldest">오래된순</option><option value="views">조회순</option>
            </select>
        </div>
        <div id="message" class="message hidden"></div>
        <div class="table-wrap">
            <table>
                <thead><tr><th>제목</th><th>작성자</th><th>조회수</th><th>수정일</th><th>태그</th></tr></thead>
                <tbody id="boardList"><tr><td colspan="5" class="empty">게시글을 불러오는 중입니다.</td></tr></tbody>
            </table>
        </div>
        <div class="pagination"><button id="prevButton" class="button" type="button">← 이전</button><span id="pageNumber">1 페이지</span><button id="nextButton" class="button" type="button">다음 →</button></div>
    </section>
</main>
<footer class="site-footer">Develop Board · Built with Kotlin & Spring Boot</footer>
<script src="/js/common.js"></script><script src="/js/list.js"></script>
</body>
</html>
