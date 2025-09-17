document.addEventListener("DOMContentLoaded", () => {
	const form = document.querySelector("form");
	const params = new URLSearchParams(window.location.search);
	const redirect = params.get("redirect");

	const usuarioInput = document.getElementById("usuario");
	const passInput = document.getElementById("clave");
	const confirmarError = document.getElementById('confirmarError');

	form.addEventListener("submit", (e) => {
		e.preventDefault(); // Evita que el formulario se envíe normalmente

		const nickmail = usuarioInput.value.trim();
		const clave = passInput.value.trim();
		const user = checkLogin(nickmail, clave);
		if (user) {
			localStorage.setItem("isLogged", "true");
			localStorage.setItem("usrRole", user.role);
			localStorage.setItem("usrAvatar", user.img);
			// Acá se podría guardar el nickname en el localStorage
			confirmarError.innerText = "";
			// Redirigimos a la pagina desde la que se quiso iniciar sesión
			window.location.href = redirect || "../index/index.html";
		} else {
			confirmarError.innerText = "Datos Incorrectos";

		}
	});
});

function checkLogin(nickmail, clave) {
	const usuarios = [
		{nickname: "atorres", mail: "atorres@gmail.com", pass: "123.torres", role: "asistente", img: "../src/images/IMG-US01.jpg"}, 
		{nickname: "miseventos", mail: "contacto@miseventos.com", pass: "22miseventos", role: "organizador", img: "../src/images/IMG-US04.jpeg"}]
	return (usuarios.find(u => (u.nickname === nickmail || u.mail === nickmail) && u.pass === clave) || null);
}
