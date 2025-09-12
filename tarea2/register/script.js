document.addEventListener('DOMContentLoaded', () => {
    const tipoSelect = document.getElementById('tipo');
    const camposOrg = document.getElementById('Campos-Org');
    const camposAsist = document.getElementById('Campos-Asist');

    
    function actualizarCampos() {
        if (tipoSelect.value === 'Organizador') {
            camposOrg.style.display = 'block';
            camposAsist.style.display = 'none';
        } else if (tipoSelect.value === 'Asistente') {
            camposOrg.style.display = 'none';
            camposAsist.style.display = 'block';
        } else {
            camposOrg.style.display = 'none';
            camposAsist.style.display = 'none';
        }
    }
    
    // Ejecuta al cambiar la selección
    tipoSelect.addEventListener('change', actualizarCampos);
    
    // Ejecuta al cargar la página para mostrar los campos
    actualizarCampos();
    
    const form = document.querySelector('form');
    const password = document.getElementById('password');
    const confirmacion = document.getElementById('confirmacion');

    form.addEventListener('submit', (e) => {
        if (password.value !== confirmacion.value) {
            e.preventDefault();
            alert('Las contraseñas no coinciden');
            confirmacion.focus();
        }
    });
});