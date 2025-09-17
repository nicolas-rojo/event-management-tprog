document.addEventListener("DOMContentLoaded", function() {
    localStorage.setItem("isLogged", "true");
    localStorage.setItem("usrRole", "organizador");


    const isLogged = localStorage.getItem("isLogged");
    const tipoCorrecto = localStorage.getItem("usrRole");

    
    // obtenemos el DIV contenedor de cada botón
    const botonDiv1 = document.getElementById("botondiv1");
    
    if (tipoCorrecto === "organizador" && isLogged === "true") {
        botonDiv1.style.display = "flex";
    } else {
        botonDiv1.style.display = "none";
    }
})