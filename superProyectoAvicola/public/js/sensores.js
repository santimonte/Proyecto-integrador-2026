

document.addEventListener("DOMContentLoaded", async () => {
    try {
        const sessionResponse = await fetch('http://127.0.0.1:8080/ProyectoAvicola/usuarios?accion=sesion', {
            method: 'GET',
            credentials: 'include'
        });

        if (sessionResponse.ok) {
            const usuario = await sessionResponse.json();
            
            const emailSpan = document.getElementById("userEmail");
            if (emailSpan) {
                emailSpan.textContent = usuario.email;
            }

            // Aplicar filtro por rol
            aplicarFiltroRoles(usuario);

        } else {
            window.location.href = "login.html";
            return;
        }
    } catch (error) {
        console.error("Error al verificar sesión:", error);
        window.location.href = "login.html";
        return;
    }

    cargarSensores();

    const formSensor = document.getElementById("formSensor");
    if (formSensor) {
        formSensor.addEventListener("submit", async (e) => {
            e.preventDefault();

            const tipoSensor = document.getElementById("tipoSensor").value;
            const ubicacion = document.getElementById("ubicacion").value;

            try {
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
                    cargarSensores();
                } else {
                    alert(data.error || "No se pudo registrar el sensor.");
                }
            } catch (error) {
                console.error("Error al guardar sensor:", error);
                alert("Error de conexión con el servidor de Java.");
            }
        });
    }

    const btnLogout = document.getElementById("btnLogout");
    if (btnLogout) {
        btnLogout.addEventListener("click", () => {
            window.location.href = "login.html";
        });
    }
});

async function cargarSensores() {
    try {
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
        }
    } catch (error) {
        console.error("Error al cargar sensores:", error);
    }
}


function aplicarFiltroRoles(usuario) {
    if (usuario.idRol === 1) { // Cliente
        console.log("Cliente detectado: Ocultando panel de alta de sensores.");
        const formCard = document.querySelector(".form-card");
        if (formCard) {
            formCard.style.display = "none";
        }
    }
}