document.getElementById('formTReg').addEventListener('submit', function(e) {
	// Limpiar errores previos
	document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');
	document.querySelectorAll('.input-error').forEach(el => el.classList.remove('input-error'));
	
	let isValid = true;

	// Validar nombre
	const nombre = document.getElementById('nombre');
	if (!nombre.value.trim()) {
		nombre.classList.add('input-error');
		document.getElementById('errorNombre').style.display = 'block';
		isValid = false;
	}

	// Validar descripcion
	const desc = document.getElementById('descripcion');
	if (!desc.value.trim()) {
		desc.classList.add('input-error');
		document.getElementById('errorDescripcion').style.display = 'block';
		isValid = false;
	}

	// Validar apellido
	const costo = document.getElementById('costo');
	const valorCosto = parseFloat(costo.value);
	if (!costo.value.trim() || isNaN(valorCosto) || valorCosto < 0) {
	    costo.classList.add('input-error');
	    const errorDiv = document.getElementById('errorCosto');
	    errorDiv.textContent = !costo.value.trim() ? "El costo es requerido" : "Debe ingresar un costo válido mayor a 0";
	    errorDiv.style.display = 'block';
	    isValid = false;
	}

	// Validar fecha
	const cupo = document.getElementById('cupo');
	const valorCupo = parseInt(cupo.value);
	if (!cupo.value.trim() || isNaN(valorCupo) || valorCupo <= 0) {
	    cupo.classList.add('input-error');
	    const errorDiv = document.getElementById('errorCupo');
	    errorDiv.textContent = !cupo.value.trim() ? "El cupo es requerido" : "Debe ingresar un cupo válido mayor a 0";
	    errorDiv.style.display = 'block';
	    isValid = false;
	}
	
	if (!isValid) {
	    e.preventDefault(); // Esto evita que el formulario se envíe
	}
});