document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");

  form.addEventListener("submit", (e) => {
    e.preventDefault(); // Evita que el formulario se envíe normalmente

    // Aca habria que validar usuario y contraseña
    // Por ahora asumo login exitoso
    localStorage.setItem("isLogged", "true");

    // Redirigir al index
    window.location.href = "../index/index.html";
  });
});
