// Verificar si el usuario está logueado y si es el propio asistente
function verificarUsuarioLogueado() {
    const isLogged = localStorage.getItem('isLogged') === 'true';
    const usuarioActual = localStorage.getItem('usuarioActual') || 'atorres'; // Simular usuario actual

    // Si está logueado y es el propio asistente, mostrar elementos privados
    if (isLogged && usuarioActual === 'atorres') {
        const elementosPropios = document.querySelectorAll('.solo-propio');
        elementosPropios.forEach(elemento => {
            elemento.style.display = 'block';
        });

        // Cambiar el título de la sección a más personal
        document.querySelector('.seccion-titulo').textContent = 'Mis Registros a Eventos';

        // Agregar clase para estilos de usuario actual
        document.body.classList.add('es-usuario-actual');
    }
}

// Ejecutar al cargar la página
document.addEventListener('DOMContentLoaded', verificarUsuarioLogueado);