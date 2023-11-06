criterios = ["Sin ordenar", "Ascendente por precio", "Descendente por precio"]

window.onload = () => {
	let miCarrito = new Carrito()
	mostrarArticulos(miCarrito)
	creaListaCriterios(miCarrito)
	verCarro(miCarrito)
}

function creaListaCriterios(miCarrito) {
	let criteriosOrdenacion = document.getElementById("criteriosOrdenacion")
	let listaArticulosOriginal = listaArticulos.slice()

	criterios.forEach(c => {
		let criterio = document.createElement("option")
		criterio.textContent = c
		criterio.id = c
		criteriosOrdenacion.appendChild(criterio)
	});

	criteriosOrdenacion.addEventListener("change", function () {
		let criterio = document.getElementById("criteriosOrdenacion").value

		if (criterio == "Ascendente por precio") {
			listaArticulos.sort((a, b) => a.precio - b.precio)
		}
		else if (criterio == "Descendente por precio") {
			listaArticulos.sort((a, b) => b.precio - a.precio)
		}
		else {
			listaArticulos = listaArticulosOriginal.slice()
		}

		mostrarArticulos(miCarrito)
	});
}

function mostrarArticulos(miCarrito) {
	let divArriculo = document.getElementById("contenedor")
	divArriculo.textContent = ""
	divArriculo.className = "row row-cols-1 row-cols-md-6 g-4"

	listaArticulos.forEach(a => {

		let col = document.createElement("div")
		col.className = "col"
		let card = document.createElement("div")
		card.className = "card"
		let imgArticulo = document.createElement("img")
		imgArticulo.className = "card-img-top"
		imgArticulo.src = `./assets/${a.codigo}.jpg`
		card.appendChild(imgArticulo)
		col.appendChild(card)

		let cardBody = document.createElement("div")
		cardBody.className = "card-body"
		let cardTitle = document.createElement("h5")
		cardTitle.className = "card-title"
		cardTitle.textContent = a.nombre

		let cardText = document.createElement("p")
		cardText.className = "card-text"
		cardText.textContent = a.descripcion

		let cardPrice = document.createElement("p")
		cardPrice.className = "card-text text-center"
		cardPrice.textContent = a.precio

		let btn = document.createElement("button")
		btn.className = "btn-success"
		btn.id = a.codigo
		btn.textContent = "Comprar"

		cardBody.appendChild(cardTitle)
		cardBody.appendChild(cardText)
		cardBody.appendChild(cardPrice)
		card.appendChild(cardBody)
		card.appendChild(btn)
		divArriculo.appendChild(col)

		btn.addEventListener("click", function () {
			ponArticuloEnCarrito(miCarrito, a.codigo)
		})
	});
}

function ponArticuloEnCarrito(miCarrito, codigo) {
	let articuloExistente = miCarrito.articulos.find(a => a.codigo == codigo)
	if(articuloExistente){
		miCarrito.aumentarUnidades(codigo)
	}
	else{
		let articulo = listaArticulos.find(a => a.codigo == codigo)
		miCarrito.anyadeArticulo(articulo)
	}
}

function verCarro(miCarrito) {
	document.getElementById("carritoImg").addEventListener("click", function () {
		let carritoDialog = document.getElementById("miDialogo")
		if (miCarrito.articulos.length === 0) {
			alert("El carrito está vacío")
		} else {
			carritoDialog.showModal()
		}
		document.getElementById("btnCierraDialog").addEventListener("click", function () {
			carritoDialog.close()
		})
		document.getElementById("btnEfectuaPedido").addEventListener("click", function () {
			console.log(miCarrito)
			carritoDialog.close()
		})
	});
}

function efectuaPedido() {

}

