document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("formInstitucion");
    const nombre = document.getElementById("nombre");
    const sitioWeb = document.getElementById("sitioWeb");
    const descripcion = document.getElementById("descripcion");

    // Elementos de error
    const errorNombre = document.getElementById("errorNombre");
    const errorSitioWeb = document.getElementById("errorSitioWeb");
    const errorDescripcion = document.getElementById("errorDescripcion");
    const confirmarError = document.getElementById("confirmarError");

    form.addEventListener("submit", function(event) {
        let isValid = true;
        confirmarError.textContent = "";

        // Validar nombre
        if (nombre.value.trim() === "") {
            errorNombre.style.display = "block";
            nombre.classList.add("input-error");
            isValid = false;
        } else {
            errorNombre.style.display = "none";
            nombre.classList.remove("input-error");
        }

        // Validar sitio web
        if (sitioWeb.value.trim() === "") {
            errorSitioWeb.style.display = "block";
            sitioWeb.classList.add("input-error");
            isValid = false;
        } else {
            errorSitioWeb.style.display = "none";
            sitioWeb.classList.remove("input-error");
        }

        // Validar descripción
        if (descripcion.value.trim() === "") {
            errorDescripcion.style.display = "block";
            descripcion.classList.add("input-error");
            isValid = false;
        } else {
            errorDescripcion.style.display = "none";
            descripcion.classList.remove("input-error");
        }

        if (!isValid) {
            event.preventDefault();
            confirmarError.textContent = "Por favor, complete todos los campos correctamente.";
        }
    });

    // Limpiar errores al escribir
    nombre.addEventListener("input", function() {
        if (nombre.value.trim() !== "") {
            errorNombre.style.display = "none";
            nombre.classList.remove("input-error");
        }
    });

    sitioWeb.addEventListener("input", function() {
        if (sitioWeb.value.trim() !== "") {
            errorSitioWeb.style.display = "none";
            sitioWeb.classList.remove("input-error");
        }
    });

    descripcion.addEventListener("input", function() {
        if (descripcion.value.trim() !== "") {
            errorDescripcion.style.display = "none";
            descripcion.classList.remove("input-error");
        }
    });
});