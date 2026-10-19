document.addEventListener("DOMContentLoaded", () => {
    const formLogin = document.getElementById("formLogin");

    if (formLogin) {
        formLogin.addEventListener("submit", async (e) => {
            e.preventDefault();

            const email = document.getElementById("emailLogin").value;
            const contrasena = document.getElementById("passwordLogin").value;

            try {
                // Petición fetch al Servlet indicando la acción 'login'
                const response = await fetch('http://localhost:8080/ProyectoAvicola/UsuarioServlet', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        accion: 'login',
                        email: email,
                        contrasena: contrasena
                    })
                });

                const data = await response.json();

                if (data.exito) {
                    // Guardamos una bandera en el navegador indicando que hay sesión activa
                    localStorage.setItem("usuarioLogueado", JSON.stringify({ email }));
                    window.location.href = "dashboard.html"; // Te lleva al panel principal
                } else {
                    alert(data.error || "Email o contraseña incorrectos.");
                }
            } catch (error) {
                console.error("Error al conectar con el servidor:", error);
                alert("Error de conexión con el backend de Java.");
            }
        });
    }
});