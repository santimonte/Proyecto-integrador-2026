document.addEventListener("DOMContentLoaded", () => {
    const formRegister = document.getElementById("formRegister");

    if (formRegister) {
        formRegister.addEventListener("submit", async (e) => {
            e.preventDefault(); // Evita que la página se recargue sola

            const email = document.getElementById("email").value;
            const contrasena = document.getElementById("contrasena").value;

            try {
                // Petición fetch al Servlet de Java
                const response = await fetch('http://localhost:8080/ProyectoAvicola/UsuarioServlet', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        accion: 'register',
                        email: email,
                        contrasena: contrasena
                    })
                });

                const data = await response.json();

                if (data.exito) {
                    alert("¡Registro exitoso! Ya puedes iniciar sesión.");
                    window.location.href = "login.html"; // Redirige al login
                } else {
                    alert(data.error || "No se pudo completar el registro.");
                }
            } catch (error) {
                console.error("Error al conectar con el servidor:", error);
                alert("Error de conexión con el backend de Java.");
            }
        });
    }
});