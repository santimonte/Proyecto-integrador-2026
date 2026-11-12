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

    cargarProduccion();

    // --- AGREGAMOS EL EVENTO SUBMIT AL FORMULARIO ---
    const formProduccion = document.getElementById("formProduccion");
    if (formProduccion) {
        formProduccion.addEventListener("submit", async (e) => {
            e.preventDefault();

            const idLote = document.getElementById("idLote").value;
            const cantidadAves = document.getElementById("cantidadAves").value;
            const pesoTotal = document.getElementById("pesoTotal").value;
            const errorMsg = document.getElementById("mensajeError");
            if (errorMsg) errorMsg.textContent = "";

            try {
                const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/faenas', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    credentials: 'include',
                    body: JSON.stringify({
                        idLote: parseInt(idLote, 10),
                        cantidadAvesFaenadas: parseInt(cantidadAves, 10),
                        pesoTotalObtenido: parseFloat(pesoTotal)
                    })
                });

                const data = await response.json().catch(() => ({}));

                if (response.ok || response.status === 201) {
                    formProduccion.reset();
                    cargarProduccion();
                    alert("¡Faena registrada con éxito!");
                } else {
                    if (errorMsg) errorMsg.textContent = data.error || "No se pudo registrar la producción.";
                }
            } catch (error) {
                console.error("Error al guardar producción:", error);
                if (errorMsg) errorMsg.textContent = "Error de conexión con el servidor.";
            }
        });
    }

    // Botón Cerrar Sesión
    const btnLogout = document.getElementById("btnLogout");
    if (btnLogout) {
        btnLogout.addEventListener("click", () => {
            window.location.href = "login.html";
        });
    }
});

async function cargarProduccion() {
    try {
        const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/faenas', {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok) {
            const registros = await response.json();
            const tbody = document.getElementById("tablaProduccionBody");
            tbody.innerHTML = "";

            if (registros.length === 0) {
                tbody.innerHTML = `<tr><td colspan="3" class="fila-vacia">No hay registros de producción todavía.</td></tr>`;
                const totalReg = document.getElementById("totalRegistros");
                if (totalReg) totalReg.textContent = "0";
                return;
            }

            const totalReg = document.getElementById("totalRegistros");
            if (totalReg) totalReg.textContent = registros.length;

            registros.forEach(r => {
                const tr = document.createElement("tr");
                // Ajustamos para mostrar los datos reales de la faena en las columnas de la tabla
                tr.innerHTML = `
                    <td>${r.idFaena || r.id || '---'}</td>
                    <td>Lote #${r.idLote || r.id_lote || '---'}</td>
                    <td>${r.cantidadAvesFaenadas || r.cantidad_aves_faenadas || 0} aves (${r.pesoTotalObtenido || r.peso_total_obtenido || 0} kg)</td>
                `;
                tbody.appendChild(tr);
            });
        }
    } catch (error) {
        console.error("Error al cargar producción:", error);
    }
}

function aplicarFiltroRoles(usuario) {
    if (usuario.idRol === 1) { // Cliente
        console.log("Cliente detectado: Ocultando panel de registro de producción.");
        const formCard = document.querySelector(".form-card");
        if (formCard) {
            formCard.style.display = "none";
        }
    }
}