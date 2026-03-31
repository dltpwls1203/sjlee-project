document.addEventListener("DOMContentLoaded", initMatchForm);

function initMatchForm() {
    loadGrounds();
    loadOpponentTeams();
    loadMatchTypes();

    document.getElementById("status")
        .addEventListener("change", handleStatusChange);

    document.getElementById("btnSave")
        .addEventListener("click", submitMatch);
}

/* =====================
 * 구장 정보 가져오기
 * ===================== */
async function loadGrounds() {
    try {
        const response = await fetch("/api/grounds/select");
        const result = await response.json();

        if (!result.success) {
            alert("구장 정보를 불러오지 못했습니다.");
            return;
        }

        const select = document.getElementById("groundId");
        result.data.forEach(ground => {
            select.appendChild(createOption(ground.id, ground.name));
        });

    } catch (e) {
        console.error(e);
        alert("구장 조회 중 오류가 발생했습니다.");
    }
}

/* =====================
 * 상대팀 정보 가져오기
 * ===================== */
async function loadOpponentTeams() {
    try {
        const response = await fetch("/api/opponent-teams");
        const result = await response.json();

        if (!result.success) {
            alert("상대 팀 정보를 불러오지 못했습니다.");
            return;
        }

        const select = document.getElementById("opponentTeamId");
        result.data.forEach(team => {
            select.appendChild(createOption(team.id, team.name));
        });

    } catch (e) {
        console.error(e);
        alert("상대 팀 조회 중 오류가 발생했습니다.");
    }
}

/* =====================
 * 경기 유형 정보 세팅
 * ===================== */
function loadMatchTypes() {
    const matchTypes = [
        { code: "EXTERNAL", name: "시합" },
        { code: "INTERNAL", name: "자체전" }
    ];

    const select = document.getElementById("matchType");

    matchTypes.forEach(type => {
        select.appendChild(createOption(type.code, type.name));
    });
}

function createOption(value, text) {
    const option = document.createElement("option");
    option.value = value;
    option.textContent = text;
    return option;
}

/* =====================
 * 상태에 따른 점수 입력 제어
 * ===================== */
function handleStatusChange() {
    const status = getValue("status");

    const ourScore = document.getElementById("ourScore");
    const opponentScore = document.getElementById("opponentScore");

    if (status === "DONE") {
        ourScore.disabled = false;
        opponentScore.disabled = false;
    } else {
        // SCHEDULED / CANCELED / POSTPONED
        ourScore.value = 0;
        opponentScore.value = 0;
        ourScore.disabled = true;
        opponentScore.disabled = true;
    }
}

/* =====================
 * 등록
 * ===================== */

async function submitMatch() {
    if (!validateForm()) return;

    const body = {
        teamId: Number(document.getElementById("teamId").value),
        matchAt: getValue("matchAt"),
        groundId: getValue("groundId") || null,
        opponentTeamId: getValue("opponentTeamId") || null,
        status: getValue("status"),
        ourScore: Number(getValue("ourScore") || 0),
        opponentScore: Number(getValue("opponentScore") || 0),
        matchType: getValue("matchType")
    };

    try {
        const response = await fetch("/api/matches", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        const result = await response.json();

        if (!result.success) {
            alert(result.error.message);
            return;
        }

        alert("경기가 등록되었습니다.");
        location.href = "/admin/matches";

    } catch (e) {
        console.error(e);
        alert("서버 오류가 발생했습니다.");
    }
}

/* =====================
 * Validation
 * ===================== */

function validateForm() {
    const matchAt = getValue("matchAt");
    const status = getValue("status");
    const ourScore = getValue("ourScore");
    const opponentScore = getValue("opponentScore");

    if (!matchAt) {
        alert("경기 일시는 필수입니다.");
        return false;
    }

    if (!status) {
        alert("경기 상태를 선택하세요.");
        return false;
    }

    if (status === "DONE") {
        if (ourScore === "" || opponentScore === "") {
            alert("완료된 경기는 점수를 입력해야 합니다.");
            return false;
        }

        if (ourScore < 0 || opponentScore < 0) {
            alert("점수는 0 이상의 정수만 입력 가능합니다.");
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
