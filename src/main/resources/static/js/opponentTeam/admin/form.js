document.addEventListener("DOMContentLoaded", initOpponentTeamForm);

function initOpponentTeamForm() {

    document.getElementById("btnSave")
        .addEventListener("click", submitOpponentTeam);
}

/* =====================
 * 보류 나중에 선택 옵션을 위해 남겨둠
 * ===================== */
function createOption(value, text) {
    const option = document.createElement("option");
    option.value = value;
    option.textContent = text;
    return option;
}


/* =====================
 * 등록
 * ===================== */
async function submitOpponentTeam() {
    if (!validateForm()) return;

    const body = {
        name: getValue("name"),
        skillLevel: getValue("skillLevel"),
        ageRange: getValue("ageRange") || null
    };

    try {
        const response = await fetch("/api/opponent-teams", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        const result = await response.json();

        if (!result.success) {
            alert(result.error.message);
            return;
        }

        alert("상대팀 정보가 등록되었습니다.");
        location.href = "/admin/opponentTeams";

    } catch (e) {
        console.error(e);
        alert("서버 오류가 발생했습니다.");
    }
}

/* =====================
 * Validation
 * ===================== */
function validateForm() {
    const name = getValue("name");
    const skillLevel = getValue("skillLevel");

    if (!name) {
        alert("상대팀 이름은 필수입니다.");
        return false;
    }

    if (!skillLevel) {
        alert("실력을 선택해주세요.");
        return false;
    }

    return true;
}

/* =====================
 * Utils
 * ===================== */
function getValue(id) {
    return document.getElementById(id)?.value;
}
