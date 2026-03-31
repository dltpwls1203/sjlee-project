document.addEventListener("DOMContentLoaded", initGroundList);

function initGroundList() {
    loadGroundList();
}

/* =====================
 * Main
 * ===================== */

async function loadGroundList() {
    try {
        const result = await fetchGroundList();

        if (!result.success) {
            showError(result.error?.message || "경기 목록을 불러오지 못했습니다.");
            return;
        }

        renderGroundList(result.data);

    } catch (err) {
        console.error(err);
        showError("서버와 통신 중 오류가 발생했습니다.");
    }
}

/* =====================
 * 매치 목록 API
 * ===================== */

async function fetchGroundList() {
    const response = await fetch("/api/grounds", {
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
function renderGroundList(grounds) {
    const tbody = document.getElementById("groundTableBody");

    if (!tbody) return;

    if (!grounds || grounds.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="6" class="text-center">등록된 구장이 없습니다.</td>
            </tr>
        `;
        return;
    }

    tbody.innerHTML = grounds
        .map((match, index) => createGroundRow(ground, index))
        .join("");
}

function createGroundRow(ground, index) {
    return `
        <tr>
            <td>${index + 1}</td> <!-- 화면용 번호 -->
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
