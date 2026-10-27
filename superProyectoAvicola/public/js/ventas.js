/* ==========================================
   VENTAS - AVÍCOLA SAN MARTÍN
   ========================================== */

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

    // 2. Cargar datos iniciales (Primero clientes, luego ventas)
    await cargarClientesYVentas();

    // 3. Manejar el envío del formulario para registrar una nueva venta
    const formVenta = document.getElementById("formVenta");
    if (formVenta) {
        formVenta.addEventListener("submit", async (e) => {
            e.preventDefault();

            const idCliente = document.getElementById("clienteVenta").value;
            const fechaVenta = document.getElementById("fechaVenta").value;
            const totalFacturado = document.getElementById("totalVenta").value;

            // Validaciones básicas antes de enviar
            if (!idCliente) {
                alert("Por favor, seleccioná un cliente.");
                return;
            }
            if (!fechaVenta) {
                alert("Por favor, indicá la fecha de la venta.");
                return;
            }
            if (!totalFacturado || parseFloat(totalFacturado) <= 0) {
                alert("El total debe ser un número mayor a 0.");
                return;
            }

            try {
                // Hacemos el POST al servlet amarrando el formato LocalDateTime (T00:00:00)
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
                    
                    // Colocar la fecha de hoy por defecto de nuevo tras el reset
                    const campoFecha = document.getElementById("fechaVenta");
                    if (campoFecha) {
                        const hoy = new Date().toISOString().split('T')[0];
                        campoFecha.value = hoy;
                    }
                    
                    cargarVentas(); // Recargar el listado y el resumen automáticamente
                } else {
                    alert(data.error || "No se pudo registrar la venta.");
                }
            } catch (error) {
                console.error("Error al guardar venta:", error);
                alert("Error de conexión con el servidor de Java.");
            }
        });
    }

    // 4. Botón de Cerrar Sesión
    document.getElementById("btnLogout").addEventListener("click", () => {
        window.location.href = "login.html";
    });
});

// Cache temporal para cruzar el id_cliente con sus datos legibles en la tabla
let clientesCache = [];

// Utilidad interna para formatear números a pesos argentinos
const formatoMoneda = new Intl.NumberFormat("es-AR", {
    style: "currency",
    currency: "ARS"
});

// Función intermedio para garantizar que los clientes carguen antes de pintar las ventas
async function cargarClientesYVentas() {
    // Seteamos fecha de hoy por defecto en el input de fecha
    const campoFecha = document.getElementById("fechaVenta");
    if (campoFecha) {
        const hoy = new Date().toISOString().split('T')[0];
        campoFecha.value = hoy;
        campoFecha.max = hoy; // Evita fechas futuras en la UI
    }

    try {
        // Obtenemos los clientes para armar el Select y la caché
        const responseClientes = await fetch('http://127.0.0.1:8080/ProyectoAvicola/clientes', {
            method: 'GET',
            credentials: 'include'
        });

        if (responseClientes.ok) {
            clientesCache = await responseClientes.json();
            const select = document.getElementById("clienteVenta");
            select.innerHTML = '<option value="">Seleccioná un cliente...</option>';

            clientesCache.forEach(c => {
                const opcion = document.createElement("option");
                opcion.value = c.idCliente;
                // CORREGIDO: Ya no muestra el número de teléfono en el select
                opcion.textContent = `Cliente #${c.idCliente}`;
                select.appendChild(opcion);
            });
        }
    } catch (error) {
        console.error("Error al cargar clientes iniciales:", error);
    }

    // Una vez que los clientes están mapeados, cargamos e imprimimos las ventas
    cargarVentas();
}

// Función para obtener, calcular resumen y pintar las ventas en la tabla
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

            // Inicializadores para el resumen acumulado
            let totalMontoAcumulado = 0;
            let cantidadVentas = ventas.length;

            if (cantidadVentas === 0) {
                tbody.innerHTML = `<tr><td colspan="4" style="text-align: center; color: var(--text-muted);">No hay ventas registradas todavía.</td></tr>`;
                document.getElementById("totalVentas").textContent = "0";
                document.getElementById("montoTotal").textContent = formatoMoneda.format(0);
                return;
            }

            // Ordenamos por idVenta descendente (las más nuevas arriba de todo)
            ventas.sort((a, b) => b.idVenta - a.idVenta);

            ventas.forEach(v => {
                totalMontoAcumulado += v.totalFacturado;

                // CORREGIDO: Buscamos el cliente y armamos la info limpia sin el número de teléfono
                const cliente = clientesCache.find(c => c.idCliente === v.idCliente);
                const infoCliente = cliente ? `Cliente #${cliente.idCliente}` : `Cliente #${v.idCliente}`;

                // Parsear fecha ISO (YYYY-MM-DD o YYYY-MM-DDTHH:MM:SS) -> DD/MM/YYYY
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

            // Actualizamos los cuadros de texto del resumen superior o inferior
            document.getElementById("totalVentas").textContent = String(cantidadVentas);
            document.getElementById("montoTotal").textContent = formatoMoneda.format(totalMontoAcumulado);

        } else {
            console.error("Error al obtener las ventas de la base de datos");
        }
    } catch (error) {
        console.error("Error de red al cargar ventas:", error);
    }
}
