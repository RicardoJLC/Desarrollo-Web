
const formulario = document.getElementById('taskForm');
const tituloInput = document.getElementById('title');
const fechaInput = document.getElementById('dueDate');
const prioridadSelect = document.getElementById('priority');
const listaTareas = document.getElementById('tasks');
const mensaje = document.getElementById('message');

// Cargar las tareas guardadas (si existen) desde el Local Storage
let tareas = JSON.parse(localStorage.getItem('tareasGuardadas')) || [];

// === Mostrar mensaje temporal en pantalla ===
function mostrarMensaje(texto, color = 'var(--success)') {
  mensaje.textContent = texto;
  mensaje.style.color = color;
  setTimeout(() => mensaje.textContent = '', 2000);
}

// === Guardar tareas en Local Storage ===
function guardarTareas() {
  localStorage.setItem('tareasGuardadas', JSON.stringify(tareas));
}

// === Mostrar tareas en pantalla ===
function mostrarTareas() {
  listaTareas.innerHTML = '';

  if (tareas.length === 0) {
    listaTareas.innerHTML = '<li>No hay tareas registradas.</li>';
    return;
  }

  tareas.forEach((tarea, indice) => {
    const li = document.createElement('li');
    li.className = `task-item ${tarea.completada ? 'completed' : ''}`;

    li.innerHTML = `
      <div>
        <input type="checkbox" ${tarea.completada ? 'checked' : ''} data-indice="${indice}">
        <strong>${tarea.titulo}</strong> - ${tarea.prioridad}
        <small>(Fecha límite: ${tarea.fecha})</small>
      </div>
      <button class="delete-btn" data-indice="${indice}">Eliminar</button>
    `;

    listaTareas.appendChild(li);
  });
}

// esto es para --> Añadir nueva tarea
formulario.addEventListener('submit', (evento) => {
  evento.preventDefault();

  // Validar que el formulario esté correctamente llenado
  if (!formulario.reportValidity()) return;

  const nuevaTarea = {
    titulo: tituloInput.value.trim(),
    fecha: fechaInput.value,
    prioridad: prioridadSelect.value,
    completada: false
  };

  tareas.push(nuevaTarea);
  guardarTareas();
  mostrarTareas();

  formulario.reset();
  mostrarMensaje('¡Tarea añadida con éxito!');
});

// === Evento: marcar tarea como completada o eliminarla ===
listaTareas.addEventListener('click', (evento) => {
  const indice = evento.target.dataset.indice;

  if (evento.target.type === 'checkbox') {
    tareas[indice].completada = evento.target.checked;
    guardarTareas();
    mostrarTareas();
  } else if (evento.target.classList.contains('delete-btn')) {
    tareas.splice(indice, 1);
    guardarTareas();
    mostrarTareas();
    mostrarMensaje('Tarea eliminada', 'var(--danger)');
  }
});

// === Cargar y mostrar tareas al abrir la página ===
mostrarTareas();

