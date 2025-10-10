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

	// Validar costo
	const costo = document.getElementById('costo');
	const valorCosto = parseFloat(costo.value);
	const errorDiv = document.getElementById('errorCosto');

		if (!costo.value.trim()) {
		    errorDiv.textContent = "El costo es requerido";
		    errorDiv.style.display = 'block';
		    costo.classList.add('input-error');
		    isValid = false;
		} else if (isNaN(valorCosto) || valorCosto <= 0) {
		    errorDiv.textContent = "Debe ingresar un costo válido positivo";
		    errorDiv.style.display = 'block';
		    costo.classList.add('input-error');
		    isValid = false;
		} else {
		    errorDiv.style.display = 'none';
		    costo.classList.remove('input-error');
		}
		

	// Validar cupo
	const cupo = document.getElementById('cupo');
	const valorCupo = parseInt(cupo.value);
	const errorDiv1 = document.getElementById('errorCupo');

	if (!cupo.value.trim()) {
	    errorDiv1.textContent = "El cupo es requerido";
	    errorDiv1.style.display = 'block';
	    cupo.classList.add('input-error');
	    isValid = false;
	} else if (isNaN(valorCupo) || valorCupo <= 0) {
	    errorDiv1.textContent = "Debe ingresar un cupo válido mayor a 0";
	    errorDiv1.style.display = 'block';
	    cupo.classList.add('input-error');
	    isValid = false;
	} else {
	    errorDiv1.style.display = 'none';
	    cupo.classList.remove('input-error');
	}
	
	if (!isValid) {
	    e.preventDefault(); // Esto evita que el formulario se envíe
	}
});
	
document.querySelectorAll('input, textarea').forEach(element => {
	element.addEventListener('input', function() {
		this.classList.remove('input-error');
		const errorId = 'error' + this.id.charAt(0).toUpperCase() + this.id.slice(1);
		const errorElement = document.getElementById(errorId);
		if (errorElement) {
			errorElement.style.display = 'none';
		}
	});
});