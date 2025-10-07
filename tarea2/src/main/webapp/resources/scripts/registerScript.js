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
});

document.getElementById('form').addEventListener('submit', function(e) {
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

	// Validar nick
	const nick = document.getElementById('nickname');
	if (!nick.value.trim()) {
		nick.classList.add('input-error');
		document.getElementById('errorNickname').style.display = 'block';
		isValid = false;
	}

	// Validar mail
	const mail = document.getElementById('mail');
	if (!mail.value.trim()) {
		mail.classList.add('input-error');
		document.getElementById('errorMail').style.display = 'block';
		isValid = false;
	}

	// Validar pass
	const pass = document.getElementById('password');
	if (!pass.value.trim()) {
		pass.classList.add('input-error');
		document.getElementById('errorPassword').style.display = 'block';
		isValid = false;
	}

	// Validar descripcion
	const desc = document.getElementById('desc');
	if (!desc.value.trim()) {
		desc.classList.add('input-error');
		document.getElementById('errorDesc').style.display = 'block';
		isValid = false;
	}

	// Validar apellido
	const apellido = document.getElementById('apellido');
	if (!apellido.value.trim()) {
		apellido.classList.add('input-error');
		document.getElementById('errorApellido').style.display = 'block';
		isValid = false;
	}

	// Validar fecha
	const fecha = document.getElementById('fechaNac');
	if (!fecha.value.trim()) {
		fecha.classList.add('input-error');
		document.getElementById('errorFechaNac').style.display = 'block';
		isValid = false;
	}

	const con = document.getElementById('confirmacion');
	if (pass.value !== con.value) {
		confirmarError.innerText = "Las Contraseñas No Coinciden";
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