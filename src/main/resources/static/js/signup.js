function check(input) {
    // 입력값이 숫자로 이루어져 있는지 검사
    if (/[^0-9]/.test(input.value)) {
        alert("전화번호는 "-" 없이 숫자로만 입력해주세요.");
        // 숫자 이외의 입력 제거
        input.value = input.value.replace(/[^\d]/g, '');
    }
}