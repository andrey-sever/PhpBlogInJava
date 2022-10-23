//Нажатие кнопки авторизация
document.querySelector('#authorization-submit').onclick = function (event) {
    event.preventDefault();
    let data = $('#form-wrapper form').serialize();
//    let login = document.querySelector('#login').value;
//    let pass = document.querySelector('#pass').value;
//
//    let data = {
//        "login": login,
//        "pass": pass
//    }

    $.post("/authorization", data, function(result) {
        authorization(result);
    });

    function authorization(result) {
        if (result != '1') {
            $('#errorBlock').show();
            $('#errorBlock').text(result);
        } else {
            $('#errorBlock').hide();
            document.location.reload(true);
        }
    }
}

//выход из кабинета
document.querySelector('#exit_authorization').onclick = function (event) {
    event.preventDefault();

    $.post("/delete_cookies", {}, function(result) {
        exitAuthorization(result);
    });

    function exitAuthorization(result) {
        location.href = "/";
    }
}

