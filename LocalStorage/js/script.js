let lista = document.getElementById("lista");
let input = document.getElementById("tarea");
let boton = document.getElementById("btnAgregar");

let tareas = JSON.parse(localStorage.getItem("tareas")) || [];

function mostrarTareas() {
    lista.innerHTML = "";
    for (let i = 0; i < tareas.length; i++) {
        let li = document.createElement("li");
        li.textContent = tareas[i].texto;

        if (tareas[i].hecho) {
            li.classList.add("completada");
        }

        li.onclick = function() {
            tareas[i].hecho = !tareas[i].hecho;
            guardar();
            mostrarTareas();
        }

        lista.appendChild(li);
    }
}

function guardar() {
    localStorage.setItem("tareas", JSON.stringify(tareas));
}

boton.onclick = function() {
    if (input.value !== "") {
        tareas.push({ texto: input.value, hecho: false });
        input.value = "";
        guardar();
        mostrarTareas();
    }
};

mostrarTareas();