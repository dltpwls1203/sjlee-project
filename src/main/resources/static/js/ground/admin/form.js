document.addEventListener("DOMContentLoaded", initGroundForm);

function initGroundForm() {

    document.getElementById("btnSave")
        .addEventListener("click", submitGround);
}



/* =====================
 * 등록
 * ===================== */
async function submitGround() {
    if (!validateForm()) return;

    const body = {
        name: getValue("name"),
        location: getValue("location") || null,
        fieldWidth: Number(getValue("fieldWidth") || 0),
        fieldLength: Number(getValue("fieldLength") || 0),
        playersPerTeam: Number(getValue("playersPerTeam") || 0),
        rentalFee: Number(getValue("rentalFee") || 0)

    };

    try {
        const response = await fetch("/api/grounds", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        const result = await response.json();

        if (!result.success) {
            alert(result.error.message);
            return;
        }

        alert("구장이 등록되었습니다.");
        location.href = "/admin/grounds";

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

    if (!name) {
        alert("구장 이름은 필수입니다.");
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
