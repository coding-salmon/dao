let hintCount = 0;
document.addEventListener("DOMContentLoaded", function (){
    console.log("Pic 준비 완료!")
    //페이지가 로드 될 때 랜덤 이미지를 로드하는 함수를 호출함
    fetchRandomPic();

});

// '/api/random-pic' 엔드포인트를 호출하여 이미지를 가져오는 함수
function fetchRandomPic(){
    fetch('/api/random-pic')
        .then(response => response.json())//서버로부터 JSON데이터를 받음
        .then(data => {
            //받은 JSON데이터에서 이미지 Blob URL과 다른 정보 추출
            const croppedImgElement  = document.getElementById('randomPic');
            croppedImgElement.src = `data:image/jpeg;base64,${data.croppedImage}`;
            croppedImgElement.style.display = 'block';

            // 원본 이미지 표시
            const originalImgElement = document.getElementById('originalPic');
            originalImgElement.src = `data:image/jpeg;base64,${data.originalImage}`;
            originalImgElement.style.display = 'none';


            const title = data.title;
            const category = data.category;
            const storeName =data.storeName;

            //<img>태그의 src 속성을 업데이트하여 이미지 표시
            document.getElementById('randomPic').src = croppedImgElement ;
            document.getElementById('originalImage').src = originalImageUrl;

            //다른 정보는 html 요소에 삽입하여 화면에 표시
            document.getElementById('title').innerText = title;
            document.getElementById('category').innerText = category;
            document.getElementById('storeName').innerText = storeName;
        })
        .catch(error => console.error('Error fetching the random pic:', error));
}

function checkAnswer(){
    const userAnser = document.getElementById('answer').value;
    fetch('API_ENDPOINT/check',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body:JSON.stringify({answer:useAnswer}),
    })
        .then(response => response.json())
        .then(data => {
            if(data.isCorrect){
                alert('정답입니다!');
            }else{
                alert('틀렸습니다. 다시 시도해보세요.');
            }
        })
        .catch(error => console.error('Error:',error))
}
// 끝내기 버튼 클릭 시
function endPic() {
    // 페이지를 새로고침하여 새로운 이미지를 불러옴
    location.reload();
}

// 힌트 버튼 클릭 시
function showPicHint() {
    // 힌트를 보여주는 로직을 여기에 추가
    if(hintCount === 0) {
        document.getElementById('category').style.display = 'block';
        hintCount++;
    }else if(hintCount ===1){
        document.getElementById('storeName').style.display = 'block';
        hintCount++;
    }
}

// 포기 버튼 클릭 시
function giveUp() {
    // 정답을 보여주는 로직을 여기에 추가
    document.getElementById('originalPic').style.display = 'block';
    document.getElementById('title').style.display = 'block';
}

// 다음Pic 버튼 클릭 시
function loadNextPic() {
    // 다음 Pic을 불러오는 로직을 fetchRandomPic 함수로 구현하여 호출
    fetchRandomPic();
    hintCount = 0;
}