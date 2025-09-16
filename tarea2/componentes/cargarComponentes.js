document.addEventListener("DOMContentLoaded", () => {
	// Cargar componentes
	loadComponent("../componentes/topbar.html", "topbar", initTopbar);
	loadComponent("../componentes/sidebar.html", "sidebar", initSidebar);
});

// Funcion para cargar componentes
function loadComponent(path, targetId, callback) {
	fetch(path)
		.then(res => res.text())
		.then(html => {
			document.getElementById(targetId).innerHTML = html;
			if (typeof callback === "function") callback();
		})
		.catch(err => console.error(`Error cargando ${path}:`, err));
}

function initTopbar() {
	const loggedMenu = document.querySelector(".logged");
	const notLoggedMenu = document.querySelector(".not-logged");
	const logoutBtn = loggedMenu?.querySelector("a:first-child");

	updateTopbar();

	if (logoutBtn) {
		logoutBtn.addEventListener("click", e => {
			e.preventDefault();
			localStorage.setItem("isLogged", "false");
			localStorage.removeItem("usrRole");
			updateTopbar();
			updateSidebar();
		});
	}
}

function updateTopbar() {
	const isLogged = localStorage.getItem("isLogged") === "true";
	const loggedMenu = document.querySelector(".logged");
	const notLoggedMenu = document.querySelector(".not-logged");

	if (loggedMenu && notLoggedMenu) {
		loggedMenu.style.display = isLogged ? "flex" : "none";
		notLoggedMenu.style.display = isLogged ? "none" : "flex";
	}
}

function initSidebar() {
	updateSidebar();
}

function updateSidebar() {
	const isLogged = localStorage.getItem("isLogged") === "true";
	const role = localStorage.getItem("usrRole");

	const miPerfil = document.getElementById("miPerfil");
	const opcOrg = document.querySelector(".opc-org");
	const opcAsist = document.querySelector(".opc-asist");

	// Mi Perfil visible si hay sesión iniciada
	if (miPerfil) {
		miPerfil.style.display = isLogged ? "flex" : "none";
		if (isLogged) {
			miPerfil.setAttribute("href", "../perfil/perfil.html");
		}
	}

	// Opciones por rol
	if (opcOrg) {
		opcOrg.style.display = isLogged && role === "organizador" ? "flex" : "none";
		opcOrg.style.flexDirection = "column";
	}

	if (opcAsist) {
		opcAsist.style.display = isLogged && role === "asistente" ? "flex" : "none";
		opcAsist.style.flexDirection = "column";
	}
}

