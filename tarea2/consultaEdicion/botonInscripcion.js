document.addEventListener("DOMContentLoaded", function () {
    const isLogged = localStorage.getItem("isLogged");
    const tipoCorrecto = localStorage.getItem("usrRole") == "asistente";
    const nombreAsistente = localStorage.getItem("usrNickname");
    const nombreEdicionElem = document.getElementById("nombreMaraton");
    const botonesDiv = document.querySelectorAll(".boton-div");
    const inscripcionBoton = document.getElementById("boton1");

    botonesDiv.forEach(div => {
        const cond = (nombreAsistente == "atorres" && (nombreEdicionElem.textContent == "Maratón de Montevideo 2024" || nombreEdicionElem.textContent == "Web Summit 2026"));

        if (isLogged && tipoCorrecto && !cond ) {
            div.style.display = "flex";
        } else {
            div.style.display = "none";
        }
    });

    inscripcionBoton.addEventListener("click", (e) => {
        e.preventDefault();
        window.location.href = window.location.href = "../altaReg/altaReg.html";
    });


});
