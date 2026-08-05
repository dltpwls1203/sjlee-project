document.addEventListener("DOMContentLoaded", initOpponentTeamList);

function initOpponentTeamList() {
    loadOpponentTeamList();
}

/* =====================
 * Main
 * ===================== */

async function loadOpponentTeamList(page = 0, keyword = "") {
    try {
        const result = await fetchOpponentTeamList(page, keyword);

        if (!result.success) {
            showError(result.error?.message || "상대팀 목록을 불러오지 못했습니다.");
            return;
        }

        renderOpponentTeamList(result.data.content, result.data.page, result.data.size);
        renderPagination(result.data, (nextPage) => loadOpponentTeamList(nextPage, keyword));

    } catch (err) {
        console.error(err);
        showError("서버와 통신 중 오류가 발생했습니다.");
    }
}

/* =====================
 * 선수 목록 API
 * ===================== */
async function fetchOpponentTeamList(page = 0, keyword = "") {
    const params = new URLSearchParams();
    params.append("page", page);
    params.append("size", 10);

    if (keyword) {
        params.append("keyword", keyword);
    }

    const response = await fetch(`/api/opponent-teams?${params.toString()}`, {
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
function renderOpponentTeamList(opponentTeams, page, size) {
    const tbody = document.getElementById("opponentTeamTableBody");

    if (!tbody) return;

    if (!opponentTeams || opponentTeams.length === 0) {
        tbody.innerHTML = `
            <tr>m
                <td colspan="6" class="text-center">등록된 상대팀이 없습니다.</td>
            </tr>
        `;
        return;
    }

    tbody.innerHTML = opponentTeams
        .map((opponentTeam, index) => createOpponentTeamRow(opponentTeam, index, page, size))
        .join("");
}


function createOpponentTeamRow(opponentTeam, index, page, size) {
    const rowNumber = page * size + index + 1;

    return `
        <tr>
            <td>${rowNumber}</td>
            <td>${opponentTeam.name}</td>
            <td>${opponentTeam.skillLevel}</td>
            <td>${opponentTeam.ageRange}</td>
            <td>${opponentTeam.matchCount}회
                (${opponentTeam.winCount}/${opponentTeam.drawCount}/${opponentTeam.loseCount})
            </td>
        </tr>
    `;
}

/* =====================
 * 검색
 * ===================== */
function searchOpponentTeams() {
    const keyword = document.getElementById("keyword")?.value ?? "";
    loadOpponentTeamList(0, keyword);
}

/* =====================
 * Utils
 * ===================== */

function showError(message) {
    alert(message); // 추후 공통 error UI로 교체 가능
}

