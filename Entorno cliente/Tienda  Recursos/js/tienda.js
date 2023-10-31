criterios = ["Sin ordenar", "Ascendente por precio", "Descendente por precio"]

function creaListaCriterios() {
	let criteriosOrdenacion = document.getElementById("criteriosOrdenacion")

	criterios.forEach(c => {
		let criterio = document.createElement("option")
		criterio.textContent = c
		criterio.value = c
		criteriosOrdenacion.appendChild(criterio)
	});

	criteriosOrdenacion.addEventListener("change", pintaArticulos);
}

window.onload = () => {
	creaListaCriterios()
	let divArriculo = document.getElementById("contenedor")
	divArriculo.className = "row row-cols-1 row-cols-md-6 g-4"

	let listaProductos = listaArticulos.slice()

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

		return listaProductos

	});
}

function pintaArticulos(listaProductos) {
	listaProductos.sort((a, b) => b.precio - a.precio)

}


function ponArticuloEnCarrito() {

}


function verCarro() {

}

function efectuaPedido() {

}

