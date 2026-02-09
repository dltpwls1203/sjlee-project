document.addEventListener("DOMContentLoaded", initMatchList);

function initMatchList() {
    loadMatchList();
}

/* =====================
 * Main
 * ===================== */

async function loadMatchList() {
    try {
        const result = await fetchMatchList();

        if (!result.success) {
            showError(result.error?.message || "경기 목록을 불러오지 못했습니다.");
            return;
        }

        renderMatchList(result.data);

    } catch (err) {
        console.error(err);
        showError("서버와 통신 중 오류가 발생했습니다.");
    }
}

/* =====================
 * API
 * ===================== */

async function fetchMatchList() {
    const response = await fetch("/api/matches", {
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

function renderMatchList(matches) {
    const tbody = document.getElementById("matchTableBody");

    if (!tbody) return;

    if (!matches || matches.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="6" class="text-center">등록된 경기가 없습니다.</td>
            </tr>
        `;
        return;
    }

    tbody.innerHTML = matches
        .map((match, index) => createMatchRow(match, index))
        .join("");
}

function createMatchRow(match, index) {
    return `
        <tr>
            <td>${index + 1}</td> <!-- 화면용 번호 -->
            <td>${formatDateWithDay(match.matchAt)}</td>
            <td>${match.opponentTeamName}</td>
            <td>${match.ourScore} : ${match.opponentScore}</td>
            <td>${renderResult(match.result)}</td>
            <td>${match.status}</td>
            <td>${match.attendCount}</td>
        </tr>
    `;
}

/* =====================
 * Utils
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

function renderResult(result) {
    if (!result) {
        return `<span class="badge bg-secondary">-</span>`;
    }

    const map = {
        WIN:  `<span class="badge bg-success">승</span>`,
        DRAW: `<span class="badge bg-warning">무</span>`,
        LOSE: `<span class="badge bg-danger">패</span>`
    };

    return map[result] ?? `<span class="badge bg-secondary">-</span>`;
}