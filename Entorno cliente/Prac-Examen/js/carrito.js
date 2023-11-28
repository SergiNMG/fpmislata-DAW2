class Carrito{
	constructor(){	
		this.articulos = []				
	}
						
	anyadeArticulo(articulo){		
		if (this.articulos.find(a => a.codigo == articulo.codigo)){
			articulo.unidades += 1
		}
		else{
			articulo.unidades = 1
			this.articulos.push(articulo)
		}
		this.verCarrito()
	}			
				
	borraArticulo(codigo){	
		this.articulos = this.articulos.filter(a => a.codigo != codigo)
		this.verCarrito()
	}
	
	modificaUnidades(codigo,n){
		let articulo = this.articulos.find(a => a.codigo == codigo)
		if (n >= 1){
			articulo.unidades += 1
		}
		else {
			articulo.unidades > 1 ? articulo.unidades -= 1 : this.borraArticulo(codigo)
		}		
		this.verCarrito()
	}	
			
	verCarrito(){
		let tabla = document.getElementById("tablaCarrito")
		let totalCarrito = document.getElementById("total")
		let sumaTotal = 0
		tabla.getElementsByTagName("tbody")[0].innerHTML = ""

		this.articulos.forEach( a => {
			let filaProducto = document.createElement("tr")

			let cImagen = document.createElement("td")
			let img = document.createElement("img")
			img.className = "imgCarrito"
			img.src = `./assets/${a.codigo}.jpg`
			cImagen.appendChild(img)

			let cNombre = document.createElement("td")
			let nombre = document.createTextNode(a.nombre)
			cNombre.appendChild(nombre)

			let cDescripcion = document.createElement("td")
			let descripcion = document.createTextNode(a.descripcion)
			cDescripcion.appendChild(descripcion)

			let cPrecio = document.createElement("td")
			let precio = document.createTextNode(a.precio)
			cPrecio.appendChild(precio)

			let cUnidades = document.createElement("td")
			let unidades = document.createTextNode(a.unidades)
			cUnidades.appendChild(unidades)

			let cTotal = document.createElement("td")
			let totalArticulo = document.createTextNode(a.unidades * a.precio)
			cTotal.appendChild(totalArticulo)

			sumaTotal += (a.unidades * a.precio)
			totalCarrito.textContent = sumaTotal

			let cAcciones = document.createElement("td")
			cAcciones.className = "tdAcciones"

			let aumentarUds = document.createElement("button")
			aumentarUds.className = "buttonUds buttonAumentar"
			aumentarUds.textContent = "  +  "
			aumentarUds.addEventListener("click", () => this.modificaUnidades(a.codigo, +1))
			cAcciones.appendChild(aumentarUds)

			let disminuirUds = document.createElement("button")
			disminuirUds.className = "buttonUds buttonDisminuir"
			disminuirUds.textContent = "  -  "
			disminuirUds.addEventListener("click", () => this.modificaUnidades(a.codigo, -1))
			cAcciones.appendChild(disminuirUds)
			
			let borrarArticulo = document.createElement("button")
			borrarArticulo.className = "buttonUds buttonBorrar"
			borrarArticulo.textContent = "Borrar"
			borrarArticulo.addEventListener("click", () => this.borraArticulo(a.codigo))
			cAcciones.appendChild(borrarArticulo)

			filaProducto.appendChild(cImagen)
			filaProducto.appendChild(cNombre)
			filaProducto.appendChild(cDescripcion)
			filaProducto.appendChild(cPrecio)
			filaProducto.appendChild(cUnidades)
			filaProducto.appendChild(cTotal)
			filaProducto.appendChild(cAcciones)

			tabla.getElementsByTagName("tbody")[0].appendChild(filaProducto)
		});

		totalCarrito.textContent = this.articulos.length == 0 ? 0 : sumaTotal
	}			
}
