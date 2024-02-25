let txtNombre = document.getElementById("Name"); //Nombre
let txtApellido = document.getElementById("Name2"); //Apellido
let txtEmail = document.getElementById("Email"); //Correo electrónico
let txtNumber = document.getElementById("Number"); //Teléfono
let btnAgregar = document.getElementById("btnAgregar");
let btnClear = document.getElementById("btnClear");
let alertValidaciones = document.getElementById("alertValidaciones");
let tablaListaContactos = document.getElementById("tablaListaContactos");
let cuerpoTabla = tablaListaContactos.getElementsByTagName("tbody").item(0);

let datos = new Array(); //Aquí vamos a almacenar los elementos de la tabla

function validarCantidad() {
    if(txtNumber.value.length==0){
        return false
    } //if length
    if (isNaN(txtNumber.value)){
        return false;
    }//isNaN
    if(parseFloat(txtNumber.value)<=0){
        return false
    }//<=0
    return true
}//Validar cantidad

btnAgregar.addEventListener("click", function(event) {
    let isValid = true;
    event.preventDefault();
    alertValidaciones.innerHTML="";
    alertValidaciones.style.display="none";
    txtNombre.style.border="";
    txtApellido.style.border="";
    txtEmail.style.border="";
    txtNumber.style.border="";

    if (txtNombre.value.length<3) {
        alertValidaciones.innerHTML="El campo <strong>Nombre</strong> es requerido</br>";
        alertValidaciones.style.display="block";
        txtNombre.style.border="solid thin red";
        isValid = false;
    } //txtNombre < 3
    if (txtApellido.value.length<3) {
        alertValidaciones.innerHTML+="El campo <strong>Apellido</strong> es requerido</br>";
        alertValidaciones.style.display="block";
        txtApellido.style.border="solid thin red";
        isValid = false;
    } //txtNombre < 3
    if (txtEmail.value.length<3) {
        alertValidaciones.innerHTML+="El campo <strong>Email</strong> es requerido</br>";
        alertValidaciones.style.display="block";
        txtEmail.style.border="solid thin red";
        isValid = false;
    } //txtNombre < 3
    if(! validarCantidad()){
        alertValidaciones.innerHTML+="El campo <strong>Teléfono</strong> es requerido";
        alertValidaciones.style.display="block";
        txtNumber.style.border="solid thin red";
        isValid = false;
    }//If ! validarCantidad
    if (isValid) {
        let row = `<tr>
                        <td>${txtNombre.value}</td>
                        <td>${txtApellido.value}</td>
                        <td>${txtEmail.value}</td>
                    </tr>`;
        let elemento = `{"nombre" : "${txtNombre.value}", "apellido" : "${txtApellido.value}", "email" : "${txtEmail.value}"}`;
        datos.push(JSON.parse(elemento));
        localStorage.setItem("datos", JSON.stringify(datos));

        cuerpoTabla.insertAdjacentHTML("beforeend", row);

        localStorage.setItem("nombre", txtNombre);
        localStorage.setItem("apellido", txtApellido);
        localStorage.setItem("email", txtEmail);
        localStorage.setItem("telefono", txtNumber);

        txtNombre.value="";
        txtApellido.value="";
        txtEmail.value="";
        txtNumber.value="";
        txtNombre.focus();
    } //isValid
}); //btnAgregar click

btnClear.addEventListener("click", function(event){
    event.preventDefault();
    alertValidaciones.innerHTML="";
    alertValidaciones.style.display="none";
    txtNombre.style.border="";
    txtApellido.style.border="";
    txtEmail.style.border="";
    txtNumber.style.border="";
    txtNombre.value="";
    txtApellido.value="";
    txtEmail.value="";
    txtNumber.value="";
    cuerpoTabla.innerHTML="";
    datos = [];
    localStorage.clear();
});//btnClear click

window.addEventListener("load", function(event){
    if(this.localStorage.getItem("datos")!=null){
        datos = JSON.parse(localStorage.getItem("datos"));
        datos.forEach((r) =>{
            let row = `<tr>
                        <td>${r.nombre}</td>
                        <td>${r.apellido}</td>
                        <td>${r.email}</td>
                        <td>${r.telefono}</td>
                    </tr>`;
                    cuerpoTabla.insertAdjacentHTML("beforeend", row);
        });
    }//datos!=null
});