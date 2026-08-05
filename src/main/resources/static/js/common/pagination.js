// /js/common/pagination.js
function renderPagination(pageData,onPageClick) {
    const pagination = document.getElementById("pagination");
    if (!pagination) return;

    const { page, totalPages } = pageData;

    if (totalPages === 0) {
        pagination.innerHTML = "";
        return;
    }

    let html = "";

    // 이전 버튼
    html += `
        <li class="page-item ${page === 0 ? "disabled" : ""}">
            <button type="button" class="page-link" data-page="${page - 1}" ${page === 0 ? "disabled" : ""}>
                이전
            </button>
        </li>
    `;

    // 페이지 번호
    for (let i = 0; i < totalPages; i++) {
        html += `
            <li class="page-item ${i === page ? "active" : ""}">
                <button type="button" class="page-link" data-page="${i}">
                    ${i + 1}
                </button>
            </li>
        `;
    }

    // 다음 버튼
    html += `
        <li class="page-item ${page === totalPages - 1 ? "disabled" : ""}">
            <button type="button" class="page-link" data-page="${page + 1}" ${page === totalPages - 1 ? "disabled" : ""}>
                다음
            </button>
        </li>
    `;

    pagination.innerHTML = html;

    pagination.onclick = function (event) {
        const target = event.target.closest("[data-page]");
        if (!target || target.disabled) return;

        const nextPage = Number(target.dataset.page);
        onPageClick(nextPage);
    };
}