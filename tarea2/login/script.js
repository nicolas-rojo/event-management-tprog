document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");
  const params = new URLSearchParams(window.location.search);
  const redirect = params.get("redirect");
  
  form.addEventListener("submit", (e) => {
    e.preventDefault(); // Evita que el formulario se envíe normalmente
    
    // Aca habria que validar usuario y contraseña
    // Por ahora asumo login exitoso
    localStorage.setItem("isLogged", "true");
    
    // Redirigimos a la pagina desde la que se quiso iniciar sesión
    window.location.href = redirect || "../index/index.html";
  });
});
