document.addEventListener("DOMContentLoaded", initPlayerList);

function initPlayerList() {
    loadPlayerList();
}

/* =====================
 * Main
 * ===================== */

async function loadPlayerList(page = 0, keyword = "") {
    try {
        const result = await fetchPlayerList(page, keyword);

        if (!result.success) {
            showError(result.error?.message || "선수 목록을 불러오지 못했습니다.");
            return;
        }

        renderPlayerList(result.data.content, result.data.page, result.data.size);
        renderPagination(result.data, (nextPage) => loadPlayerList(nextPage, keyword));

    } catch (err) {
        console.error(err);
        showError("서버와 통신 중 오류가 발생했습니다.");
    }
}

/* =====================
 * 선수 목록 API
 * ===================== */
async function fetchPlayerList(page = 0, keyword = "") {
    const params = new URLSearchParams();
    params.append("page", page);
    params.append("size", 10);

    if (keyword) {
        params.append("keyword", keyword);
    }

    const response = await fetch(`/api/players?${params.toString()}`, {
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
function renderPlayerList(players, page, size) {
    const tbody = document.getElementById("playerTableBody");

    if (!tbody) return;

    if (!players || players.length === 0) {
        tbody.innerHTML = `
            <tr>m
                <td colspan="7" class="text-center">등록된 선수가 없습니다.</td>
            </tr>
        `;
        return;
    }

    tbody.innerHTML = players
        .map((player, index) => createPlayerRow(player, index, page, size))
        .join("");
}


function createPlayerRow(player, index, page, size) {
    const rowNumber = page * size + index + 1;

    return `
        <tr>
            <td>${rowNumber}</td>
            <td>${player.name}</td>
            <td>${getKoreanAge(player.birthDate)}</td>
            <td>${player.position ?? "-"}</td>
            <td>${player.backNumber ?? "-"}</td>
            <td>${player.attendCount ?? 0}</td>
            <td class="text-center">
                <button type="button"
                        class="btn btn-sm btn-outline-primary"
                        title="선수 수정"
                        onclick="goToPlayerDetail(${player.playerId})">
                    <i class="fas fa-edit"></i> 수정
                </button>
            </td>
        </tr>
    `;
}

/* =====================
 * 검색
 * ===================== */
function searchPlayers() {
    const keyword = document.getElementById("keyword")?.value ?? "";
    loadPlayerList(0, keyword);
}

/* =====================
 * Utils
 * ===================== */

function showError(message) {
    alert(message); // 추후 공통 error UI로 교체 가능
}

function getKoreanAge(birthDate) {
    if(!birthDate) return "-";

    const baseYear = new Date().getFullYear();
    const birthYear = Number(birthDate.split("-")[0]);
    const age = baseYear - birthYear + 1;

    return `${age}세`;
}

function goToPlayerDetail(playerId) {
    location.href = `/admin/players/detail/${playerId}`;
}