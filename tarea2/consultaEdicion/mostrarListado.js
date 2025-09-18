
document.addEventListener("DOMContentLoaded", function () {

    const isLogged = localStorage.getItem("isLogged");
    const tipoCorrecto = localStorage.getItem("usrRole");
    const nombreOrganizador = localStorage.getItem("usrName");

    if (tipoCorrecto === "organizador" && isLogged === "true" && nombreOrganizador ) {
        listado.style.display = "flex";
        textoListadoRegistro.style.display = "block";
    } else {
        listado.style.display = "none";
        textoListadoRegistro.style.display = "none";
    }
});