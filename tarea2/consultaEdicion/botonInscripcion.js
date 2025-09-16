document.addEventListener("DOMContentLoaded", function () {
    const tipoCorrecto = true;
    const noInscripto = true;
    // obtenemos el DIV contenedor de cada botón
    const botonesDiv = document.querySelectorAll(".boton-div");
    botonesDiv.forEach(div => {
        if (tipoCorrecto && noInscripto) {
            div.style.display = "flex";
        } else {
            div.style.display = "none";
        }
    });


});
