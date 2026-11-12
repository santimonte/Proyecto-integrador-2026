/* ==========================================
   CÁMARAS FRIGORÍFICAS - AVÍCOLA SAN MARTÍN
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
        } else {
            window.location.href = "login.html";
            return;
        }
    } catch (error) {
        console.error("Error al verificar sesión:", error);
        window.location.href = "login.html";
        return;
    }

    cargarCamaras();

    const btnLogout = document.getElementById("btnLogout");
    if (btnLogout) {
        btnLogout.addEventListener("click", () => {
            window.location.href = "login.html";
        });
    }
});

async function cargarCamaras() {
    try {
        // Asegúrate de que tu backend tenga un servlet o mapeo para /camaras (usando el procedimiento listar_tabla)
        const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/camaras', {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok) {
            const camaras = await response.json();
            const tbody = document.getElementById("tablaCamarasBody");
            tbody.innerHTML = "";

            if (camaras.length === 0) {
                tbody.innerHTML = `<tr><td colspan="3" class="fila-vacia">No hay cámaras registradas.</td></tr>`;
                document.getElementById("totalCamarasCard").textContent = "0";
                return;
            }

            document.getElementById("totalCamarasCard").textContent = String(camaras.length);

            camaras.forEach(c => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${c.idCamara || c.id_camara || '---'}</td>
                    <td>${c.capacidad || 0} unidades</td>
                    <td><span style="color: #4ade80; font-weight: 600;">${c.estadoCadenaFrio || c.estado_cadena_frio || 'Óptimo'}</span></td>
                `;
                tbody.appendChild(tr);
            });
        }
    } catch (error) {
        console.error("Error al cargar cámaras:", error);
    }
}