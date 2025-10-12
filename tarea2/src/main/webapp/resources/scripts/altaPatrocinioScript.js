document.getElementById('formPatrocinio').addEventListener('submit', function(e) {
    // Limpiar errores previos
    document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');
    document.querySelectorAll('.input-error').forEach(el => el.classList.remove('input-error'));
    document.getElementById('confirmarError').innerText = '';
    
    let isValid = true;

    // Validar tipo de registro
    const tipoRegistro = document.getElementById('tipoRegistro');
    if (!tipoRegistro.value || tipoRegistro.value === "") {
        tipoRegistro.classList.add('input-error');
        document.getElementById('errorTipoRegistro').style.display = 'block';
        isValid = false;
    }

    // Validar institución
    const institucion = document.getElementById('institucion');
    if (!institucion.value || institucion.value === "") {
        institucion.classList.add('input-error');
        document.getElementById('errorInstitucion').style.display = 'block';
        isValid = false;
    }

    // Validar nivel
    const nivel = document.getElementById('nivel');
    if (!nivel.value || nivel.value === "") {
        nivel.classList.add('input-error');
        document.getElementById('errorNivel').style.display = 'block';
        isValid = false;
    }

    // Validar monto
    const monto = document.getElementById('monto');
    const valorMonto = parseFloat(monto.value);
    if (!monto.value.trim() || isNaN(valorMonto) || valorMonto <= 0) {
        monto.classList.add('input-error');
        const errorDiv = document.getElementById('errorMonto');
        errorDiv.textContent = !monto.value.trim() ? "El monto es requerido" : "Debe ingresar un monto válido mayor a 0";
        errorDiv.style.display = 'block';
        isValid = false;
    }

    // Validar cantidad de cupos
    const cantidadCupos = document.getElementById('cantidadCupos');
    const valorCupos = parseInt(cantidadCupos.value);
    if (!cantidadCupos.value.trim() || isNaN(valorCupos) || valorCupos <= 0) {
        cantidadCupos.classList.add('input-error');
        const errorDiv = document.getElementById('errorCantidadCupos');
        errorDiv.textContent = !cantidadCupos.value.trim() ? "La cantidad de cupos es requerida" : "Debe ingresar una cantidad válida mayor a 0";
        errorDiv.style.display = 'block';
        isValid = false;
    }

    // Validar código
    const codigo = document.getElementById('codigo');
    if (!codigo.value.trim()) {
        codigo.classList.add('input-error');
        document.getElementById('errorCodigo').style.display = 'block';
        isValid = false;
    }

    if (!isValid) {
        e.preventDefault();
        document.getElementById('confirmarError').innerText = "Por favor, complete todos los campos correctamente.";
    }
});

document.querySelectorAll('input, textarea, select').forEach(element => {
    element.addEventListener('input', function() {
        this.classList.remove('input-error');
        const errorId = 'error' + this.id.charAt(0).toUpperCase() + this.id.slice(1);
        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.style.display = 'none';
        }
        document.getElementById('confirmarError').innerText = '';
    });
});

// Para los select
document.querySelectorAll('select').forEach(element => {
    element.addEventListener('change', function() {
        this.classList.remove('input-error');
        const errorId = 'error' + this.id.charAt(0).toUpperCase() + this.id.slice(1);
        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.style.display = 'none';
        }
    });
});