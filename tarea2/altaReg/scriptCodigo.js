document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formTReg");
    const cancelarBtn = document.getElementById("cancelarBtn");
    const aplicarBtn = document.getElementById("aplicarBtn");

    const params = new URLSearchParams(window.location.search);
    const redirect = params.get("redirect");

    const codigoInput = document.getElementById("codigo");
    const costoRegistro = document.getElementById("costoRegistro");
    const confirmarError = document.getElementById('confirmarError');

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        localStorage.setItem("atorresRegistradaPE", "true");
        window.location.href = redirect || "../consultaEdicion/consultaEdicionTecPunta.html";
    });

    cancelarBtn.addEventListener("click", () => {
        window.location.href = redirect || "../consultaEdicion/consultaEdicionTecPunta.html";
    });

    aplicarBtn.addEventListener("click", () => {
        const codigoCorrecto = codigoInput.value.trim() === "TECHUDELAR";
        codigoInput.disabled = false;
        if (codigoCorrecto) {
            costoRegistro.value = "$0";
            confirmarError.innerText = "";
            codigoInput.disabled = true;
        } else {
            confirmarError.innerText = "Código inválido";
        }
    });
});