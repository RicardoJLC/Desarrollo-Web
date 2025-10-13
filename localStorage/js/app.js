const btnGuardar = document.querySelector(".btnGuardar");
const nombre = document.getElementById("nombre");
const apellido = document.getElementById("apellido");
const nacimiento = document.getElementById("nacimiento");
const saludo = document.querySelector(".saludo");

// Mostrar datos guardados al cargar la página
const guardado = localStorage.getItem("datosUsuario");
if (guardado) {
  const datos = JSON.parse(guardado);
  saludo.textContent = `Guardado: ${datos.nombre} ${datos.apellido}, nacido el ${datos.nacimiento}`;
}

// Guardar datos en localStorage
btnGuardar.addEventListener("click", () => {
  const datos = {
    nombre: nombre.value,
    apellido: apellido.value,
    nacimiento: nacimiento.value
  };

  localStorage.setItem("datosUsuario", JSON.stringify(datos));
  saludo.textContent = `Guardado: ${datos.nombre} ${datos.apellido}, nacido el ${datos.nacimiento}`;
});
