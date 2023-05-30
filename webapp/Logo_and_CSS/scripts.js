
function verificaPassword(x) {
    if (x.length < 8)
    return false;
    var maiuscolaRGEX = /[A-Za-z]/;
    if (!maiuscolaRGEX.test(x))
    return false;
    var specRGEX = /[!.@?$;*_-]/;
    if (!specRGEX.test(x))
    return false;
    var numeroRGEX = /[0-9]/;
    if (!numeroRGEX.test(x))
    return false;
    return true;
}

    function validate() {
        var password = document.getElementById('password3').value;
        var passwordResult = verificaPassword(password);
        if (passwordResult) {
            document.getElementById('passCheck').innerHTML = "Password Sicura";
            document.getElementById('passCheck').style = "background-color: greenyellow";
            document.getElementById('logSubmit2').style = "display: block";
        } else if (password.length > 0){
            document.getElementById('passCheck').innerHTML = "Password Non Sicura";
            document.getElementById('passCheck').style = "background-color: red";
            document.getElementById('logSubmit2').style = "display: none";
        } else {
            document.getElementById('passCheck').innerHTML = "Nessuna password inserita";
            document.getElementById('passCheck').style = "background-color: lightgray";
            document.getElementById('logSubmit2').style = "display: none";
        }
    }

    function hidePSWD(){
        var checkboxValue = document.getElementById('hidePSWD2');
        if (checkboxValue.checked) {
            document.getElementById('password3').type = "text";
        }
        else
            document.getElementById('password3').type = "password";

    }

    function changeFirstImage(id, i){
        document.getElementById('FirstImg').setAttribute("src", "./ImgServlet?id=" + id + "&imgNumber=" + i);
    }