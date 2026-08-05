const playerId = document.getElementById("playerId").value;

document.addEventListener("DOMContentLoaded", initPlayerDetail);

function initPlayerDetail() {
    loadPlayerDetail();

    document.getElementById("editBtn")
        .addEventListener("click", enableEditMode);

    document.getElementById("saveBtn")
        .addEventListener("click", submitUpdate);
}

/* =====================
 * 선수 상세 조회
 * ===================== */

async function loadPlayerDetail() {

    try {
        const response = await fetch(`/api/players/${playerId}`);
        const result = await response.json();

        if (!result.success) {
            alert(result.error.message);
            return;
        }

        bindDetail(result.data);

    } catch (e) {
        console.error(e);
        alert("선수 정보를 불러오는 중 오류가 발생했습니다.");
    }
}

function bindDetail(data) {

    setValue("name", data.playerName);
    setValue("birthDate", data.birthDate);
    setValue("position", data.position);
    setValue("backNumber", data.backNumber);

    toggleFormDisabled(true);
}

/* =====================
 * 수정 모드
 * ===================== */

function enableEditMode() {

    toggleFormDisabled(false);

    document.getElementById("editBtn").classList.add("d-none");
    document.getElementById("saveBtn").classList.remove("d-none");

    const listBtn = document.getElementById("listBtn");

    listBtn.textContent = "취소";
    listBtn.removeAttribute("href");
    listBtn.onclick = cancelEdit;
}

async function cancelEdit(e) {

    e.preventDefault();

    await loadPlayerDetail();

    toggleFormDisabled(true);

    document.getElementById("saveBtn").classList.add("d-none");
    document.getElementById("editBtn").classList.remove("d-none");

    const listBtn = document.getElementById("listBtn");

    listBtn.textContent = "목록";
    listBtn.href = "/admin/players";
    listBtn.onclick = null;
}

function toggleFormDisabled(disabled) {

    [
        "name",
        "birthDate",
        "position",
        "backNumber"
    ].forEach(id => {

        const el = document.getElementById(id);

        if (el) {
            el.disabled = disabled;
        }
    });
}

/* =====================
 * 수정
 * ===================== */
async function submitUpdate() {

    if (!validateForm()) {
        return;
    }

    const body = {
        playerName: getValue("name"),
        birthDate: getValue("birthDate"),
        position: getValue("position"),
        backNumber: Number(getValue("backNumber"))
    };

    try {

        const response = await fetch(`/api/players/${playerId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body)
        });

        const result = await response.json();

        if (!result.success) {
            alert(result.error.message);
            return;
        }

        alert("선수 정보가 수정되었습니다.");

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

    const name = getValue("name");

    if (!name) {
        alert("선수 이름은 필수입니다.");
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

function setValue(id, value) {

    const el = document.getElementById(id);

    if (el && value !== undefined && value !== null) {
        el.value = value;
    }
}