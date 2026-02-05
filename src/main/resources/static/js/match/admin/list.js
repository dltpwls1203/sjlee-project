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
            <td>${formatDate(match.matchAt)}</td>
            <td>${match.opponentName}</td>
            <td>${match.ourScore} : ${match.opponentScore}</td>
            <td>${match.status}</td>
            <td>${match.attendanceCount}</td>
        </tr>
    `;
}

/* =====================
 * Utils
 * ===================== */

function formatDate(date) {
    if (!date) return "-";
    return date.replaceAll("-", ".");
}

function showError(message) {
    alert(message); // 추후 공통 error UI로 교체 가능
}