const form = document.querySelector("form");
const botao_cadastrar = document.querySelector("button");
const nome = document.querySelector(".nome");
const email = document.querySelector(".email");
const senha1 = document.querySelector(".senha1");
const senha2 = document.querySelector(".senha2");

const msgsucesso = document.getElementById("mensagem-sucesso");
const msgerro = document.getElementById("mensagem-erro");

botao_cadastrar.disabled = true;

function validaSenha(){
    if (senha1.value === senha2.value){
        botao_cadastrar.disabled = false;
        senha2.style.backgroundColor = "initial";
    }else {
        botao_cadastrar.disabled = true;
        senha2.style.backgroundColor = "rgba(255, 0, 0, 0.2)";
    }
};

senha1.addEventListener("input", validaSenha);
senha2.addEventListener("input", validaSenha);

function cadastrar() {

    fetch("http://localhost:8080/userRegistration",
    {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify({
            nome: nome.value,
            email: email.value,
            senha1: senha1.value,
            senha2: senha2.value
        })
    })
    .then(function (res){
        if (res.status === 201){
            msgsucesso.innerHTML = "Conta criada com sucesso!";
            msgsucesso.style.display = "block";
        }else if (res.status === 500){
            msgerro.innerHTML = "Já exite uma conta cadastrada para este email!";
            msgerro.style.display = "block";
        } else{
            msgerro.innerHTML = "Um ou mais campos inválidos!";
            msgerro.style.display = "block";
        }
    })
    .catch(function (res){
        msgerro.innerHTML = "Um ou mais campos inválidos!";
        msgerro.style.display = "block";
    })
};

function limpar() {
    nome.value ="";
    email.value ="";
    senha1.value ="";
    senha2.value ="";
};

form.addEventListener('submit', function(event){
    event.preventDefault();

  validaSenha();

  if (!botao_cadastrar.disabled) {
    cadastrar();
    limpar();
  }
});