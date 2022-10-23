document.querySelector('#message-submit').onclick = function (event) {
    event.preventDefault();

    let login = getCookieLogin();
    let message = document.querySelector('#message').value;
    let articleId = getIdFromUrl();

    let data = {
        "login": login,
        "message": message,
        "articleId": articleId
    }

    $.post("/comment_add", data, function (result) {
        commentAdd(result);
    });

    function commentAdd(result) {
        if (result != '1') {
            $('#errorBlock').show();
            $('#errorBlock').text(result);
        } else {
            $('#errorBlock').hide();
            document.location.reload(true);
        }
    }
}