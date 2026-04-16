document.addEventListener("DOMContentLoaded", initMatchDetail);

function initMatchDetail() {
    loadGrounds();
    loadOpponentTeams();
    loadMatchDetail();

    document.getElementById("editBtn")
        .addEventListener("click", enableEditMode);

    document.getElementById("saveBtn")
        .addEventListener("click", submitUpdate);

    document.getElementById("status")
        .addEventListener("change", handleStatusChange);

    document.getElementById("attendanceBtn")
        .addEventListener("click", openAttendancePopup);
}

/* =====================
 * 경기 상세 정보
 * ===================== */

async function loadMatchDetail() {
    try {
        const response = await fetch(`/api/matches/${matchId}`);
        const result = await response.json();

        if (!result.success) {
            alert("경기 정보를 불러오지 못했습니다.");
            return;
        }

        bindDetail(result.data);

    } catch (e) {
        console.error(e);
        alert("상세 조회 중 오류가 발생했습니다.");
    }
}


function bindDetail(data) {
    setValue("matchAt", data.matchAt);
    setValue("groundId", data.groundId);
    setValue("opponentTeamId", data.opponentTeamId);
    setValue("status", data.status);
    setValue("ourScore", data.ourScore);
    setValue("opponentScore", data.opponentScore);
    setValue("attendanceCount", data.attendanceCount);

    handleStatusChange();
}

/* =====================
 * 구장 선택 옵션
 * ===================== */

async function loadGrounds() {
    const response = await fetch("/api/grounds");
    const result = await response.json();

    if (!result.success) return;

    const select = document.getElementById("groundId");
    result.data.forEach(ground =>
        select.appendChild(createOption(ground.id, ground.name))
    );
}

/* =====================
 * 상대팀 선택 옵션
 * ===================== */

async function loadOpponentTeams() {
    const response = await fetch("/api/opponent-teams/select");
    const result = await response.json();

    if (!result.success) return;

    const select = document.getElementById("opponentTeamId");
    result.data.forEach(team =>
        select.appendChild(createOption(team.id, team.name))
    );
}

function createOption(value, text) {
    const option = document.createElement("option");
    option.value = value;
    option.textContent = text;
    return option;
}

/* =====================
 * 수정 모드
 * ===================== */

function enableEditMode() {
    toggleFormDisabled(false);

    document.getElementById("editBtn").classList.add("d-none");
    document.getElementById("saveBtn").classList.remove("d-none");
}

function toggleFormDisabled(disabled) {
    [
        "matchAt",
        "groundId",
        "opponentTeamId",
        "status",
        "ourScore",
        "opponentScore"
    ].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.disabled = disabled;
    });
}

/* =====================
 * Status Rule Handling
 * ===================== */

function handleStatusChange() {
    const status = getValue("status");

    const ourScore = document.getElementById("ourScore");
    const opponentScore = document.getElementById("opponentScore");

    if (status === "DONE") {
        ourScore.disabled = false;
        opponentScore.disabled = false;
    } else {
        ourScore.value = 0;
        opponentScore.value = 0;
        ourScore.disabled = true;
        opponentScore.disabled = true;
    }
}

/* =====================
 * Submit Update
 * ===================== */

async function submitUpdate() {
    if (!validateForm()) return;

    const body = {
        matchAt: getValue("matchAt"),
        groundId: getValue("groundId") || null,
        opponentTeamId: getValue("opponentTeamId") || null,
        status: getValue("status"),
        ourScore: Number(getValue("ourScore") || 0),
        opponentScore: Number(getValue("opponentScore") || 0)
    };

    try {
        const response = await fetch(`/api/admin/matches/${matchId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        const result = await response.json();

        if (!result.success) {
            alert(result.error.message);
            return;
        }

        alert("경기 정보가 수정되었습니다.");
        location.reload();

    } catch (e) {
        console.error(e);
        alert("수정 중 오류가 발생했습니다.");
    }
}

/* =====================
 * Validation
 * ===================== */

function validateForm() {
    const matchAt = getValue("matchAt");
    const status = getValue("status");

    if (!matchAt) {
        alert("경기 일시는 필수입니다.");
        return false;
    }

    if (!status) {
        alert("경기 상태를 선택하세요.");
        return false;
    }

    if (status === "DONE") {
        const ourScore = getValue("ourScore");
        const opponentScore = getValue("opponentScore");

        if (ourScore === "" || opponentScore === "") {
            alert("완료된 경기는 점수를 입력해야 합니다.");
            return false;
        }
    }

    return true;
}


/* =====================
 * Utils
 * ===================== */

function getValue(id) {
    return document.getElementById(id)?.value;
}

function setValue(id, value) {
    const el = document.getElementById(id);
    if (el && value !== undefined && value !== null) {
        el.value = value;
    }
}

/* =====================
 * Attendance
 * ===================== */
function openAttendancePopup() {

    const modal = new bootstrap.Modal(
        document.getElementById('attendanceModal')
    );

    modal.show();

    loadAttendance();
}

async function loadAttendance() {

    try {
        const response = await fetch(`/api/matches/${matchId}/attendance`);
        const result = await response.json();

        if (!result.success) {
            alert("출석 정보를 불러오지 못했습니다.");
            return;
        }

        renderAttendanceList(result.data);

    } catch (e) {
        console.error(e);
        alert("출석 조회 중 오류 발생");
    }
}

function renderAttendanceList(list) {

    const tbody = document.getElementById("attendanceTableBody");

    if (!list || list.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="3" class="text-center">출석 인원이 없습니다.</td>
            </tr>
        `;
        return;
    }

    tbody.innerHTML = list.map((item, index) => `
        <tr>
            <td>
                <input type="checkbox"
                       class="attendance-checkbox"
                       value="${item.playerId}">
            </td>
            <td>${renderPlayerName(item.playerName, item.attendStatus)}</td>
            <td>${renderAttendStatus(item.attendStatus)}</td>
        </tr>
    `).join("");
}

function renderAttendStatus(status) {

    const map = {
        ATTEND: "참석",
        LATE: "지각",
        ABSENT: "불참"
    };

    return map[status] ?? "불참";
}

function renderPlayerName(name, status) {

    if (status === "ATTEND") {
        return `<span style="color:#007bff; font-weight:bold;">${name}</span>`;
    }

    return name;
}

function getSelectedPlayerIds() {
    const checked = document.querySelectorAll('.attendance-checkbox:checked');

    return Array.from(checked).map(cb => Number(cb.value));
}

async function updateAttendance(status) {

    const playerIds = getSelectedPlayerIds();

    if (playerIds.length === 0) {
        alert("선수를 선택하세요.");
        return;
    }

    try {
        const response = await fetch(`/api/matches/${matchId}/attendance`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                playerIds: playerIds,
                attendStatus: status
            })
        });

        const result = await response.json();

        if (!result.success) {
            alert(result.error.message);
            return;
        }

        alert("출석 상태가 변경되었습니다.");

        loadAttendance(); // 다시 조회

    } catch (e) {
        console.error(e);
        alert("출석 변경 중 오류 발생");
    }
}

