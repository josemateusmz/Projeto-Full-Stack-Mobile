const form = document.querySelector("form");
const botao_logar = document.querySelector("button");
const email = document.querySelector(".email");
const senha = document.querySelector(".senha");
const msgerro = document.getElementById("mensagem-erro");


function logar() {

    fetch("http://localhost:8080/login",
    {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify({
            email: email.value,
            senha: senha.value
        })
    })
    .then(function (res) {
     if (res.status === 201) {
        //window.location.href = "/home/index.html";
        return res.json();
     }else{
        msgerro.innerHTML = "Email e/ou senha inválido(os)";
        msgerro.style.display = "block";
     }
     console.log(res)
     })
    .then(function (data) {
        localStorage.setItem('userId', data.idUsuario);
        window.location.href = "perfil/perfil.html";
    })
};

form.addEventListener('submit', function(event){
    event.preventDefault();
    logar();
});