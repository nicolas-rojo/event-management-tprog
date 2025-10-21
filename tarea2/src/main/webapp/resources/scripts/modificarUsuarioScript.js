document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('formModificar');
    
    if (form) {
        form.addEventListener('submit', function(e) {
            let valid = true;
            
            // Validar nombre
            const nombre = document.getElementById('nombre');
            const errorNombre = document.getElementById('errorNombre');
            if (nombre && nombre.value.trim() === '') {
                errorNombre.style.display = 'block';
                valid = false;
            } else if (errorNombre) {
                errorNombre.style.display = 'none';
            }
            
            // Validar apellido (solo para Asistente)
            const apellido = document.getElementById('apellido');
            const errorApellido = document.getElementById('errorApellido');
            if (apellido && apellido.value.trim() === '') {
                errorApellido.style.display = 'block';
                valid = false;
            } else if (errorApellido) {
                errorApellido.style.display = 'none';
            }
            
            // Validar contraseñas
            const passActual = document.getElementById('passActual');
            const passNueva = document.getElementById('passNueva');
            const passConfirmar = document.getElementById('passConfirmar');
            const errorPassActual = document.getElementById('errorPassActual');
            const errorPassNueva = document.getElementById('errorPassNueva');
            const errorPassConfirmar = document.getElementById('errorPassConfirmar');
            
            // Verificar si se está intentando cambiar la contraseña
            const intentaCambiarPassword = (passNueva && passNueva.value.trim() !== '') || 
                                           (passConfirmar && passConfirmar.value.trim() !== '');
            
            if (intentaCambiarPassword) {
                // Si se intenta cambiar contraseña, validar todos los campos
                
                // Validar contraseña actual
                if (!passActual.value.trim()) {
                    errorPassActual.style.display = 'block';
                    errorPassActual.textContent = 'Debe ingresar su contraseña actual';
                    valid = false;
                } else {
                    errorPassActual.style.display = 'none';
                }
                
                // Validar contraseña nueva
                if (!passNueva.value.trim()) {
                    errorPassNueva.style.display = 'block';
                    errorPassNueva.textContent = 'Debe ingresar la nueva contraseña';
                    valid = false;
                } else {
                    errorPassNueva.style.display = 'none';
                }
                
                // Validar confirmación
                if (!passConfirmar.value.trim()) {
                    errorPassConfirmar.style.display = 'block';
                    errorPassConfirmar.textContent = 'Debe confirmar la nueva contraseña';
                    valid = false;
                } else if (passNueva.value !== passConfirmar.value) {
                    errorPassConfirmar.style.display = 'block';
                    errorPassConfirmar.textContent = 'Las contraseñas no coinciden';
                    valid = false;
                } else {
                    errorPassConfirmar.style.display = 'none';
                }
            } else {
                // Si no se intenta cambiar contraseña, ocultar errores
                if (errorPassActual) errorPassActual.style.display = 'none';
                if (errorPassNueva) errorPassNueva.style.display = 'none';
                if (errorPassConfirmar) errorPassConfirmar.style.display = 'none';
            }
            
            if (!valid) {
                e.preventDefault();
            }
        });
    }
    
    // Limpiar mensajes de error cuando el usuario escribe
    const inputs = form.querySelectorAll('input, textarea');
    inputs.forEach(input => {
        input.addEventListener('input', function() {
            const errorDiv = document.getElementById('error' + this.id.charAt(0).toUpperCase() + this.id.slice(1));
            if (errorDiv) {
                errorDiv.style.display = 'none';
            }
        });
    });
});