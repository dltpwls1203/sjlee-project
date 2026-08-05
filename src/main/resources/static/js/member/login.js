document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("loginForm");
    const btn = document.getElementById("loginBtn");

    if (!form || !btn) return;

    // ✔ form 기본 submit 동작 완전히 차단
    form.addEventListener("submit", (e) => {
        e.preventDefault();
    });

    // ✔ 로그인 버튼 클릭 → API 호출
    btn.addEventListener("click", async (e) => {
        e.preventDefault();

        const email = form.email.value.trim();
        const password = form.password.value.trim();

        // 유효성 검사
        if (!email || !password) {
            showError("아이디와 비밀번호를 입력하세요.");
            return;
        }

        try {
            // 로그인 API 호출
            const response = await fetch("/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            });

            const result = await response.json();

            // 로그인 실패
            if (!result.success) {
                showError(result.error.message);
                return;
            }

            const role = result.data.role;

            if (role === "ADMIN" || role === "SUPER_ADMIN") {
                window.location.href = "/admin/dashboard";
            } else {
                window.location.href = "/members/home";
            }

        } catch (err) {
            console.error(err);
            showError("서버와 통신 중 오류가 발생했습니다.");
        }
    });
});

/**
 * 에러 메시지 표시 (HTML에 맞게 조정 가능)
 */
function showError(message) {
    alert(message); // 👉 나중에 div error 영역으로 바꿔도 됨
}