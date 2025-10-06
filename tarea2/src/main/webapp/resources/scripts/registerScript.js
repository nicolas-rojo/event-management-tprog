document.addEventListener('DOMContentLoaded', () => {
    const tipoSelect = document.getElementById('tipo');
    const camposOrg = document.getElementById('Campos-Org');
    const camposAsist = document.getElementById('Campos-Asist');
    const campoDesc = document.getElementById('desc');
    const campoUrl = document.getElementById('url');
    const campoApellido = document.getElementById('apellido');
    const campoFecha = document.getElementById('fechaNac');

    function actualizarCampos() {
        if (tipoSelect.value === 'organizador') {
            camposOrg.style.display = 'block';
            camposAsist.style.display = 'none';
            campoDesc.value = "";
            campoUrl.value = "";
            campoApellido.value = 'default';
            campoFecha.value = '0001-01-01';

        } else if (tipoSelect.value === 'asistente') {
            camposOrg.style.display = 'none';
            camposAsist.style.display = 'block';
            campoDesc.value = 'default';
            campoUrl.value = 'default';
            campoApellido.value = "";
            campoFecha.value = null;
        } else {
            camposOrg.style.display = 'none';
            camposAsist.style.display = 'none';
        }
    }

    // Ejecuta al cambiar la selección
    tipoSelect.addEventListener('change', actualizarCampos);

    // Ejecuta al cargar la página para mostrar los campos
    actualizarCampos();

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        if (password.value !== confirmacion.value) {
            confirmarError.innerText = "Las Contraseñas No Coinciden";
        } else {
            confirmarError.innerText = "";
			form.submit();
        }
    });
});