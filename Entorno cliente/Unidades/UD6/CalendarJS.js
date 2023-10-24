// Obtener la fecha actual
const fechaActual = new Date();
const mesActual = fechaActual.getMonth();
const anioActual = fechaActual.getFullYear();

// Obtener el primer día del mes actual
const primerDiaDelMes = new Date(anioActual, mesActual, 1);

// Obtener el último día del mes actual
const ultimoDiaDelMes = new Date(anioActual, mesActual + 1, 0);

// Crear una tabla HTML para el calendario
const calendario = document.createElement('table');
calendario.className = 'calendario';

// Crear una fila de encabezado con los nombres de los días de la semana
const filaEncabezado = document.createElement('tr');
const diasSemana = ['Dom', 'Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb'];
diasSemana.forEach(dia => {
    const celdaEncabezado = document.createElement('th');
    celdaEncabezado.textContent = dia;
    filaEncabezado.appendChild(celdaEncabezado);
});
calendario.appendChild(filaEncabezado);

// Calcular el número de filas necesarias para mostrar todos los días del mes
const numFilas = Math.ceil((ultimoDiaDelMes.getDate() + primerDiaDelMes.getDay()) / 7);

// Llevar un contador para los días del mes
let dia = 1;

// Crear filas y celdas para el calendario
for (let i = 0; i < numFilas; i++) {
    const fila = document.createElement('tr');

    for (let j = 0; j < 7; j++) {
        const celda = document.createElement('td');

        if (i === 0 && j < primerDiaDelMes.getDay()) {
            // Celdas vacías antes del primer día del mes
            celda.textContent = '';
        } else if (dia <= ultimoDiaDelMes.getDate()) {
            // Mostrar los números de los días del mes
            celda.textContent = dia;
            dia++;
        }

        fila.appendChild(celda);
    }

    calendario.appendChild(fila);
}

// Agregar el calendario al documento
document.body.appendChild(calendario);
