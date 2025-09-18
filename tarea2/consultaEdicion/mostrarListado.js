
document.addEventListener("DOMContentLoaded", function () {

    const isLogged = localStorage.getItem("isLogged");
    const tipoCorrecto = localStorage.getItem("usrRole");
    const nombreOrganizador = localStorage.getItem("usrName");

   const listaEdiciones = [
    { organizador: "Intendencia de Montevideo", edicion: "Maratón de Montevideo 2022" },
    { organizador: "Intendencia de Montevideo", edicion: "Maratón de Montevideo 2024" },
    { organizador: "Corporación Tecnológica", edicion: "Mobile World Congress 2025" },
    { organizador: "Corporación Tecnológica", edicion: "Web Summit 2026" },
    { organizador: "Universidad de la República", edicion: "Tecnología Punta del Este 2026" }
];

const heap = {};
listaEdiciones.forEach(item => {
    heap[item.edicion] = item.organizador;
});

    const nombreEdicionElem = document.getElementById("nombreMaraton");
    const nombreEdicion = nombreEdicionElem.textContent; 
    localStorage.setItem("ediciones", JSON.stringify(listaEdiciones));


    if (tipoCorrecto === "organizador" && isLogged === "true" && nombreOrganizador === heap[nombreEdicion]) {
        listado.style.display = "flex";
        textoListadoRegistro.style.display = "block";
    } else {
        listado.style.display = "none";
        textoListadoRegistro.style.display = "none";
    }
});