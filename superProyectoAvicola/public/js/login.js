document.addEventListener("DOMContentLoaded", () => {
    const formLogin = document.getElementById("formLogin");

    if (formLogin) {
        formLogin.addEventListener("submit", async (e) => {
            e.preventDefault();

            const email = document.getElementById("emailLogin").value;
            const contrasena = document.getElementById("passwordLogin").value;

            try {
                // Petición fetch al Servlet indicando la acción 'login'
                const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/usuarios', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    credentials: 'include',
                    body: JSON.stringify({
                        accion: 'login',
                        email: email,
                        contrasena: contrasena
                    })
                });

                const data = await response.json();

                if (data.exito) {
                    // CERO localStorage ni ningún 'set'. 
                    // La sesión ya quedó creada en Java (HttpSession) y Tomcat maneja la cookie automáticamente.
                    alert("¡Inicio de sesión exitoso! Bienvenido al sistema.")
                    window.location.href = "./dashboard.html"; 
                } else {
                    alert(data.error || data.mensaje || "Email o contraseña incorrectos.");
                }
            } catch (error) {
                console.error("Error al conectar con el servidor:", error);
                alert("Error de conexión con el backend de Java.");
            }
        });
    }
});