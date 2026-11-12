
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

            // Aplicar el filtro por rol
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

    await cargarClientesYVentas();

    const formVenta = document.getElementById("formVenta");
    if (formVenta) {
        formVenta.addEventListener("submit", async (e) => {
            e.preventDefault();

            const idCliente = document.getElementById("clienteVenta").value;
            const fechaVenta = document.getElementById("fechaVenta").value;
            const totalFacturado = document.getElementById("totalVenta").value;

            if (!idCliente || !fechaVenta || !totalFacturado || parseFloat(totalFacturado) <= 0) {
                alert("Por favor completa los datos de la venta correctamente.");
                return;
            }

            try {
                const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/ventas', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    credentials: 'include',
                    body: JSON.stringify({
                        idCliente: parseInt(idCliente, 10),
                        fechaVenta: `${fechaVenta}T00:00:00`,
                        totalFacturado: parseFloat(totalFacturado)
                    })
                });

                const data = await response.json();
                if ((response.ok || response.status === 201) && data.exito) {
                    alert("¡Venta registrada con éxito!");
                    formVenta.reset();
                    cargarVentas();
                } else {
                    alert(data.error || "No se pudo registrar la venta.");
                }
            } catch (error) {
                console.error("Error al guardar venta:", error);
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

let clientesCache = [];
const formatoMoneda = new Intl.NumberFormat("es-AR", { style: "currency", currency: "ARS" });

async function cargarClientesYVentas() {
    const campoFecha = document.getElementById("fechaVenta");
    if (campoFecha) {
        const hoy = new Date().toISOString().split('T')[0];
        campoFecha.value = hoy;
    }

    try {
        const responseClientes = await fetch('http://127.0.0.1:8080/ProyectoAvicola/clientes', {
            method: 'GET',
            credentials: 'include'
        });

        if (responseClientes.ok) {
            clientesCache = await responseClientes.json();
            const select = document.getElementById("clienteVenta");
            if (select) {
                select.innerHTML = '<option value="">Seleccioná un cliente...</option>';
                clientesCache.forEach(c => {
                    const opcion = document.createElement("option");
                    opcion.value = c.idCliente;
                    opcion.textContent = `Cliente #${c.idCliente}`;
                    select.appendChild(opcion);
                });
            }
        }
    } catch (error) {
        console.error("Error al cargar clientes:", error);
    }

    cargarVentas();
}

async function cargarVentas() {
    try {
        const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/ventas', {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok) {
            const ventas = await response.json();
            const tbody = document.getElementById("tablaVentasBody");
            tbody.innerHTML = "";

            let totalMontoAcumulado = 0;
            let cantidadVentas = ventas.length;

            if (cantidadVentas === 0) {
                tbody.innerHTML = `<tr><td colspan="4" style="text-align: center; color: var(--text-muted);">No hay ventas registradas todavía.</td></tr>`;
                const elemTotalVentas = document.getElementById("totalVentas");
                const elemMontoTotal = document.getElementById("montoTotal");
                if (elemTotalVentas) elemTotalVentas.textContent = "0";
                if (elemMontoTotal) elemMontoTotal.textContent = formatoMoneda.format(0);
                return;
            }

            ventas.sort((a, b) => b.idVenta - a.idVenta);

            ventas.forEach(v => {
                totalMontoAcumulado += v.totalFacturado;

                const cliente = clientesCache.find(c => c.idCliente === v.idCliente);
                const infoCliente = cliente ? `Cliente #${cliente.idCliente}` : `Cliente #${v.idCliente}`;

                let fechaFormateada = "---";
                if (v.fechaVenta) {
                    const soloFecha = v.fechaVenta.split("T")[0];
                    const [anio, mes, dia] = soloFecha.split("-");
                    fechaFormateada = `${dia}/${mes}/${anio}`;
                }

                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${v.idVenta}</td>
                    <td>${infoCliente}</td>
                    <td>${fechaFormateada}</td>
                    <td class="col-monto">${formatoMoneda.format(v.totalFacturado)}</td>
                `;
                tbody.appendChild(tr);
            });

            const elemTotalVentas = document.getElementById("totalVentas");
            const elemMontoTotal = document.getElementById("montoTotal");
            if (elemTotalVentas) elemTotalVentas.textContent = String(cantidadVentas);
            if (elemMontoTotal) elemMontoTotal.textContent = formatoMoneda.format(totalMontoAcumulado);
        }
    } catch (error) {
        console.error("Error al cargar ventas:", error);
    }
}


function aplicarFiltroRoles(usuario) {
    if (usuario.idRol === 1) { // Cliente
        console.log("Cliente detectado: Ocultando panel para crear nuevas ventas.");
        const formCard = document.querySelector(".form-card");
        if (formCard) {
            formCard.style.display = "none";
        }
    }
}