document.addEventListener("DOMContentLoaded", () => {
    // 1. Verificar si hay sesión activa (ej: desde localStorage o sessionStorage)
    const emailUsuario = localStorage.getItem("usuarioEmail");

    if (!emailUsuario) {
        // Si no está logueado, redirigir al login
        window.location.href = "login.html";
        return;
    }

    // Mostrar el email en la barra superior
    document.getElementById("userEmail").textContent = emailUsuario;

    // 2. Controlar Cierre de Sesión
    document.getElementById("btnLogout").addEventListener("click", () => {
        localStorage.removeItem("usuarioEmail");
        localStorage.removeItem("idRol");
        window.location.href = "login.html";
    });

    // 3. (Opcional) Cargar métricas iniciales del servidor Tomcat
    cargarResumenInicio();
});

function cargarResumenInicio() {
    // Aquí podemos hacer un fetch al backend para traer métricas de los sensores/galpones
    console.log("Cargando datos del panel principal...");
}