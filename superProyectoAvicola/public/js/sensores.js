document.addEventListener("DOMContentLoaded", async () => {
    // 1. Verificar sesión activa con Java
    try {
        const sessionResponse = await fetch('http://127.0.0.1:8080/ProyectoAvicola/usuarios?accion=sesion', {
            method: 'GET',
            credentials: 'include'
        });

        if (sessionResponse.ok) {
            const usuario = await sessionResponse.json();
            document.getElementById("userEmail").textContent = usuario.email;
        } else {
            window.location.href = "login.html";
            return;
        }
    } catch (error) {
        console.error("Error al verificar sesión:", error);
        window.location.href = "login.html";
        return;
    }

    // 2. Cargar la lista de sensores al iniciar la página
    cargarSensores();

    // 3. Manejar el envío del formulario para registrar un nuevo sensor
    const formSensor = document.getElementById("formSensor");
    if (formSensor) {
        formSensor.addEventListener("submit", async (e) => {
            e.preventDefault();

            const tipoSensor = document.getElementById("tipoSensor").value;
            const ubicacion = document.getElementById("ubicacion").value;

            try {
                // CORREGIDO: Apunta a 127.0.0.1
                const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/sensores', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    credentials: 'include',
                    body: JSON.stringify({
                        tipoSensor: tipoSensor,
                        ubicacion: ubicacion
                    })
                });

                const data = await response.json();
                if (response.ok && data.exito) {
                    alert("¡Sensor registrado con éxito!");
                    formSensor.reset();
                    cargarSensores(); // Recargar la tabla automáticamente
                } else {
                    alert(data.error || "No se pudo registrar el sensor.");
                }
            } catch (error) {
                console.error("Error al guardar sensor:", error);
                alert("Error de conexión con el servidor de Java.");
            }
        });
    }

    // 4. Botón de Cerrar Sesión
    document.getElementById("btnLogout").addEventListener("click", () => {
        window.location.href = "login.html";
    });
});

// Función para obtener y pintar los sensores en la tabla
async function cargarSensores() {
    try {
        // CORREGIDO: Apunta a 127.0.0.1
        const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/sensores', {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok) {
            const sensores = await response.json();
            const tbody = document.getElementById("tablaSensoresBody");
            tbody.innerHTML = "";

            if (sensores.length === 0) {
                tbody.innerHTML = `<tr><td colspan="3" style="text-align: center; color: var(--text-muted);">No hay sensores registrados todavía.</td></tr>`;
                return;
            }

            sensores.forEach(s => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${s.idSensor}</td>
                    <td>${s.tipoSensor}</td>
                    <td>${s.ubicacion}</td>
                `;
                tbody.appendChild(tr);
            });
        } else {
            console.error("Error al obtener los sensores de la base de datos");
        }
    } catch (error) {
        console.error("Error de red al cargar sensores:", error);
    }
}