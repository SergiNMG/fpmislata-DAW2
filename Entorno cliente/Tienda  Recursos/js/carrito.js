class Carrito {
	constructor(id) {
		this.articulos = []
		this.id = id
	}

	anyadeArticulo(articulo) {
		this.articulos.push(articulo)
		this.verCarrito(articulo)
		//console.log(this.articulos)
	}

	//codigoArticulo = this.articulos.forEach(a => a.codigo)

	borraArticulo(codigoArticulo) {

	}

	modificaUnidades(codigo, n) {
	}

	verCarrito(articulo) {
		let tabla = document.getElementById("tablaCarrito")
		let filaProducto = document.createElement("tr")

		let cImagen = document.createElement("td")
		let imgProducto = document.createElement("img")
		imgProducto.className = "imgCarrito"
		imgProducto.src = `./assets/${articulo.codigo}.jpg`
		cImagen.appendChild(imgProducto)

		let cNombre = document.createElement("td")
		cNombre.textContent = articulo.nombre

		let cDescripcion = document.createElement("td")
		cDescripcion.textContent = articulo.descripcion

		let cPrecio = document.createElement("td")
		cPrecio.textContent = articulo.precio

		let cUnidades = document.createElement("td")

		let cTotal = document.createElement("td")

		let cAcciones = document.createElement("td")
		//let divAcciones = document.createElement("div")
		cAcciones.className = "tdAcciones"
		let aumentarUds = document.createElement("button")
		aumentarUds.className = "buttonUds buttonAumentar"
		aumentarUds.textContent = "  +  "
		cAcciones.appendChild(aumentarUds)

		let disminuirUds = document.createElement("button")
		disminuirUds.className = "buttonUds buttonDisminuir"
		disminuirUds.textContent = "  -  "
		cAcciones.appendChild(disminuirUds)

		let borrarArticulo = document.createElement("button")
		borrarArticulo.className = "buttonUds buttonBorrar"
		borrarArticulo.textContent = "Borrar"
		cAcciones.appendChild(borrarArticulo)
		//cAcciones.textContent = divAcciones

		filaProducto.appendChild(cImagen)
		filaProducto.appendChild(cNombre)
		filaProducto.appendChild(cDescripcion)
		filaProducto.appendChild(cPrecio)
		filaProducto.appendChild(cUnidades)
		filaProducto.appendChild(cTotal)
		filaProducto.appendChild(cAcciones)

		tabla.getElementsByTagName("tbody")[0].appendChild(filaProducto)
	}
}
