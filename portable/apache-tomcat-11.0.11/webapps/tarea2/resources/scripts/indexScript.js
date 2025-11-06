
let eventoSeleccionado = "";

function abrirModalBaja(nombreEvento, e) {
    e.stopPropagation();
    e.preventDefault();

    eventoSeleccionado = nombreEvento;
    document.getElementById("nombreEventoBaja").textContent = nombreEvento;
    document.getElementById("modalBaja").classList.add("active");
}

function cerrarModalBaja() {
    document.getElementById("modalBaja").classList.remove("active");
    eventoSeleccionado = "";
}

function confirmarBaja() {
    if (eventoSeleccionado) {
        const formId = "form-" + eventoSeleccionado.replace(/\s+/g, "_");
        const form = document.getElementById(formId);
        if (form) form.submit();
    }
    cerrarModalBaja();
}

document.addEventListener("keydown", function(event) {
    if (event.key === "Escape") cerrarModalBaja();
});