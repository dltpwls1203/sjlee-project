document.addEventListener("DOMContentLoaded", initPlayerList);

function initPlayerList() {
    loadPlayerList();
}

/* =====================
 * Main
 * ===================== */

async function loadPlayerList(keyword = "") {
    try {
        const result = await fetchPlayerList(keyword);

        if (!result.success) {
            showError(result.error?.message || "선수 목록을 불러오지 못했습니다.");
            return;
        }

        renderPlayerList(result.data);

    } catch (err) {
        console.error(err);
        showError("서버와 통신 중 오류가 발생했습니다.");
    }
}

/* =====================
 * 선수 목록 API
 * ===================== */

async function fetchPlayerList(keyword) {
    const url = keyword
        ? `/api/players?keyword=${encodeURIComponent(keyword)}`
        : `/api/players`;

    const response = await fetch(url, {
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

function renderPlayerList(players) {
    const tbody = document.getElementById("playerTableBody");

    if (!tbody) return;

    if (!players || players.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="6" class="text-center">등록된 선수가 없습니다.</td>
            </tr>
        `;
        return;
    }

    tbody.innerHTML = players
        .map((player, index) => createPlayerRow(player, index))
        .join("");
}

function createPlayerRow(player, index) {
    return `
        <tr>
            <td>${index + 1}</td>
            <td>${player.name}</td>
            <td>${getKoreanAge(player.birthDate)}</td>
            <td>${player.position ?? "-"}</td>
            <td>${player.backNumber ?? "-"}</td>
            <td>${player.attendCount ?? 0}</td>
        </tr>
    `;
}

/* =====================
 * 검색
 * ===================== */

function searchPlayers() {
    const keyword = document.getElementById("keyword")?.value ?? "";
    loadPlayerList(keyword);
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