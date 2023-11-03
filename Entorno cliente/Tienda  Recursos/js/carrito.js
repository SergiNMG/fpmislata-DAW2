class Carrito {
	constructor(id) {
		this.articulos = []
		this.id = 3047
		this.unidades = new Map()
	}

	anyadeArticulo(articulo) {
		this.articulos.push(articulo)
		if(!this.unidades.has(articulo.codigo)){
			this.unidades.set(articulo.codigo, 1)
		}
		this.verCarrito(articulo)
		//console.log(this.articulos)
	}

	//codigoArticulo = this.articulos.forEach(a => a.codigo)

	/* modificaUnidades(articulo, unidades){

	}*/

	aumentarUnidades(articulo){
		let codigoArticulo = articulo.codigo
		if(this.unidades.has(codigoArticulo)){
			this.unidades.set(codigoArticulo, this.unidades.get(codigoArticulo) + 1)
		}
		console.log(this.unidades)
		this.verCarrito(articulo)
	}

	disminuirUnidades(articulo){
		let codigoArticulo = articulo.codigo
		if(this.unidades.has(codigoArticulo)){
			if(this.unidades.get(codigoArticulo) > 1){
				this.unidades.set(codigoArticulo, this.unidades - 1)
			}
		}
		console.log(this.unidades)
		this.verCarrito(articulo)
	}

	borrarArticulo(codigoArticulo){
		this.articulos = this.articulos.filter(a => a.codigo == codigoArticulo)
		this.unidades.delete(codigoArticulo)
		this.verCarrito(articulo)
	}

	mostrarPrecio(articulo){


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
		
		aumentarUds.addEventListener("click", () => {
			this.aumentarUnidades(articulo)
		});

		let disminuirUds = document.createElement("button")
		disminuirUds.className = "buttonUds buttonDisminuir"
		disminuirUds.textContent = "  -  "
		cAcciones.appendChild(disminuirUds)
		disminuirUds.addEventListener("click", () => {
			this.disminuirUnidades(articulo)
		});

		let borrarArticulo = document.createElement("button")
		borrarArticulo.className = "buttonUds buttonBorrar"
		borrarArticulo.textContent = "Borrar"
		cAcciones.appendChild(borrarArticulo)
		borrarArticulo.addEventListener("click", () => {
			this.borrarArticulo(articulo.codigo)
		});
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
