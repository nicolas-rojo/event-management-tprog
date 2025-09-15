// Base de datos simulada de usuarios (debe ser la misma que en consultaUsuario.html)
const usuariosDB = {
    'sofirod': {
        tipo: 'asistente',
        nombre: 'Sofia Rodriguez',
        nickname: 'sofirod',
        email: 'srodriguez@outlook.com',
        nacimiento: '03/02/1995',
        edad: 29,
        registros: [
            { evento: 'Maratón de Montevideo 2024', estado: 'Confirmado' },
            { evento: 'Maratón de Montevideo 2025', estado: 'Pendiente' }
        ]
    },
    'AnaG': {
        tipo: 'asistente',
        nombre: 'Ana Gomez',
        nickname: 'AnaG',
        email: 'ana.gomez@hotmail.com',
        nacimiento: '10/06/2000',
        edad: 24,
        registros: [
            { evento: 'Maratón de Montevideo 2024', estado: 'Confirmado' },
            { evento: 'Maratón de Montevideo 2025', estado: 'Pendiente' }
        ]
    },
    'JaviL': {
        tipo: 'asistente',
        nombre: 'Javier Lopez',
        nickname: 'JaviL',
        email: 'javier.lopez@outlook.com',
        nacimiento: '15/03/1998',
        edad: 26,
        registros: [
            { evento: 'Maratón de Montevideo 2024', estado: 'Confirmado' }
        ]
    },
    'OrgMaraton': {
        tipo: 'organizador',
        nombre: 'Organización Maratón',
        nickname: 'OrgMaraton',
        email: 'info@maratonmontevideo.com',
        sitioWeb: 'maratonmontevideo.com',
        descripcion: 'Organizadores de la Maratón de Montevideo',
        eventos: [
            { evento: 'Maratón de Montevideo 2024', estado: 'Aceptada' },
            { evento: 'Maratón de Montevideo 2025', estado: 'Ingresada' },
            { evento: 'Maratón de Montevideo 2023', estado: 'Rechazada' }
        ]
    },
    'MiOrganizacion': {
        tipo: 'organizador',
        nombre: 'Mi Organización',
        nickname: 'MiOrganizacion',
        email: 'contacto@miorganizacion.com',
        sitioWeb: 'miorganizacion.com',
        descripcion: 'Organizadores de eventos deportivos',
        eventos: [
            { evento: 'Eventos Deportivos 2024', estado: 'Aceptada' },
            { evento: 'Eventos Deportivos 2025', estado: 'Ingresada' }
        ]
    }
};

// Usuario actual simulado
const usuarioActual = 'MiOrganizacion'; // Cambiar según quién esté logueado

document.addEventListener('DOMContentLoaded', function () {
    // Obtener parámetros de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const usuario = urlParams.get('usuario');

    if (!usuario || !usuariosDB[usuario]) {
        // Si no hay usuario o no existe, redirigir a la lista
        alert('Usuario no encontrado');
        window.location.href = 'consultaUsuario.html';
        return;
    }

    const datosUsuario = usuariosDB[usuario];
    const esPropio = usuario === usuarioActual;
    const tipoUsuario = datosUsuario.tipo;

    // Configurar las clases del body
    document.body.className = ''; // Limpiar clases
    document.body.classList.add(tipoUsuario);
    if (esPropio) {
        document.body.classList.add('propio');
        document.body.classList.add('es-usuario-actual');
    }

    // Actualizar información principal
    actualizarInformacionPrincipal(datosUsuario, esPropio);

    // Actualizar secciones según el tipo de usuario
    if (tipoUsuario === 'asistente') {
        actualizarRegistrosAsistente(datosUsuario.registros);
    } else if (tipoUsuario === 'organizador') {
        actualizarEventosOrganizador(datosUsuario.eventos);
    }

    // Configurar botones si es el usuario actual
    if (esPropio) {
        configurarBotonesAccion();
    }

    // Cambiar título de la página
    document.title = datosUsuario.nombre + ' - Detalle de Usuario';
});

function actualizarInformacionPrincipal(datos, esPropio) {
    // Actualizar nombre y nickname
    document.querySelector('.nombre-usuario').textContent = datos.nombre;
    document.querySelector('.nickname-usuario').textContent = '@' + datos.nickname;

    // Actualizar detalles básicos
    const detalles = document.querySelectorAll('.detalle-item');

    // Email (siempre presente)
    detalles[0].querySelector('span:nth-child(2)').textContent = datos.email;

    // Tipo de usuario
    detalles[1].querySelector('span:nth-child(2)').textContent =
        'Tipo: ' + (datos.tipo === 'asistente' ? 'Asistente' : 'Organizador');

    if (datos.tipo === 'asistente') {
        // Fecha de nacimiento y edad para asistentes
        detalles[2].querySelector('span:nth-child(2)').textContent =
            'Fecha de nacimiento: ' + datos.nacimiento;
        detalles[3].querySelector('span:nth-child(2)').textContent =
            'Edad: ' + datos.edad + ' años';
    } else {
        // Sitio web y descripción para organizadores
        detalles[2].querySelector('span:nth-child(2)').textContent =
            'Sitio web: ' + datos.sitioWeb;
        detalles[3].querySelector('span:nth-child(2)').textContent =
            'Descripción: ' + datos.descripcion;
    }
}

function actualizarRegistrosAsistente(registros) {
    const listaItems = document.querySelector('.solo-asistente .lista-items');
    listaItems.innerHTML = '';

    if (!registros || registros.length === 0) {
        const item = document.createElement('div');
        item.className = 'item';
        item.innerHTML = '<span>No hay registros a eventos</span>';
        listaItems.appendChild(item);
        return;
    }

    registros.forEach(registro => {
        const item = document.createElement('div');
        item.className = 'item';

        const icono = registro.estado === 'Confirmado' ? '✅' :
            registro.estado === 'Pendiente' ? '⏳' : '❌';

        item.innerHTML = `<span>${icono} ${registro.evento} - Estado: ${registro.estado}</span>`;
        listaItems.appendChild(item);
    });
}

function actualizarEventosOrganizador(eventos) {
    const listaItems = document.querySelector('.solo-organizador .lista-items');
    listaItems.innerHTML = '';

    if (!eventos || eventos.length === 0) {
        const item = document.createElement('div');
        item.className = 'item';
        item.innerHTML = '<span>No hay eventos organizados</span>';
        listaItems.appendChild(item);
        return;
    }

    eventos.forEach(evento => {
        const item = document.createElement('div');
        item.className = 'item';

        const icono = evento.estado === 'Aceptada' ? '✅' :
            evento.estado === 'Ingresada' ? '⏳' : '❌';

        item.innerHTML = `<span>${icono} ${evento.evento} - Estado: ${evento.estado}</span>`;
        listaItems.appendChild(item);
    });
}

function configurarBotonesAccion() {
    const botonEditar = document.querySelector('.boton-primario');
    const botonCerrarSesion = document.querySelector('.boton-secundario');

    if (botonEditar) {
        botonEditar.addEventListener('click', function () {
            alert('Funcionalidad de editar perfil - Por implementar');
        });
    }

    if (botonCerrarSesion) {
        botonCerrarSesion.addEventListener('click', function () {
            if (confirm('¿Estás seguro que deseas cerrar sesión?')) {
                // Aquí irías a la página de login
                window.location.href = '../login/login.html';
            }
        });
    }
}