// Base de datos de usuarios (la misma que en el detalle)
const usuariosDB = {
    'sofirod': {
        tipo: 'asistente',
        nombre: 'Sofia Rodriguez',
        nickname: 'sofirod',
        email: 'srodriguez@outlook.com',
        nacimiento: '03/02/1995',
        edad: 29,
        evento: 'Maratón Montevideo'
    },
    'AnaG': {
        tipo: 'asistente',
        nombre: 'Ana Gomez',
        nickname: 'AnaG',
        email: 'ana.gomez@hotmail.com',
        nacimiento: '10/06/2000',
        edad: 24,
        evento: 'Maratón Montevideo'
    },
    'JaviL': {
        tipo: 'asistente',
        nombre: 'Javier Lopez',
        nickname: 'JaviL',
        email: 'javier.lopez@outlook.com',
        nacimiento: '15/03/1998',
        edad: 26,
        evento: 'Maratón Montevideo'
    },
    'OrgMaraton': {
        tipo: 'organizador',
        nombre: 'Organización Maratón',
        nickname: 'OrgMaraton',
        email: 'info@maratonmontevideo.com',
        sitioWeb: 'maratonmontevideo.com',
        evento: 'Maratón Montevideo'
    },
    'MiOrganizacion': {
        tipo: 'organizador',
        nombre: 'Mi Organización',
        nickname: 'MiOrganizacion',
        email: 'contacto@miorganizacion.com',
        sitioWeb: 'miorganizacion.com',
        evento: 'Eventos Deportivos'
    }
};

// Usuario actual simulado
const usuarioActual = 'MiOrganizacion'; // Cambiar según quién esté logueado

document.addEventListener('DOMContentLoaded', function () {
    cargarUsuarios();
    configurarFiltros();
});

function cargarUsuarios(filtro = 'todos') {
    const contenedor = document.getElementById('contenedor-usuarios');
    contenedor.innerHTML = '';

    Object.keys(usuariosDB).forEach(nickname => {
        const usuario = usuariosDB[nickname];

        // Aplicar filtro
        if (filtro !== 'todos' && usuario.tipo !== filtro) {
            return;
        }

        const tarjeta = crearTarjetaUsuario(nickname, usuario);
        contenedor.appendChild(tarjeta);
    });
}

function crearTarjetaUsuario(nickname, usuario) {
    const esUsuarioActual = nickname === usuarioActual;
    const tarjeta = document.createElement('div');

    tarjeta.className = `tarjeta-usuario ${usuario.tipo}${esUsuarioActual ? ' usuario-actual' : ''}`;

    const iconoGenero = usuario.tipo === 'asistente' ?
        (usuario.nombre.includes('Ana') || usuario.nombre.includes('Sofia') ? '🏃‍♀️' : '🏃‍♂️') :
        '📋';

    tarjeta.innerHTML = `
                <a href="consultaUsuarioDetalle.html?usuario=${nickname}">
                    <div class="sin-imagen">Sin imagen</div>
                    <span class="tipo-usuario tipo-${usuario.tipo}">
                        ${usuario.tipo === 'asistente' ? 'Asistente' : 'Organizador'}
                    </span>
                    ${esUsuarioActual ? '<span class="etiqueta-usuario-actual">Tú</span>' : ''}
                    <div class="contenido-usuario">
                        <h3 class="nombre-usuario">${usuario.nombre}</h3>
                        <p class="nickname-usuario">@${nickname}</p>
                        <div class="detalles-usuario">
                            <div class="detalle-item">
                                <span class="icono">📧</span>
                                <span>${usuario.email}</span>
                            </div>
                            ${usuario.tipo === 'asistente' ? `
                            <div class="detalle-item">
                                <span class="icono">🎂</span>
                                <span>${usuario.nacimiento} (${usuario.edad} años)</span>
                            </div>
                            <div class="detalle-item">
                                <span class="icono">${iconoGenero}</span>
                                <span>Participa en: ${usuario.evento}</span>
                            </div>
                            ` : `
                            <div class="detalle-item">
                                <span class="icono">📋</span>
                                <span>Organiza: ${usuario.evento}</span>
                            </div>
                            <div class="detalle-item">
                                <span class="icono">🌐</span>
                                <span>${usuario.sitioWeb}</span>
                            </div>
                            `}
                        </div>
                    </div>
                </a>
            `;

    return tarjeta;
}

function configurarFiltros() {
    const botonesFiltro = document.querySelectorAll('.filtro-btn');

    botonesFiltro.forEach(boton => {
        boton.addEventListener('click', function () {
            // Remover clase active de todos los botones
            botonesFiltro.forEach(b => b.classList.remove('active'));

            // Agregar clase active al botón clickeado
            this.classList.add('active');

            // Cargar usuarios con el filtro
            const filtro = this.getAttribute('data-filtro');
            cargarUsuarios(filtro);
        });
    });
}