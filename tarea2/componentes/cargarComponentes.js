document.addEventListener("DOMContentLoaded", () => {
  // Cargar Topbar
  fetch("../componentes/topbar.html")
    .then((res) => res.text())
    .then((data) => {
      document.getElementById("topbar").innerHTML = data;

      // ---- Lógica de login/logout ----
      const loggedMenu = document.querySelector(".logged");
      const notLoggedMenu = document.querySelector(".not-logged");
      const logoutBtn = loggedMenu.querySelector("a:first-child"); // el botón "Cerrar Sesión"

      let isLogged = localStorage.getItem("isLogged") === "true";

      function updateTopbar() {
        if (isLogged) {
          loggedMenu.style.display = "flex";
          notLoggedMenu.style.display = "none";
        } else {
          loggedMenu.style.display = "none";
          notLoggedMenu.style.display = "flex";
        }
      }

      if (logoutBtn) {
        logoutBtn.addEventListener("click", (e) => {
          e.preventDefault();
          isLogged = false;
          localStorage.setItem("isLogged", "false");
          updateTopbar();
        });
      }

      updateTopbar();
    });

  // Cargar Sidebar
  fetch("../componentes/sidebar.html")
    .then((res) => res.text())
    .then((data) => {
      document.getElementById("sidebar").innerHTML = data;
    });
});
