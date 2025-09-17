// Verificar si el usuario está logueado y si es el propio organizador
function verificarUsuarioLogueado() {
    const isLogged = localStorage.getItem('isLogged') === 'true';
    const usuarioActual = localStorage.getItem('usuarioActual') || 'miseventos'; // Simular usuario actual

    // Si está logueado y es el propio organizador, mostrar elementos privados
    if (isLogged && usuarioActual === 'miseventos') {
        const elementosPropios = document.querySelectorAll('.solo-propio');
        elementosPropios.forEach(elemento => {
            elemento.style.display = 'block';
        });

        // Cambiar el título de la sección
        document.querySelector('.seccion-titulo').textContent = 'Mis Ediciones de Eventos';

        // Agregar clase para estilos de usuario actual
        document.body.classList.add('es-usuario-actual');
    }
}

function confirmarCerrarSesion() {
    if (confirm('¿Estás seguro que deseas cerrar sesión?')) {
        localStorage.removeItem('isLogged');
        localStorage.removeItem('usuarioActual');
        window.location.href = '../login/login.html';
    }
}

// Ejecutar al cargar la página
document.addEventListener('DOMContentLoaded', verificarUsuarioLogueado);