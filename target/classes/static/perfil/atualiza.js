const formm = document.querySelector("form");
const nomee = document.querySelector(".nome");
const emaill = document.querySelector(".email");
const telefonee = document.querySelector(".telefone");
const descricaoo = document.querySelector("#descricao");
const dtaberturaa = document.querySelector(".abertura");
const msgerroo = document.getElementById("mensagem-erro");
const msgsucessoo = document.getElementById("mensagem-sucesso");
const desativarr = document.querySelector(".btdesativar")
const editarr = document.querySelector("bteditar");

const userId = localStorage.getItem('userId');

desativar.addEventListener('click', desativarConta);

fetch(`http://localhost:8080/userRegistration/${userId}`)
    .then(function (res){
        if (res.status === 200){
            return res.json();
        }else{
            mensagemErro.innerHTML = "Não foi possível carregar os dados";
            mensagemErro.style.display = "block"; 
        }
    })
    .then(function (userData){
        nome.value = userData.nome;
        email.value = userData.email;
        telefone.value = userData.telefone;
        descricao.value = userData.descricao;
        dtabertura.value = userData.accountCreationDate;

        console.log(userData);
    })
    .catch(function (error){
        mensagemErro.innerHTML = "Não foi possível carregar os dados";
        mensagemErro.style.display = "block";           
    });

function desativarConta() {
    fetch(`http://localhost:8080/userRegistration/${userId}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (response.ok) {
          window.location.href = "/cgfinancas/static/login/login.html";
          return response.json();
        } else {
          throw new Error('Não foi possível excluir o usuário');
        }
      })
      .then(data => {
        console.log('Usuário excluído com sucesso', data);
      })
      .catch(error => {
        console.error('Erro ao excluir o usuário', error);
      });
};

form.addEventListener('submit', atualizarConta);

function atualizarConta(){
 
    fetch(`http://localhost:8080/userRegistration/${userId}`,
    {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "PUT",
        body: JSON.stringify({
            nome: nome.value,
            email: email.value,
            telefone: telefone.value,
            descricao: descricao.value
        })
    })
    .then(function (res){
        if (res.status === 201){
            msgsucesso.innerHTML = "Conta atualizada com sucesso!";
            msgsucesso.style.display = "block";
        }else if (res.status === 500){
            msgerro.innerHTML = "Não foi possívelo atualizar os dados da conta!";
            msgerro.style.display = "block";
        } else{
            msgerro.innerHTML = "Não foi possívell atualizar os dados da conta!";
            msgerro.style.display = "block";
        }
    })
    .catch(function (res){
        msgerro.innerHTML = "Não foi possívelu atualizar os dados da conta!";
        msgerro.style.display = "block";
    })

    fetch(`http://localhost:8080/userRegistration/${userId}`)
    .then(function (res){
        if (res.status === 200){
            return res.json();
        }else{
            mensagemErro.innerHTML = "Não foi possível carregar os dados";
            mensagemErro.style.display = "block"; 
        }
    })
    .then(function (userData){
        nome.value = userData.nome;
        email.value = userData.email;
        telefone.value = userData.telefone;
        descricao.value = userData.descricao;
        dtabertura.value = userData.accountCreationDate;

        console.log(userData);
    })
    .catch(function (error){
        mensagemErro.innerHTML = "Não foi possível carregar os dados";
        mensagemErro.style.display = "block";           
    });
};