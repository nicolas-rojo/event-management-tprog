// Verificar si el usuario está logueado y si es el propio organizador
function verificarUsuarioLogueado() {
    const isLogged = localStorage.getItem('isLogged') === 'true';
    const usuarioActual = localStorage.getItem('usrNickname');  // Simular usuario actual

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
// Ejecutar al cargar la página
document.addEventListener('DOMContentLoaded', verificarUsuarioLogueado);