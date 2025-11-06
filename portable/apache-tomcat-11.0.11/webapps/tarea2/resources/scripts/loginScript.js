document.getElementById('formLogin').addEventListener('submit', function(e) {
	// Limpiar errores previos
	document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');
	document.querySelectorAll('.input-error').forEach(el => el.classList.remove('input-error'));

	let isValid = true;

	// Validar nombre
	const nickmail = document.getElementById('nickmail');
	if (!nickmail.value.trim()) {
		nickmail.classList.add('input-error');
		document.getElementById('errorNickmail').style.display = 'block';
		isValid = false;
	}

	// Validar descripción
	const pass = document.getElementById('clave');
	if (!pass.value.trim()) {
		pass.classList.add('input-error');
		document.getElementById('errorClave').style.display = 'block';
		isValid = false;
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