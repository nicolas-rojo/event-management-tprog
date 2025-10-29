function mostrarModalAsistencia(event, nombreEvento) {
    event.stopPropagation(); // Evita que se active el link del card
    document.getElementById('nombreEvento').textContent = nombreEvento;
    document.getElementById('modalAsistencia').classList.add('active');
}

function cerrarModal() {
    document.getElementById('modalAsistencia').classList.remove('active');
}

function confirmarAsistencia() {
    const nombreEvento = document.getElementById('nombreEvento').textContent;
    cerrarModal();
}

// Cerrar modal con tecla Escape
document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        cerrarModal();
    }
});