let edicionSeleccionada = '';

function mostrarModalAsistencia(event, nombreEvento) {
    event.stopPropagation();
    event.preventDefault();
    edicionSeleccionada = nombreEvento;
    document.getElementById('nombreEvento').textContent = nombreEvento;
    document.getElementById('nombreEdicionInput').value = nombreEvento;
    document.getElementById('modalAsistencia').classList.add('active');
}

function cerrarModal() {
    document.getElementById('modalAsistencia').classList.remove('active');
    edicionSeleccionada = '';
}

document.addEventListener('click', function(event) {
    const modal = document.getElementById('modalAsistencia');
    if (event.target === modal) {
        cerrarModal();
    }
});

document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        cerrarModal();
    }
});
