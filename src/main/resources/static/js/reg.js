//Нажатие кнопки регистрация
document.querySelector('#registration-submit').onclick = function (event) {
    event.preventDefault();
    let name = document.querySelector('#name').value;
    let login = document.querySelector('#login').value;
    let email = document.querySelector('#email').value;
    let pass = document.querySelector('#pass').value;

    let data = {
        "name": name,
        "login": login,
        "email": email,
        "pass": pass
    }

    $.post("/registration", data, function(result) {
        registration(result)
    });

    function registration(result) {
        if (result != '1') {
            $('#errorBlock').show();
            $('#errorBlock').text(result);
        } else {
            $('#errorBlock').hide();
            $('#registration-submit').hide();
            $('#successBlock').show();
        }
    }
}
