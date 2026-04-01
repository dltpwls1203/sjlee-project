// /js/common/pagination.js

function renderPagination(pageData) {
    const pagination = document.getElementById("pagination");
    if (!pagination) return;

    const { page, totalPages } = pageData;
    const keyword = document.getElementById("keyword")?.value ?? "";

    if (totalPages === 0) {
        pagination.innerHTML = "";
        return;
    }

    let html = "";

    // 이전 버튼
    html += `
        <li class="page-item ${page === 0 ? "disabled" : ""}">
            <a class="page-link" href="javascript:void(0)"
               onclick="loadPlayerList(${page - 1}, '${keyword}')">
                이전
            </a>
        </li>
    `;

    // 페이지 번호
    for (let i = 0; i < totalPages; i++) {
        html += `
            <li class="page-item ${i === page ? "active" : ""}">
                <a class="page-link" href="javascript:void(0)"
                   onclick="loadPlayerList(${i}, '${keyword}')">
                    ${i + 1}
                </a>
            </li>
        `;
    }

    // 다음 버튼
    html += `
        <li class="page-item ${page === totalPages - 1 ? "disabled" : ""}">
            <a class="page-link" href="javascript:void(0)"
               onclick="loadPlayerList(${page + 1}, '${keyword}')">
                다음
            </a>
        </li>
    `;

    pagination.innerHTML = html;
}