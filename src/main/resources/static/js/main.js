//Загрузка частей кода html
document.addEventListener('DOMContentLoaded', function () {
    $("#includedHeader").load("/parts/header.html");
    $("#includedFooter").load("/parts/footer.html");
    $("#includedAside").load("/parts/aside.html");
    checkCookies(blockButton);
    if (document.location.pathname == "/") outputListArticles();
    if (document.location.pathname == "/auth.html") checkCookies(blockFormsCabinet);
    if (document.location.pathname == "/news.html") outputArticle();
});

//Вход по кукам
function checkCookies(funcResult) {
    $.post("/login_by_cookies", {}, function (result) {
        funcResult(result);
    });
}

//Если куки есть, то открыть кнопки кабинета, иначе стандартные
function blockButton(result) {
    if (result == '') {
        $('#wrapper-in-button').show();
    } else {
        $('.wrapper-cabinet-button').show();
        $('#wrapper-cabinet-article').show();
    }
}

//Если куки есть, то вывод логина, нет форма авторизации
function blockFormsCabinet(result) {
    if (result == '') {
        $('#form-wrapper').show();
    } else {
        document.querySelector('#authorization-user').outerHTML = result.name;
        $('#authorization-user-wrapper').show();
    }
}

//Получить логин по куки
function getCookieLogin() {
    let cookie = document.cookie;
    if (cookie != '') {
        cookie = cookie.replace('login=', '')
    }
    return cookie;
}

function getIdFromUrl() {
    return Number(document.location.search.replace('?id=', ''))
}

//Вывод списка статей на странице index
function outputListArticles() {
    $.post("/list_articles", {}, function (result) {
        listPerPage(result);
    });

    function listPerPage(result) {
        for (let obj of result) {
            articlesHTML = '<h2>' + obj.title + '</h2>'
                + '<p>' + obj.intro + '</p>'
                + '<p><b>Автор статьи:</b><mark>' + obj.author + '</mark></p>'
                + '<a href=\"/news.html?id=' + obj.id + '\">'
                + '<button class="btn btn-warning mb-5">Прочитать больше</button>'
                + '</a>';
            document.querySelector('#articles-list').innerHTML += articlesHTML;
        }
    }
}

//Вывод статьи на странице news
function outputArticle() {
    let id = getIdFromUrl();
    $.get("/article_one/" + id, {}, function (result) {
        articleOut(result);
    });

    $.post("/comments_list", {"id": id}, function (result) {
        commentsList(result);
    });

    function articleOut(result) {
        let date = new Date(result.date);
        let monthsRus = ["Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября",
            "Октября", "Ноября", "Декабря"];
        let timePublic = date.getDate() + ' ' + monthsRus[date.getMonth()] + ' ' + date.getFullYear() +
            ' ' + date.getHours() + ':' + date.getMinutes();
        articleHTML = '<h1>' + result.title + '</h1>'
            + '<p><b>Автор статьи:</b><mark>' + result.author + '</mark></p>'
            + '<p><b>Время публикации: </b><u>' + timePublic + '</u></p>'
            + '<p>'
            + result.intro
            + '<br><br>'
            + result.text
            + '</p>';
        document.querySelector('#article-one').innerHTML += articleHTML;

        enteringCommentByLogin(result.author);
    }

    // Выводить или нет форму оставления комментов
    function enteringCommentByLogin(author) {
        let errorMessage = '';
        let currentLogin = getCookieLogin();
        if (currentLogin == '') {
            errorMessage = 'Чтобы оставлять комментарий нужно зарегистрироваться.';
        } else if (currentLogin == author) {
            errorMessage = 'Нельзя оставлять комментарий под своей статьей.';
        }

        if (errorMessage != '') {
            document.querySelector('#wrapper-add-comment').innerHTML =
                '<div class="alert alert-warning" role="alert">' + errorMessage + '</div>';
        }
        $('#wrapper-add-comment').show();
    }

    function commentsList(result) {
        for (let obj of result) {
            articlesHTML = '<div class="alert alert-info mb-2">'
                + '<h4>' + obj.login + '</h4>'
                + '<p>' + obj.message + '</p>'
                + '</div>';
            document.querySelector('#message-list').innerHTML += articlesHTML;
        }
    }
}
