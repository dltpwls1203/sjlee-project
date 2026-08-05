document.addEventListener("DOMContentLoaded", initPlayerForm);

function initPlayerForm() {

    document.getElementById("btnSave")
        .addEventListener("click", submitPlayer);
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
async function submitPlayer() {
    if (!validateForm()) return;

    const body = {
        teamId: Number(document.getElementById("teamId").value),
        name: getValue("name"),
        position: getValue("position"),
        birthDate: getValue("birthDate") || null,
        backNumber: Number(getValue("backNumber") || 0),
        status: getValue("status") || "ACTIVE",
        playerType: getValue("playerType") || "REGULAR"
    };

    try {
        const response = await fetch("/api/players", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        const result = await response.json();

        if (!result.success) {
            alert(result.error.message);
            return;
        }

        alert("선수가 등록되었습니다.");
        location.href = "/admin/players";

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
    const position = getValue("position");

    if (!name) {
        alert("선수 이름은 필수입니다.");
        return false;
    }

    if (!position) {
        alert("포지션을 선택해주세요.");
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
