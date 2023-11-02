class Carrito{
	constructor(id){
		this.articulos = []						
	}
						
	anyadeArticulo(articulo){		
		this.articulos.push(articulo)
		this.verCarrito(articulo)
		//console.log(this.articulos)
	}
	
	//codigoArticulo = this.articulos.forEach(a => a.codigo)
				
	borraArticulo(codigoArticulo){

	}
	
	modificaUnidades(codigo,n){		
	}	
			
	verCarrito(articulo){
		console.log(articulo)
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
