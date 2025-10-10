const form = document.getElementById('formEdicion');
const confirmarError = document.getElementById('confirmarError');

form.addEventListener('submit', function(e) {
    document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');
    document.querySelectorAll('.input-error').forEach(el => el.classList.remove('input-error'));
    confirmarError.innerText = '';

    let isValid = true;

    const nombre = document.getElementById('nombreEd');
    const sigla = document.getElementById('sigla');
    const ciudad = document.getElementById('ciudad');
    const pais = document.getElementById('pais');
    const fechaIni = document.getElementById('fechaIni');
    const fechaFin = document.getElementById('fechaFin');

    if (!nombre.value.trim()) { nombre.classList.add('input-error'); document.getElementById('errorNombreEd').style.display='block'; isValid=false; }
    if (!sigla.value.trim()) { sigla.classList.add('input-error'); document.getElementById('errorSigla').style.display='block'; isValid=false; }
    if (!ciudad.value.trim()) { ciudad.classList.add('input-error'); document.getElementById('errorCiudad').style.display='block'; isValid=false; }
    if (!pais.value.trim()) { pais.classList.add('input-error'); document.getElementById('errorPais').style.display='block'; isValid=false; }
    if (!fechaIni.value) { fechaIni.classList.add('input-error'); document.getElementById('errorFechaIni').style.display='block'; isValid=false; }
    if (!fechaFin.value) { fechaFin.classList.add('input-error'); document.getElementById('errorFechaFin').style.display='block'; isValid=false; }

    if (fechaIni.value && fechaFin.value) {
        if (fechaFin.value < fechaIni.value) {
            confirmarError.innerText = "La fecha de inicio no puede ser posterior a la fecha de fin";
            isValid = false;
        }
    }

    if (!isValid) e.preventDefault();
});

document.querySelectorAll('input').forEach(el => {
    el.addEventListener('input', function() {
        this.classList.remove('input-error');
        const errorEl = document.getElementById('error' + this.id.charAt(0).toUpperCase() + this.id.slice(1));
        if (errorEl) errorEl.style.display='none';
        confirmarError.innerText = '';
    });
});
