document.addEventListener("DOMContentLoaded", function (){
    fetchRandomImage();
});

function fetchRandomImage(){
    fetch('API_ENDPOINT/random')
        .then(response => response.json())
        .then(data => {
            document.getElementById('original').src = data.original_image_url;
            document.getElementById('zoomed').src = data.zoomed_image_url;
        })
        .catch(error => console.error('Error: error'));
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