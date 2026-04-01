document.addEventListener("DOMContentLoaded", initMatchList);

function initMatchList() {
    loadMatchList();
}

/* =====================
 * Main
 * ===================== */

async function loadMatchList(keyword = "") {
    try {
        const result = await fetchMatchList(keyword);

        if (!result.success) {
            showError(result.error?.message || "경기 목록을 불러오지 못했습니다.");
            return;
        }

        renderMatchList(result.data.content, result.data.page, result.data.size);
        renderPagination(result.data, "loadMatchList", keyword);

    } catch (err) {
        console.error(err);
        showError("서버와 통신 중 오류가 발생했습니다.");
    }
}

/* =====================
 * 매치 목록 API
 * ===================== */
async function fetchMatchList(page = 0, keyword = "") {
    const params = new URLSearchParams();
    params.append("page", page);
    params.append("size", 10);

    if (keyword) {
        params.append("keyword", keyword);
    }

    const response = await fetch(`/api/matches?${params.toString()}`, {
        method: "GET",
        headers: { "Accept": "application/json" }
    });

    if (!response.ok) {
        throw new Error("HTTP error " + response.status);
    }

    return response.json();
}

/* =====================
 * Render
 * ===================== */
function renderMatchList(matches, page, size) {
    const tbody = document.getElementById("matchTableBody");

    if (!tbody) return;

    if (!matches || matches.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="8" class="text-center">등록된 경기가 없습니다.</td>
            </tr>
        `;
        return;
    }

    tbody.innerHTML = matches
        .map((match, index) => createMatchRow(match, index, page, size))
        .join("");
}

function createMatchRow(match, index, page, size) {
    const rowNumber = page * size + index + 1;

    return `
        <tr>
            <td>${rowNumber}</td>
            <td>${formatDateWithDay(match.matchAt)}</td>
            <td>${match.groundName} (${match.playersPerTeam} vs ${match.playersPerTeam})</td>
            <td>${renderOpponentName(match.opponentTeamName, match.matchType)}</td>
            <td>${renderResult(match.result, match.matchType, match.ourScore, match.opponentScore)}</td>
            <td>${renderStatus(match.status)}</td>
            <td>${match.attendanceCount}</td>
            <td class="text-center">
                <button type="button"
                        class="btn btn-sm btn-outline-primary"
                        title="상세보기"
                        onclick="goToMatchDetail(${match.matchId})">
                    <i class="fas fa-search">상세</i>
                </button>
            </td>
        </tr>
    `;
}

/* =====================
 * 날짜 포멧
 * ===================== */
function formatDateWithDay(isoDate) {
    if (!isoDate) return "-";

    const d = new Date(isoDate);

    const yyyy = d.getFullYear();
    const mm = String(d.getMonth() + 1).padStart(2, "0");
    const dd = String(d.getDate()).padStart(2, "0");

    const days = ["일", "월", "화", "수", "목", "금", "토"];
    const day = days[d.getDay()];

    const hh = String(d.getHours()).padStart(2, "0");
    const min = String(d.getMinutes()).padStart(2, "0");

    return `${yyyy}.${mm}.${dd} (${day}) ${hh}:${min}`;
}

function showError(message) {
    alert(message); // 추후 공통 error UI로 교체 가능
}

/* =====================
 * DB 결과 코드 → 화면 표시 텍스트 변환
 * (WIN/DRAW/LOSE → 승/무/패)
 * ===================== */
function renderResult(result, matchType, ourScore, opponentScore) {

    if (matchType === 'INTERNAL') {
        return `<span class="badge bg-info">자체전</span>`;
    }

    const map = {
        WIN:  `<span class="badge bg-success">승</span>`,
        DRAW: `<span class="badge bg-warning">무</span>`,
        LOSE: `<span class="badge bg-danger">패</span>`
    };

    const resultText = map[result] ?? `<span class="badge bg-secondary">-</span>`;

    if (ourScore == null || opponentScore == null) {
        return resultText;
    }

    if (ourScore === 0 && opponentScore === 0) {
        return resultText;
    }

    return `${resultText} (${ourScore} : ${opponentScore})`;
}

/* =====================
 * DB 결과 코드 → 화면 표시 텍스트 변환
 * (SCHEDULED/DONE/CANCELED/POSTPONE → 예정/종료/취소/연기)
 * ===================== */
function renderStatus(status) {
    if (!status) {
        return `-`;
    }

    const map = {
        SCHEDULED:  `예정`,
        DONE:  `종료`,
        CANCELED:  `취소`,
        POSTPONE:  `연기`

    };

    return map[status] ?? `-`;
}

/* =====================
 * 상대팀 이름
 * (INTERNAL → 자체전)
 * ===================== */
function renderOpponentName(name, matchType) {
    if (matchType === 'INTERNAL') {
        return '자체전';
    }
    return name ?? '-';
}

function goToMatchDetail(matchId) {
    window.location.href = `/admin/matches/detail/${matchId}`;
}