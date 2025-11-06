function abrirModal(tipo) {
    const modalId = tipo === 'seguidores' ? 'modalSeguidores' : 'modalSeguidos';
    document.getElementById(modalId).style.display = 'flex';
}

function cerrarModal(event, modalId) {
    const modal = document.getElementById(modalId);
    if (!event || event.target === modal || event.target.classList.contains('modal-cerrar')) {
        modal.style.display = 'none';
    }
}

document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        document.getElementById('modalSeguidores').style.display = 'none';
        document.getElementById('modalSeguidos').style.display = 'none';
    }
});