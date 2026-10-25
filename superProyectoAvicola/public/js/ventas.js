/* ==========================================
   VENTAS - AVÍCOLA SAN MARTÍN
   Versión de prueba: los datos viven solo en memoria.
   No usa localStorage/sessionStorage ni fetch todavía.
   Al recargar la página los datos vuelven al estado inicial.
   ========================================== */

// ---------- 1. DATOS DE EJEMPLO (simulan las tablas clientes y ventas) ----------
const clientes = [
    { idCliente: 1, nombre: "Supermercado El Sol" },
    { idCliente: 2, nombre: "Carnicería Don Pedro" },
    { idCliente: 3, nombre: "Restaurante La Estancia" },
    { idCliente: 4, nombre: "Distribuidora Norte" }
];

const ventas = [
    { idVenta: 1, idCliente: 1, fechaVenta: "2026-10-18", totalFacturado: 185000.50 },
    { idVenta: 2, idCliente: 3, fechaVenta: "2026-10-20", totalFacturado: 74300.00 },
    { idVenta: 3, idCliente: 2, fechaVenta: "2026-10-23", totalFacturado: 129900.75 }
];

// Siguiente ID disponible (en la base real lo genera AUTO_INCREMENT)
let proximoIdVenta = ventas.length + 1;

// ---------- 2. UTILIDADES ----------
const formatoMoneda = new Intl.NumberFormat("es-AR", {
    style: "currency",
    currency: "ARS"
});

function obtenerNombreCliente(idCliente) {
    const cliente = clientes.find(c => c.idCliente === idCliente);
    return cliente ? cliente.nombre : "Cliente desconocido";
}

// Fecha "YYYY-MM-DD" -> "DD/MM/YYYY" sin pasar por Date (evita desfases de zona horaria)
function formatearFecha(fechaIso) {
    const [anio, mes, dia] = fechaIso.split("-");
    return `${dia}/${mes}/${anio}`;
}

function fechaDeHoyIso() {
    const hoy = new Date();
    const mes = String(hoy.getMonth() + 1).padStart(2, "0");
    const dia = String(hoy.getDate()).padStart(2, "0");
    return `${hoy.getFullYear()}-${mes}-${dia}`;
}

// Crea una celda <td> con texto seguro (sin innerHTML)
function crearCelda(texto, clase) {
    const td = document.createElement("td");
    td.append(document.createTextNode(texto));
    if (clase) {
        td.classList.add(clase);
    }
    return td;
}

// Reemplaza el contenido de un elemento por un texto
function mostrarTexto(elemento, texto) {
    elemento.replaceChildren(document.createTextNode(texto));
}

// ---------- 3. RENDERIZADO ----------
function cargarClientesEnSelect() {
    const select = document.getElementById("clienteVenta");

    clientes.forEach(cliente => {
        const opcion = document.createElement("option");
        opcion.append(document.createTextNode(cliente.nombre));
        opcion.value = cliente.idCliente;
        select.appendChild(opcion);
    });
}

function pintarTablaVentas() {
    const tbody = document.getElementById("tablaVentasBody");
    tbody.replaceChildren();

    if (ventas.length === 0) {
        const tr = document.createElement("tr");
        const td = crearCelda("No hay ventas registradas todavía.", "fila-vacia");
        td.colSpan = 4;
        tr.appendChild(td);
        tbody.appendChild(tr);
        return;
    }

    // Más recientes primero
    const ordenadas = [...ventas].sort((a, b) => b.idVenta - a.idVenta);

    ordenadas.forEach(v => {
        const tr = document.createElement("tr");
        tr.appendChild(crearCelda(String(v.idVenta)));
        tr.appendChild(crearCelda(obtenerNombreCliente(v.idCliente)));
        tr.appendChild(crearCelda(formatearFecha(v.fechaVenta)));
        tr.appendChild(crearCelda(formatoMoneda.format(v.totalFacturado), "col-monto"));
        tbody.appendChild(tr);
    });
}

function actualizarResumen() {
    const total = ventas.reduce((acumulado, v) => acumulado + v.totalFacturado, 0);

    mostrarTexto(document.getElementById("totalVentas"), String(ventas.length));
    mostrarTexto(document.getElementById("montoTotal"), formatoMoneda.format(total));
}

// ---------- 4. FORMULARIO ----------
function manejarEnvioFormulario(evento) {
    evento.preventDefault();

    const campoCliente = document.getElementById("clienteVenta");
    const campoFecha = document.getElementById("fechaVenta");
    const campoTotal = document.getElementById("totalVenta");
    const mensajeError = document.getElementById("mensajeError");

    const idCliente = parseInt(campoCliente.value, 10);
    const fechaVenta = campoFecha.value;
    const totalFacturado = parseFloat(campoTotal.value);

    // Validaciones
    if (Number.isNaN(idCliente)) {
        mostrarTexto(mensajeError, "Seleccioná un cliente.");
        return;
    }
    if (!fechaVenta) {
        mostrarTexto(mensajeError, "Indicá la fecha de la venta.");
        return;
    }
    if (fechaVenta > fechaDeHoyIso()) {
        mostrarTexto(mensajeError, "La fecha no puede ser futura.");
        return;
    }
    if (Number.isNaN(totalFacturado) || totalFacturado <= 0) {
        mostrarTexto(mensajeError, "El total debe ser un número mayor a 0.");
        return;
    }

    mostrarTexto(mensajeError, "");

    // Alta en memoria (más adelante esto será un POST al servlet de Java)
    ventas.push({
        idVenta: proximoIdVenta,
        idCliente: idCliente,
        fechaVenta: fechaVenta,
        totalFacturado: Math.round(totalFacturado * 100) / 100
    });
    proximoIdVenta++;

    evento.target.reset();
    campoFecha.value = fechaDeHoyIso();

    pintarTablaVentas();
    actualizarResumen();
}

// ---------- 5. INICIO ----------
document.addEventListener("DOMContentLoaded", () => {
    cargarClientesEnSelect();

    // La fecha arranca en el día de hoy y no permite fechas futuras
    const campoFecha = document.getElementById("fechaVenta");
    campoFecha.value = fechaDeHoyIso();
    campoFecha.max = fechaDeHoyIso();

    pintarTablaVentas();
    actualizarResumen();

    document.getElementById("formVenta").addEventListener("submit", manejarEnvioFormulario);

    // Botón de Cerrar Sesión (igual que en las otras vistas)
    document.getElementById("btnLogout").addEventListener("click", () => {
        window.location.assign("login.html");
    });
});
