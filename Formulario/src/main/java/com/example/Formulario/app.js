const formulario = document.querySelector(".formulario");
const campos = document.querySelectorAll(".campoObligatorio");
const mostrarError = document.querySelector(".mostrarError");

formulario.addEventListener("submit", (e) => {
    let camposVacios = false;
    mostrarError.innerHTML = "";

    campos.forEach(campo => {
        if (campo.value.trim() === "") {
            campo.classList.add("rojo");
            campo.classList.remove("verde");
            camposVacios = true;
        }
    });

    if (camposVacios) {
        e.preventDefault();
        mostrarError.innerHTML = "<p>⚠️ Faltan llenar campos obligatorios</p>";
    }
});

campos.forEach(campo => {
    campo.addEventListener("keydown", () => {
        if (campo.value.trim() !== "") {
            campo.classList.add("verde");
            campo.classList.remove("rojo");
        }
    });

    campo.addEventListener("focusout", () => {
        if (campo.value.trim() === "") {
            campo.classList.add("rojo");
            campo.classList.remove("verde");
        } else {
            campo.classList.add("verde");
            campo.classList.remove("rojo");
        }
    });
});
