document.addEventListener('DOMContentLoaded', function (){
    var imageContainer = document.getElementById('imageContainer'); // 크롭될 이미지를 표시할 img 태그
    var input =document.getElementById('pic'); // 파일을 업로드하는 input 태그
    var zoomInButton = document.getElementById('zoomIn');
    var zoomOutButton = document.getElementById('zoomOut');
    var moveLeftButton = document.getElementById('moveLeft');
    var moveRightButton = document.getElementById('moveRight');
    var moveUpButton = document.getElementById('moveUp');
    var moveDownButton = document.getElementById('moveDown');
    var image = document.getElementById('image');
    var form =document.querySelector('form'); // 이미지와 추가 데이터를 서버로 전송하는 form
    var cropper; // Cropper.js 인스턴스를 저장할 변수

    // 파일을 읽고 URL로 변환한 후 크롭 준비를 완료하는 함수
    var done = function (url) {
        // input.value = ''; //input 필드 초기화
        image.src = url; //읽은 파일의 url을 img 태그의 src로 설정
        imageContainer.style.display = 'block';

        //이미 cropper 인스턴스가 존재한다면 제거
        if (cropper) {
            cropper.destroy();
        }
        //Cropper.js 인스턴스 초기화
        cropper = new Cropper(image, {
            aspectRatio: 1 / 1,
            viewMode:3,
            zoomable: true,
            minCropBoxWidth: 100, // 최소 크롭 박스 너비를 100px로 설정
            minCropBoxHeight: 100, // 최소 크롭 박스 높이를 100px로 설정
        });
    };

    // 사용자가 파일을 input에 업로드하면 실행되는 이벤트 리스너
    input.addEventListener('change', function (e) {
        var files = e.target.files; // 선택된 파일 목록
        //파일이 선택되었는지 확인
        if (files && files.length > 0) {
            var reader = new FileReader(); //FileReader 인스턴스 생성
            //파일 읽기 성공 시 실행될 함수 설정
            reader.onload = function (e) {
                done(reader.result); //읽은 파일의 결과(URL)을 done 함수에 전달

            };
            reader.readAsDataURL(files[0]); //파일을 Data URL로  읽기
        }
    });

    zoomInButton.addEventListener('click',function (){
        if(cropper){
            cropper.zoom(0.1); // 줌인
        }
    });

    zoomOutButton.addEventListener('click',function (){
        if(cropper){
            cropper.zoom(-0.1); //줌 아웃
        }
    });

    // 버튼 이벤트 리스너 추가
    moveLeftButton.addEventListener('click', function() {
        if (cropper) {
            cropper.move(-10, 0); // 왼쪽으로 10px 이동
        }
    });

    moveRightButton.addEventListener('click', function() {
        if (cropper) {
            cropper.move(10, 0); // 오른쪽으로 10px 이동
        }
    });

    moveUpButton.addEventListener('click', function() {
        if (cropper) {
            cropper.move(0, -10); // 위로 10px 이동
        }
    });

    moveDownButton.addEventListener('click', function() {
        if (cropper) {
            cropper.move(0, 10); // 아래로 10px 이동
        }
    });


    //폼 제출시 실행되는 이벤트 리스터
    form.addEventListener('submit', function (e){
        e.preventDefault(); // 기본 폼 제출 동작 금지

        //크롭된 이미지를 Blob 형태로 변환
        cropper.getCroppedCanvas().toBlob(function (blob){
            var formData = new FormData(form); //현재 폼의 데이터를 기반으로 fom
        formData.append('croppedImage', blob, 'croppedImage.jpg'); //크롭된 이미지의 데이터를 추가
        // 서버로 formData 전송하는 로직 추가

        //FormData를 폼한하는 POST 요청으로 서버에 데이터 전송
        fetch(form.action, {
            method:'POST',
            body: formData,

        })

            .then(response => response.text()) //응답을 텍스틀 변환
            .then(success => {
                console.log(success); //성공 메시지 로깅
                //여기서 업로드 성공 후 처리를 구현할 수 있습니다.
            })
            .catch(error => {
                console.error('Error:', error); // 에러 로깅
                //여기서 업로드 실패 처리를 구현할 수 있습니다.
            });
        });
    });
});