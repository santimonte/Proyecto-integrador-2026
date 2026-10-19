// index.js - Lógica exclusiva para la vista de inicio
document.addEventListener("DOMContentLoaded", () => {
    console.log("✅ Página de Inicio (Avícola San Martín) cargada correctamente.");

    // Opcional: Si deseas que la interfaz cambie si el usuario ya inició sesión
    const sesion = localStorage.getItem("usuarioLogueado");
    if (sesion) {
        console.log("Usuario con sesión activa detectada.");
        // Aquí podrías cambiar dinámicamente los botones del navbar si lo deseas en un futuro
    }
});