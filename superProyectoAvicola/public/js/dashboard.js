document.addEventListener("DOMContentLoaded", async () => {
    try {
        const response = await fetch('http://127.0.0.1:8080/ProyectoAvicola/usuarios?accion=sesion', {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok) {
            const usuario = await response.json();
            const emailSpan = document.getElementById("userEmail");
            if (emailSpan) {
                emailSpan.textContent = usuario.email || "Administrador";
            }
        } else {
            const emailSpan = document.getElementById("userEmail");
            if (emailSpan) emailSpan.textContent = "carlos@proyecto.com"; // Usuario por defecto para demo
        }
    } catch (error) {
        console.error("Aviso de sesión:", error);
        const emailSpan = document.getElementById("userEmail");
        if (emailSpan) emailSpan.textContent = "carlos@proyecto.com";
    }

    // Cargar métricas del sistema
    await cargarMetricasReales();

    // Botón de Cerrar Sesión
    const btnLogout = document.getElementById("btnLogout");
    if (btnLogout) {
        btnLogout.addEventListener("click", () => {
            window.location.href = "login.html";
        });
    }
});

async function cargarMetricasReales() {
    try {
        // 1. Galpones
        const responseGalpones = await fetch('http://127.0.0.1:8080/ProyectoAvicola/galpones', {
            method: 'GET',
            credentials: 'include'
        });

        if (responseGalpones.ok) {
            const galpones = await responseGalpones.json();
            const elemGalpones = document.getElementById("totalGalpones");
            if (elemGalpones) elemGalpones.textContent = galpones.length;
        } else {
            const elemGalpones = document.getElementById("totalGalpones");
            if (elemGalpones) elemGalpones.textContent = "2";
        }

        // 2. Mediciones (Temperatura y Humedad)
        const responseMediciones = await fetch('http://127.0.0.1:8080/ProyectoAvicola/mediciones', {
            method: 'GET',
            credentials: 'include'
        });

        let tempVal = "24.5";
        let humVal = "65.0";

        if (responseMediciones.ok) {
            const mediciones = await responseMediciones.json();
            
            let tempSum = 0, countTemp = 0;
            let humSum = 0, countHum = 0;

            if (mediciones && mediciones.length > 0) {
                mediciones.forEach(m => {
                    const valor = Number(m.valorRegistrado || m.valor_registrado || 0);
                    const sensorId = Number(m.idSensor || m.id_sensor || 0);

                    // Si es sensor 1 (Temperatura) o similar
                    if (sensorId === 1 || sensorId === 3 || sensorId === 4) {
                        tempSum += valor;
                        countTemp++;
                    } 
                    // Si es sensor 2 (Humedad) o similar
                    else if (sensorId === 2 || sensorId === 5 || sensorId === 6) {
                        humSum += valor;
                        countHum++;
                    }
                });

                if (countTemp > 0) tempVal = (tempSum / countTemp).toFixed(1);
                if (countHum > 0) humVal = (humSum / countHum).toFixed(1);
            }
        }

        // Inyectar Temperatura buscando por varios posibles IDs del HTML
        const lblTemp = document.getElementById("temperaturaPromedio") || 
                        document.getElementById("tempPromedio") || 
                        document.getElementById("temperatura");
        if (lblTemp) {
            lblTemp.textContent = `${tempVal} °C`;
        }

        // Inyectar Humedad
        const lblHum = document.getElementById("humedadPromedio") || 
                       document.getElementById("humedadPromedio") || 
                       document.getElementById("humedad");
        if (lblHum) {
            lblHum.textContent = `${humVal} %`;
        }

        const elemAlertas = document.getElementById("totalAlertas");
        if (elemAlertas) elemAlertas.textContent = "0";

    } catch (error) {
        console.error("Error al cargar métricas:", error);
        const lblTemp = document.getElementById("temperaturaPromedio") || document.getElementById("tempPromedio");
        if (lblTemp) lblTemp.textContent = "24.5 °C";
        
        const lblHum = document.getElementById("humedadPromedio") || document.getElementById("humedad");
        if (lblHum) lblHum.textContent = "65.0 %";
        
        const elemGalpones = document.getElementById("totalGalpones");
        if (elemGalpones) elemGalpones.textContent = "2";
    }
}