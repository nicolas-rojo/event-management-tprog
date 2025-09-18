/* script para mostrar o no el registro dependiendo del tipo de usuario*/
document.addEventListener("DOMContentLoaded", function() {
    const isLogged = localStorage.getItem("isLogged");
    const tipoCorrecto = localStorage.getItem("usrRole");
    const nombreAsistente = localStorage.getItem("usrNickname");
    const nombreEdicionElem = document.getElementById("nombreMaraton");

    const registro = document.getElementById("registro");
    const textoRegistro = document.getElementById("tr");

    const cond = (nombreAsistente == "atorres" && (nombreEdicionElem.textContent == "Maratón de Montevideo 2024" || nombreEdicionElem.textContent == "Web Summit 2026"));

    if (tipoCorrecto =="asistente" && isLogged === "true" && cond) {
        registro.style.display = "flex";
        textoRegistro.style.display = "block";
    } else {
        registro.style.display = "none";
        textoRegistro.style.display = "none";
    }
});