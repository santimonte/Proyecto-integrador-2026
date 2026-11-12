/* ==========================================
   SANIDAD Y LOTES - AVÍCOLA SAN MARTÍN
   ========================================== */

document.addEventListener("DOMContentLoaded", async () => {
    try {
        const sessionResponse = await fetch('http://127.0.0.1:8080/ProyectoAvicola/usuarios?accion=sesion', {
            method: 'GET',
            credentials: 'include'
        });

        if (sessionResponse.ok) {
            const usuario = await sessionResponse.json();
            const emailSpan = document.getElementById("userEmail");
            if (emailSpan) emailSpan.textContent = usuario.email;

            // Ocultar formulario si es cliente (idRol === 1)
            if (Number(usuario.idRol) === 1) {
                const formCard = document.getElementById("cardFormularioSanidad");
                if (formCard) formCard.style.setProperty("display", "none", "important");
            }
        } else {
            window.location.href = "login.html";
            return;
        }
    } catch (error) {
        console.error("Error al verificar sesión:", error);
        window.location.href = "login.html";
        return;
    }

    cargarControlesSanidad();

    const formSanidad = document.getElementById("formSanidad");
    if (formSanidad) {
        formSanidad.addEventListener("submit", async (e) => {
            e.preventDefault();
            const idLote = document.getElementById("idLote").value;
            const cantidadBajas = document.getElementById("cantidadBajas").value;
            const observaciones = document.getElementById("observaciones").value;

            try {


                // Asumiendo un servlet /sanidad o /controlesSanidad configurado en Java
                const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/controles-sanidad', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    credentials: 'include',
                    body: JSON.stringify({
                        idLote: parseInt(idLote, 10),
                        cantidadBajas: parseInt(cantidadBajas, 10),
                        observacionesVeterinarias: observaciones
                    })
                });

                const data = await response.json();
                if ((response.ok || response.status === 201) && data.exito) {
                    alert("¡Control sanitario registrado con éxito!");
                    formSanidad.reset();
                    cargarControlesSanidad();
                } else {
                    alert(data.error || "No se pudo registrar el control.");
                }
            } catch (error) {
                console.error("Error al guardar control:", error);
                alert("Error de conexión con el servidor.");
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

async function cargarControlesSanidad() {
    try {
        const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/controles-sanidad', {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok) {
            const controles = await response.json();
            const tbody = document.getElementById("tablaSanidadBody");
            tbody.innerHTML = "";

            if (controles.length === 0) {
                tbody.innerHTML = `<tr><td colspan="4" class="fila-vacia">No hay controles de sanidad registrados.</td></tr>`;
                document.getElementById("totalControlesCard").textContent = "0";
                return;
            }

            document.getElementById("totalControlesCard").textContent = String(controles.length);

            controles.forEach(cs => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${cs.idControl || cs.id_control || '---'}</td>
                    <td>Lote #${cs.idLote || cs.id_lote || '---'}</td>
                    <td>${cs.cantidadBajas ?? cs.cantidad_bajas ?? 0} aves</td>
                    <td>${cs.observacionesVeterinarias || cs.observaciones_veterinarias || 'Sin observaciones'}</td>
                `;
                tbody.appendChild(tr);
            });
        }
    } catch (error) {
        console.error("Error al cargar controles de sanidad:", error);
    }
}