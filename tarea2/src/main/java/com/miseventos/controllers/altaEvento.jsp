<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Evento</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/altaEventoStyle.css">
</head>

<body>
    <a href="${pageContext.request.contextPath}/home">
        <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo">
    </a>
    <div class="login-container">
        <h2>Nuevo Evento</h2>
        <form action="${pageContext.request.contextPath}/NuevoEvento" method="post" id="formEvento">
            <input type="text" id="nombreEv" name="nombreEv" placeholder="Nombre" required>
            <div class="error-message" id="errorNombre">El nombre es requerido</div>

            <textarea id="desc" name="desc" placeholder="Descripción" required></textarea>
            <div class="error-message" id="errorDesc">La descripción es requerida</div>

            <input type="text" id="sigla" name="sigla" placeholder="Siglas" required>
            <div class="error-message" id="errorSigla">Las siglas son requeridas</div>

            <div class="form-group">
                <label for="categorias">Categorías (mantén Ctrl/Cmd para seleccionar múltiples):</label>
                <select id="categorias" name="categorias" multiple required size="6">
                    <option value="Tecnologia">Tecnología</option>
                    <option value="Innovacion">Innovación</option>
                    <option value="Literatura">Literatura</option>
                    <option value="Cultura">Cultura</option>
                    <option value="Musica">Música</option>
                    <option value="Deporte">Deporte</option>
                    <option value="Salud">Salud</option>
                    <option value="Entretenimiento">Entretenimiento</option>
                    <option value="Agro">Agro</option>
                    <option value="Negocios">Negocios</option>
                    <option value="Moda">Moda</option>
                    <option value="Investigacion">Investigación</option>
                </select>
                <div class="error-message" id="errorCategorias">Selecciona al menos una categoría</div>
            </div>

            <input type="file" id="imagen" name="imagen" accept="image/*">

            <!-- Inputs ocultos para las categorías seleccionadas -->
            <div id="categoriasHidden"></div>

            <div class="button-group">
                <button type="submit">Aceptar</button>
                <button type="button" id="cancelarBtn" onclick="history.back()">Cancelar</button>
            </div>
        </form>
    </div>

    <script>
        document.getElementById('formEvento').addEventListener('submit', function (e) {
            // Limpiar errores previos
            document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');
            document.querySelectorAll('.input-error').forEach(el => el.classList.remove('input-error'));

            let isValid = true;

            // Validar nombre
            const nombre = document.getElementById('nombreEv');
            if (!nombre.value.trim()) {
                nombre.classList.add('input-error');
                document.getElementById('errorNombre').style.display = 'block';
                isValid = false;
            }

            // Validar descripción
            const desc = document.getElementById('desc');
            if (!desc.value.trim()) {
                desc.classList.add('input-error');
                document.getElementById('errorDesc').style.display = 'block';
                isValid = false;
            }

            // Validar siglas
            const sigla = document.getElementById('sigla');
            if (!sigla.value.trim()) {
                sigla.classList.add('input-error');
                document.getElementById('errorSigla').style.display = 'block';
                isValid = false;
            }

            // Validar categorías
            const categorias = document.getElementById('categorias');
            const categoriasSeleccionadas = Array.from(categorias.selectedOptions).map(option => option.value);
            
            if (categoriasSeleccionadas.length === 0) {
                categorias.classList.add('input-error');
                document.getElementById('errorCategorias').style.display = 'block';
                isValid = false;
            } else {
                // Crear inputs hidden para cada categoría seleccionada
                const hiddenContainer = document.getElementById('categoriasHidden');
                hiddenContainer.innerHTML = ''; // Limpiar anteriores
                
                categoriasSeleccionadas.forEach(cat => {
                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'categorias[]';
                    input.value = cat;
                    hiddenContainer.appendChild(input);
                });
            }

            if (!isValid) {
                e.preventDefault();
            }
        });

        document.querySelectorAll('input, textarea, select').forEach(element => {
            element.addEventListener('input', function () {
                this.classList.remove('input-error');
                const errorId = 'error' + this.id.charAt(0).toUpperCase() + this.id.slice(1);
                const errorElement = document.getElementById(errorId);
                if (errorElement) {
                    errorElement.style.display = 'none';
                }
            });
        });

        // Para el select múltiple
        document.getElementById('categorias').addEventListener('change', function() {
            this.classList.remove('input-error');
            document.getElementById('errorCategorias').style.display = 'none';
        });
    </script>
</body>

</html>