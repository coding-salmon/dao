function checkPassword(){
    const passwordField = document.getElementById('password');
    const confirmPasswordField = document.getElementById('confirmPassword');
    const errorElement = document.getElementById('password-error'); // 오류 메시지를 표시할 요소

    function checkPassword() {
        const password = passwordField.value;
        const confirmPassword = confirmPasswordField.value;

        if (password === confirmPassword) {
            // 비밀번호가 일치하는 경우
            errorElement.textContent = ''; // 오류 메시지 초기화
        } else {
            // 비밀번호가 일치하지 않는 경우
            errorElement.textContent = '비밀번호가 일치하지 않습니다.'; // 오류 메시지 표시
        }
    }

// 비밀번호 확인 필드의 값이 변경될 때마다 checkPassword 함수 호출
    confirmPasswordField.addEventListener('input', checkPassword);
}



function checkError() {
    const phoneInput = document.getElementById('phone');
    const phoneError = document.getElementById('phone-error');
    // 입력값을 숫자만 포함하도록 필터링하고, 11자리로 제한
    phoneInput.value = phoneInput.value.replace(/[^\d]/g, '').slice(0, 11);
    // 입력값이 숫자로 이루어져 있는지 검사
    if (/[^0-9]/.test(phoneInput.value)) {
        phoneError.textContent = "전화번호는 ' - ' 없이 숫자로만 입력해주세요.";

    } else if (phoneInput.value.length > 11){
        phoneError.textContent = "전화번호는 11자리를 초과할 수 없습니다.";
    } else if (phoneInput.value.length < 10) {
        phoneError.textContent = "전화번호는 10자리 이상이어야 합니다."
    }else{
        // 입력이 유효할 경우, 오류 메시지 숨기기
        phoneError.textContent = "";

    }
}

// 휴대폰 번호 중복 검사
function checkPhone() {
    const phoneInput = document.getElementById('phone');
    const phoneCheckDiv = document.getElementById('phone-check');
    const phone = phoneInput.value;


    // 중복 검사를 위한 서버 요청 구현
    // POST 요청을 통해 서버에 JSON 형태로 휴대폰 번호 전송
    fetch('/api/check-phone', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ phone: phone }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.isDuplicate) {
                phoneCheckDiv.textContent = "이미 사용 중인 휴대폰 번호입니다.";
                phoneCheckDiv.style.color = 'red';
            } else {
                phoneCheckDiv.textContent = "사용 가능한 휴대폰 번호입니다.";
                phoneCheckDiv.style.color = 'green';
            }
        })
        .catch(error => {
            console.error('Error:', error);
            phoneCheckDiv.textContent = "휴대폰 번호 중복 검사 중 오류 발생.";
        });
}

    // 이메일 중복검사
    function checkEmail() {
    const email = document.getElementById('email').value;
    if (email) {
    fetch('/api/check-email', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json',
},
    body: JSON.stringify({email: email}),
})
    .then(response => response.json())
    .then(data => {
    if (data.isAvailable) {
    document.getElementById('email-check').textContent = '사용 가능한 이메일입니다.';
    document.getElementById('email-check').style.color = 'green';
} else {
    document.getElementById('email-check').textContent = '이미 사용 중인 이메일입니다.';
    document.getElementById('email-check').style.color = 'red';
}
})
    .catch((error) => {
    console.error('Error:', error);
});
}
}
