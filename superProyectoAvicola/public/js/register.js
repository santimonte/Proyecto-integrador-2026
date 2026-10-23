document.addEventListener("DOMContentLoaded", () => {
    const formRegister = document.getElementById("formRegister");

    if (formRegister) {
        formRegister.addEventListener("submit", async (e) => {
            e.preventDefault(); // Evita que la página se recargue sola

            const email = document.getElementById("email").value;
            const contrasena = document.getElementById("contrasena").value;

            try {
                // Petición fetch al Servlet de Java
                const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/usuarios', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        email: email,
                        contrasena: contrasena
                    })
                });

                const data = await response.json();

                // Validamos si la respuesta HTTP fue exitosa (201 Created)
                if (response.ok) {
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