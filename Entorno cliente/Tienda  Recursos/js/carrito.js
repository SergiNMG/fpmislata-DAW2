class Carrito {
	constructor(id) {
		this.id = id
		this.articulos = []
		this.unidades = new Map()
	}

	anyadeArticulo(articulo) {
		this.articulos.push(articulo)
		if (!this.unidades.has(articulo.codigo)) {
			this.unidades.set(articulo.codigo, 1)
		}
		this.verCarrito()
		//console.log(this.articulos)
	}

	//this.articulos.forEach(a => console.log(a.codigo))

	modificarUnidades(codigoArticulo, cantidad) {
		/*if (this.unidades.has(codigoArticulo)){
			let unidadesActuales = this.unidades.get(codigoArticulo)
		}*/
		if (cantidad > 0) {
			this.unidades.set(codigoArticulo, this.unidades.get(codigoArticulo) + cantidad)
		}
		else if (this.unidades.get(codigoArticulo) + cantidad > 0) {
			this.unidades.set(codigoArticulo, this.unidades.get(codigoArticulo) + cantidad)
		}
		else {
			this.borrarArticulo(codigoArticulo)
		}
		console.log(this.unidades)
		this.verCarrito()
	}

	//Modificación de unidades con varios métodos: 

	/*aumentarUnidades(codigoArticulo){
		if(this.unidades.has(codigoArticulo)){
			this.unidades.set(codigoArticulo, this.unidades.get(codigoArticulo) + 1)
		}
		console.log(this.unidades)
		this.verCarrito()
	}

	disminuirUnidades(codigoArticulo){
		if(this.unidades.has(codigoArticulo)){
			if(this.unidades.get(codigoArticulo) > 1){
				this.unidades.set(codigoArticulo, this.unidades.get(codigoArticulo) - 1)
			}else{
				this.borrarArticulo(codigoArticulo)
			}
		}
		console.log(this.unidades)
		this.verCarrito()
	}*/

	borrarArticulo(codigoArticulo) {
		console.log("Deleted: " + codigoArticulo)
		this.articulos = this.articulos.filter(a => a.codigo != codigoArticulo)
		this.unidades.delete(codigoArticulo)
		this.verCarrito()
	}

	verCarrito() {
		let tabla = document.getElementById("tablaCarrito")
		let totalCarrito = document.getElementById("total")
		let idCarrito = document.getElementById("idPedido")
		idCarrito.textContent = this.id
		let sumaTotal = 0
		tabla.getElementsByTagName("tbody")[0].innerHTML = ""

		this.articulos.forEach(articulo => {
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
			cUnidades.textContent = this.unidades.get(articulo.codigo)

			let cTotal = document.createElement("td")
			let precioTotal = this.unidades.get(articulo.codigo) * articulo.precio
			cTotal.textContent = precioTotal
			sumaTotal += precioTotal
			totalCarrito.textContent = sumaTotal

			let cAcciones = document.createElement("td")
			cAcciones.className = "tdAcciones"

			let aumentarUds = document.createElement("button")
			aumentarUds.className = "buttonUds buttonAumentar"
			aumentarUds.textContent = "  +  "
			cAcciones.appendChild(aumentarUds)
			aumentarUds.addEventListener("click", () => {
				//this.aumentarUnidades(articulo.codigo)
				this.modificarUnidades(articulo.codigo, 1)
			});

			let disminuirUds = document.createElement("button")
			disminuirUds.className = "buttonUds buttonDisminuir"
			disminuirUds.textContent = "  -  "
			cAcciones.appendChild(disminuirUds)
			disminuirUds.addEventListener("click", () => {
				//this.disminuirUnidades(articulo.codigo)
				this.modificarUnidades(articulo.codigo, -1)
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
			//totalCarrito.textContent = sumaTotal
		});
		
		/*
		if (this.articulos.length == 0){
			totalCarrito.textContent = 0
		}else{
			totalCarrito.textContent == sumaTotal
		}*/
		
		totalCarrito.textContent = this.articulos.length == 0 ? 0 : sumaTotal
	}
}
