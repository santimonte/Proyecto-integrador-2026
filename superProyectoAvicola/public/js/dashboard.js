document.addEventListener("DOMContentLoaded", async () => {
    try {
        // Le preguntamos al servidor Java si la sesión sigue activa
        const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/usuarios?accion=sesion', {
            method: 'GET',
            credentials: 'include' // Vital para enviar la cookie de sesión a Tomcat
        });

        if (response.ok) {
            const usuario = await response.json();
            // Mostramos el email que viene directo de la sesión de Java
            document.getElementById("userEmail").textContent = usuario.email;
        } else {
            // Si Java dice que no hay sesión, lo mandamos al login
            window.location.href = "login.html";
        }
    } catch (error) {
        console.error("Error al verificar la sesión:", error);
        window.location.href = "login.html";
    }

    // Botón de Cerrar Sesión
    document.getElementById("btnLogout").addEventListener("click", () => {
        // Opcional: Podrías invalidar la sesión en Java, por ahora redirigimos al login
        window.location.href = "login.html";
    });
});