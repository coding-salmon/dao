document.addEventListener('DOMContentLoaded', function (){
    var imageContainer = document.getElementById('imageContainer'); // 크롭될 이미지를 표시할 img 태그
    var input =document.getElementById('pic'); // 파일을 업로드하는 input 태그
    var confirmButton = document.getElementById('confirm'); // 크롭된 영역을 선택하는 버튼
    var zoomInButton = document.getElementById('zoomIn');
    var zoomOutButton = document.getElementById('zoomOut');
    var moveLeftButton = document.getElementById('moveLeft');
    var moveRightButton = document.getElementById('moveRight');
    var moveUpButton = document.getElementById('moveUp');
    var moveDownButton = document.getElementById('moveDown');
    var image = document.getElementById('image');
    var form =document.querySelector('form'); // 이미지와 추가 데이터를 서버로 전송하는 form
    var cropper; // Cropper.js 인스턴스를 저장할 변수
    var originalImageFile; // 원본이미지를 저장할 변수
    var message = "${session.getAttribute('message')}";
    var modal = document.getElementById('croppedImageModal'); // 모달 창 참조 추가
    var closeModal  = document.getElementById('closeModal'); // 닫기 버튼 참조 추가
    var previewCroppedImage = document.getElementById('previewCroppedImage'); // 크롭된 이미지 미리보기 참조 추가

    input.value = ''; //input 필드 초기화

    // 파일을 읽고 URL로 변환한 후 크롭 준비를 완료하는 함수
    var done = function (url) {

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
            originalImageFile = files[0]; //원본 이미지 파일 저장

            var reader = new FileReader(); //FileReader 인스턴스 생성
            //파일 읽기 성공 시 실행될 함수 설정
            reader.onload = function (e) {
                done(reader.result); //읽은 파일의 결과(URL)을 done 함수에 전달

            };
            reader.readAsDataURL(originalImageFile); //파일을 Data URL로  읽기
        }
    });

    zoomInButton.addEventListener('click',function (){
        if(cropper){
            cropper.zoom(0.1); // 줌인
            checkZoomLevel(); // 줌 레벨 확인
        }
    });

    zoomOutButton.addEventListener('click',function (){
        if(cropper){
            cropper.zoom(-0.1); //줌 아웃
            checkZoomLevel(); // 줌 레벨 확인
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

    //줌 레벨을 확인하여 해당하는 작업을 수행하는 함수
    var checkZoomLevel =function (){
        var currentZoomLevel = cropper.getData().scaleX; //현재 줌 레벨 가져오기
        console.log(currentZoomLevel);
        // document.getElementById('zoomLevel').value = currentZoomLevel; // 숨겨진 필드 값 업데이트
    };

    var croppedImageDataURL; // 크롭된 이미지를 Base64 형태로 저장하는 변수

    // pic 자르기 버튼 클릭 시 실행되는 이벤트 리스너
    confirmButton.addEventListener('click', function (){
        if(cropper){
            modal.style.display="block"; //모달 창 표시
            var croppedImageDataURL = cropper.getCroppedCanvas().toDataURL();


            previewCroppedImage.src = croppedImageDataURL; // 크롭된 이미지 미리보기에 표시



            // <span> 요소(닫기 버튼)를 클릭하면 모달을 닫습니다.


            closeModal .onclick = function() {
                modal.style.display = "none";
                document.getElementById('cropStatusMessage').innerHTML = '픽 자르기 완료.';
                document.getElementById('cropStatusMessage').style.display = 'block';


            }
            // 모달 외부 영역을 클릭하면 모달을 닫습니다.
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                    document.getElementById('cropStatusMessage').innerHTML = '픽 자르기 완료.';
                    document.getElementById('cropStatusMessage').style.display = 'block';


                }
            }



            // 크롭된 이미지 데이터 URL을 콘솔에 출력 (디버깅 목적)
            console.log(croppedImageDataURL);
        }
    })



    //폼 제출시 실행되는 이벤트 리스터
    form.addEventListener('submit', function (e){
        e.preventDefault(); // 기본 폼 제출 동작 금지

        //크롭된 이미지를 Blob 형태로 변환
        cropper.getCroppedCanvas().toBlob(function (blob){
            var formData = new FormData(form); //현재 폼의 데이터를 기반으로 fom
        formData.append('originalImage', originalImageFile); // 원본 이미지 추가
        formData.append('croppedImage', blob, 'croppedImage.jpg'); //크롭된 이미지의 데이터를 추가
        // 서버로 formData 전송하는 로직 추가

        //FormData를 포함하는 POST 요청으로 서버에 데이터 전송
        fetch(form.action, {
            method:'POST',
            body: formData,

        });



        });
    });
});