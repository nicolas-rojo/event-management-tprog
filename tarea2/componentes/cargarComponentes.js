document.addEventListener('DOMContentLoaded', () => {
  // Cargar Topbar
  fetch('../componentes/topbar.html')
    .then(res => res.text())
    .then(data => {
      document.getElementById('topbar').innerHTML = data;
    });

  // Cargar Sidebar
  fetch('../componentes/sidebar.html')
    .then(res => res.text())
    .then(data => {
      document.getElementById('sidebar').innerHTML = data;
    });
});