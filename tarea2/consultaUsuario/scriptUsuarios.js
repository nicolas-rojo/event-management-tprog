document.addEventListener('DOMContentLoaded', () => {
    const isLogged = localStorage.getItem('isLogged') === 'true';
    const usuarioActual = localStorage.getItem('nickname'); // valor guardado en localStorage

    // Seleccionamos todas las tarjetas de usuario
    const tarjetas = document.querySelectorAll('.tarjeta-usuario');

    tarjetas.forEach(tarjeta => {
        const nicknameTarjeta = tarjeta.querySelector('.nickname-usuario').textContent.replace('@', '').trim();

        // Por defecto, la etiqueta "Tú" está oculta
        const etiqueta = tarjeta.querySelector('.etiqueta-usuario-actual');
        if (etiqueta) {
            etiqueta.style.display = 'none';
        }

        // Si el usuario está logueado y coincide con la tarjeta
        if (isLogged && usuarioActual === nicknameTarjeta) {
            // Agregar borde dorado
            tarjeta.classList.add('usuario-actual');

            // Mostrar etiqueta "Tú"
            if (etiqueta) {
                etiqueta.style.display = 'block';
            }
        }
    });
});



function verDetalleAsistente() {
    window.location.href = 'consultaDetalleAsistente.html';
}

function verDetalleOrganizador() {
    window.location.href = 'consultaDetalleOrganizador.html';
}