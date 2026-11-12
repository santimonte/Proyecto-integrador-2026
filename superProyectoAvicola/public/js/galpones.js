/* ==========================================
   GALPONES - AVÍCOLA SAN MARTÍN
   ========================================== */

document.addEventListener("DOMContentLoaded", async () => {
    try {
        const sessionResponse = await fetch('http://127.0.0.1:8080/ProyectoAvicola/usuarios?accion=sesion', {
            method: 'GET',
            credentials: 'include'
        });

        if (sessionResponse.ok) {
            const usuario = await sessionResponse.json();
            
            // Mostramos el email del usuario logueado en la barra superior
            const emailSpan = document.getElementById("userEmail");
            if (emailSpan) {
                emailSpan.textContent = usuario.email;
            }

            // Aplicar el filtro por rol de forma estricta
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

    // Cargar galpones reales
    cargarGalpones();

    // Guardar galpón (Formulario)
    const formGalpon = document.getElementById("formGalpon");
    if (formGalpon) {
        formGalpon.addEventListener("submit", async (e) => {
            e.preventDefault();

            const nombreGalpon = document.getElementById("nombreGalpon").value.trim();
            const capacidadMaxima = document.getElementById("capacidadMaxima").value.trim();

            if (!nombreGalpon || !capacidadMaxima) {
                alert("Por favor completa todos los campos requeridos.");
                return;
            }

            try {
                const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/galpones', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    credentials: 'include',
                    body: JSON.stringify({
                        nombreGalpon: nombreGalpon,
                        capacidadMaxima: capacidadMaxima
                    })
                });

                const data = await response.json();
                if ((response.ok || response.status === 201) && data.exito) {
                    alert("¡Galpón registrado con éxito!");
                    formGalpon.reset();
                    cargarGalpones();
                } else {
                    alert(data.error || "No se pudo registrar el galpón.");
                }
            } catch (error) {
                console.error("Error al guardar galpón:", error);
                alert("Error de conexión con el servidor de Java.");
            }
        });
    }

    // Botón de Cerrar Sesión
    const btnLogout = document.getElementById("btnLogout");
    if (btnLogout) {
        btnLogout.addEventListener("click", () => {
            window.location.href = "login.html";
        });
    }
});

async function cargarGalpones() {
    try {
        const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/galpones', {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok) {
            const galpones = await response.json();
            const tbody = document.getElementById("tablaGalponesBody");
            tbody.innerHTML = "";

            if (galpones.length === 0) {
                tbody.innerHTML = `<tr><td colspan="3" class="fila-vacia">No hay galpones registrados todavía.</td></tr>`;
                const cardTotal = document.getElementById("totalGalponesCard");
                if (cardTotal) cardTotal.textContent = "0";
                return;
            }

            const cardTotal = document.getElementById("totalGalponesCard");
            if (cardTotal) cardTotal.textContent = String(galpones.length);

            galpones.forEach(g => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${g.idGalpon}</td>
                    <td>${g.nombreGalpon}</td>
                    <td>${g.capacidadMaxima} aves</td>
                `;
                tbody.appendChild(tr);
            });
        }
    } catch (error) {
        console.error("Error al cargar galpones:", error);
    }
}

// ==========================================
// FUNCIÓN REUTILIZABLE: CONTROL DE ROLES
// ==========================================
function aplicarFiltroRoles(usuario) {
    const rolNumerico = Number(usuario.idRol);
    
    // Si es Cliente (Rol 1), ocultamos el formulario de alta de galpones
    if (rolNumerico === 1) {
        const cardFormulario = document.getElementById("cardFormularioGalpon");
        if (cardFormulario) {
            cardFormulario.style.setProperty("display", "none", "important");
        }
    }
}