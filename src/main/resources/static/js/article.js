//Нажатие кнопки добавить. Статью
document.querySelector('#article-submit').onclick = function (event) {
    event.preventDefault();
    let data = $('#article-add form').serialize();
//    let title = document.querySelector('#title').value;
//    let intro = document.querySelector('#intro').value;
//    let text = document.querySelector('#text').value;
//
//    let data = {
//        "title": title,
//        "intro": intro,
//        "text": text
//    }

    $.post("/article_add", data, function(result) {
        articleAdd(result)
    });

    function articleAdd(result) {
        if (result != '1') {
            $('#errorBlock').show();
            $('#errorBlock').text(result);
        } else {
            $('#errorBlock').hide();
            document.querySelector('#article-submit').style.visibility = 'hidden';
            $('#successBlock').show();
        }
    }
}
