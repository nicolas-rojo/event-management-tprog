document.getElementById('formEdicion').addEventListener('submit', function(e) {
	// Limpiar errores previos
	document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');
	document.querySelectorAll('.input-error').forEach(el => el.classList.remove('input-error'));

	let isValid = true;

	// Validar nombre
	const nombre = document.getElementById('nombreEd');
	if (!nombre.value.trim()) {
		nombre.classList.add('input-error');
		document.getElementById('errorNombreEd').style.display = 'block';
		isValid = false;
	}

	// Validar sigla
	const sigla = document.getElementById('sigla');
	if (!sigla.value.trim()) {
		sigla.classList.add('input-error');
		document.getElementById('errorSigla').style.display = 'block';
		isValid = false;
	}

	// Validar ciudad
	const ciudad = document.getElementById('ciudad');
	if (!ciudad.value.trim()) {
		ciudad.classList.add('input-error');
		document.getElementById('errorCiudad').style.display = 'block';
		isValid = false;
	}

	// Validar pais
	const pais = document.getElementById('pais');
	if (!pais.value.trim()) {
		pais.classList.add('input-error');
		document.getElementById('errorPais').style.display = 'block';
		isValid = false;
	}

	// Validar fechaIni
	const fechaIni = document.getElementById('fechaIni');
	if (!fechaIni.value.trim()) {
		fechaIni.classList.add('input-error');
		document.getElementById('errorFechaIni').style.display = 'block';
		isValid = false;
	}

	// Validar fechaFin
	const fechaFin = document.getElementById('fechaFin');
	if (!fechaFin.value.trim()) {
		fechaFin.classList.add('input-error');
		document.getElementById('errorFechaFin').style.display = 'block';
		isValid = false;
	}
	
	if (fechaFin.value <= fechaIni.value) {
			confirmarError.innerText = "La fecha de inicio no puede ser posterior";
			isValid = false;
		} else {
			confirmarError.innerText = "";
		}

	if (!isValid) {
		e.preventDefault();
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
	});
});