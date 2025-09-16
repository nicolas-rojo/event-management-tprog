
document.addEventListener("DOMContentLoaded", function() {
    const tipoCorrecto = true;
    const organizadorDeEdicion = true; 
    const listado = document.getElementById("listado-registros");
    const textoListadoRegistro = document.getElementById("tlr");

    if (tipoCorrecto && organizadorDeEdicion) {
        listado.style.display = "flex";
        textoListadoRegistro.style.display = "block";
    } else {
        listado.style.display = "none";
        textoListadoRegistro.style.display = "none";
    }
});