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
            showError(result.error?.message || "구장 목록을 불러오지 못했습니다.");
            return;
        }

        console.log(result.data);

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
        .map((ground, index) => createGroundRow(ground, index))
        .join("");
}

function createGroundRow(ground, index) {
    return `
        <tr>
            <td>${index + 1}</td> <!-- 화면용 번호 -->
            <td>${ground.name}</td>
            <td>${ground.location}</td>
            <td>${formatFieldSize(ground.fieldWidth, ground.fieldLength)}</td>
            <td>${formatPlayers(ground.playerPerTeam)}</td>
            <td>${formatRentalFee(ground.rentalFee)}</td>
        </tr>
    `;
}

function formatPlayers(count) {
    if (!count) return "-";
    return `${count} vs ${count}`;
}

function formatFieldSize(width, length) {
    if (!width || !length) return "-";
    return `${width}m × ${length}m`;
}

function formatRentalFee(rentalFee) {
    if (rentalFee == null) return "-";
    return rentalFee.toLocaleString() + "원";
}