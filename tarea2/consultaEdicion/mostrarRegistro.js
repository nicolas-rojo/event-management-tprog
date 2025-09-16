/* script para mostrar o no el registro dependiendo del tipo de usuario*/
document.addEventListener("DOMContentLoaded", function() {
    const tipoCorrecto = true; 
    const inscripto = true;
    const registro = document.getElementById("registro");
    const textoRegistro = document.getElementById("tr");

    if (tipoCorrecto && inscripto) {
        registro.style.display = "flex";
        textoRegistro.style.display = "block";
    } else {
        registro.style.display = "none";
        textoRegistro.style.display = "none";
    }
});