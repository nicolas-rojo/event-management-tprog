document.getElementById('formEvento').addEventListener('submit', function(e) {
	// Limpiar errores previos
	document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');
	document.querySelectorAll('.input-error').forEach(el => el.classList.remove('input-error'));

	let isValid = true;

	// Validar nombre
	const nombre = document.getElementById('nombreEv');
	if (!nombre.value.trim()) {
		nombre.classList.add('input-error');
		document.getElementById('errorNombreEv').style.display = 'block';
		isValid = false;
	}

	// Validar descripción
	const desc = document.getElementById('desc');
	if (!desc.value.trim()) {
		desc.classList.add('input-error');
		document.getElementById('errorDesc').style.display = 'block';
		isValid = false;
	}

	// Validar siglas
	const sigla = document.getElementById('sigla');
	if (!sigla.value.trim()) {
		sigla.classList.add('input-error');
		document.getElementById('errorSigla').style.display = 'block';
		isValid = false;
	}

	// Validar categorías
	const categorias = document.getElementById('categorias');
	const categoriasSeleccionadas = Array.from(categorias.selectedOptions).map(option => option.value);

	if (categoriasSeleccionadas.length === 0) {
		categorias.classList.add('input-error');
		document.getElementById('errorCategorias').style.display = 'block';
		isValid = false;
	} else {
		// Crear inputs hidden para cada categoría seleccionada
		const hiddenContainer = document.getElementById('categoriasHidden');
		hiddenContainer.innerHTML = ''; // Limpiar anteriores

		categoriasSeleccionadas.forEach(cat => {
			const input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'categorias[]';
			input.value = cat;
			hiddenContainer.appendChild(input);
		});
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

// Para el select múltiple
document.getElementById('categorias').addEventListener('change', function() {
	this.classList.remove('input-error');
	document.getElementById('errorCategorias').style.display = 'none';
});