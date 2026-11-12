/* ==========================================
   STOCK E INSUMOS - AVÍCOLA SAN MARTÍN
   ========================================== */

document.addEventListener("DOMContentLoaded", async () => {
    try {
        // 1. Verificación de sesión
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

            // Ocultar formulario si es cliente (idRol === 1)
            if (Number(usuario.idRol) === 1) {
                const formCard = document.querySelector(".form-card");
                if (formCard) formCard.style.display = "none";
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

    // 2. Cargar inventario inicial al abrir la página
    cargarStock();

    // 3. Manejo del formulario de registro de insumos
    const formInsumo = document.getElementById("formInsumo"); // Asegúrate de que tu formulario tenga este ID en stock.html
    if (formInsumo) {
        formInsumo.addEventListener("submit", async (e) => {
            e.preventDefault();

            // Tomamos los valores de los inputs (ajusta los IDs si en tu HTML tienen otros nombres)
            const descripcionInsumo = document.getElementById("nombreInsumo").value; 
            const cantidad = document.getElementById("cantidadInsumo").value;

            try {
                const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/insumos', { // Endpoint del servlet de insumos
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    credentials: 'include',
                    body: JSON.stringify({
                        descripcionInsumo: descripcionInsumo, // Coincide exactamente con InsumoCompra.java
                        cantidad: parseInt(cantidad, 10)
                    })
                });

                const data = await response.json().catch(() => ({}));

                if (response.ok || response.status === 201) {
                    formInsumo.reset();
                    cargarStock(); // Recarga la tabla al instante
                    alert("¡Insumo registrado con éxito!");
                } else {
                    alert(data.error || "No se pudo registrar el insumo.");
                }
            } catch (error) {
                console.error("Error al guardar insumo:", error);
                alert("Error de conexión con el servidor.");
            }
        });
    }

    // 4. Botón Cerrar Sesión
    const btnLogout = document.getElementById("btnLogout");
    if (btnLogout) {
        btnLogout.addEventListener("click", () => {
            window.location.href = "login.html";
        });
    }
});

// Función para obtener y listar el stock actual en la tabla
async function cargarStock() {
    try {
        const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/insumos', {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok) {
            const registros = await response.json();
            const tbody = document.getElementById("tablaStockBody"); // Asegúrate de que el tbody tenga este ID en stock.html
            if (!tbody) return;
            
            tbody.innerHTML = "";

            if (registros.length === 0) {
                tbody.innerHTML = `<tr><td colspan="3" class="fila-vacia">No hay insumos registrados.</td></tr>`;
                const totalReg = document.getElementById("totalRegistros");
                if (totalReg) totalReg.textContent = "0";
                return;
            }

            const totalReg = document.getElementById("totalRegistros");
            if (totalReg) totalReg.textContent = registros.length;

            registros.forEach(i => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${i.idCompra || i.idInsumo || i.id || '---'}</td>
                    <td>${i.descripcionInsumo || i.descripcion || 'Insumo sin descripción'}</td>
                    <td>${i.cantidad || 0} unidades</td>
                `;
                tbody.appendChild(tr);
            });
        }
    } catch (error) {
        console.error("Error al cargar stock:", error);
    }
}