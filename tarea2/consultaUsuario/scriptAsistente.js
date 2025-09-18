function verificarUsuarioLogueado() {
    const isLogged = localStorage.getItem('isLogged') === 'true';
    const usuarioActual = localStorage.getItem('nickname'); // valor real guardado

    if (isLogged && usuarioActual === 'atorres') {
        const elementosPropios = document.querySelectorAll('.solo-propio');
        elementosPropios.forEach(elemento => {
            elemento.style.display = 'block';
        });

        document.querySelector('.seccion-titulo').textContent = 'Mis Registros a Eventos';
        document.body.classList.add('es-usuario-actual');
    }
}


// Ejecutar al cargar la página
document.addEventListener('DOMContentLoaded', verificarUsuarioLogueado);