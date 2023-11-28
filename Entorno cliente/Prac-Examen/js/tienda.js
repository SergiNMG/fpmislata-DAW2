criterios = ["Sin ordenar", "Ascendente por precio", "Descendente por precio"]
let miCarrito 

window.onload = () => {
	miCarrito = new Carrito;
	creaListaCriterios()
	pintaArticulos()
	let imgCarrito = document.getElementById("imgCarrito")
	imgCarrito.addEventListener("click", function(){
		verCarro()
	});
}

function creaListaCriterios() {
	let listaCriterios = document.getElementById("criteriosOrdenacion")
	listaArticulosOriginal = listaArticulos.slice()

	criterios.forEach(c => {
		let criterio = document.createElement("option")
		criterio.textContent = c
		criterio.id = c
		listaCriterios.appendChild(criterio)
	});

	listaCriterios.addEventListener("change", function () {
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
		pintaArticulos()
	})
}


function pintaArticulos() {
	let divArticulos = document.getElementById("contenedor")
	divArticulos.textContent = ""
	let infoArticulo = ""

	listaArticulos.forEach(a => {
		infoArticulo +=
		`<div class = "col">
			<div class = "card">
			<img width=100px src="./assets/${a.codigo}.jpg" class = "card-img-top"/>
				<div class="card-body">
					<h5 class="card-title">${a.nombre}</h5>
					<p class = "card-text">${a.descripcion}</p>
					<b>
					<p class = "card-text text-center">${a.precio}</p>
					</b>
				</div>
			<button id="${a.codigo}" class="btn-success">comprar</button>
			</div>
		</div>`
	})
	divArticulos.innerHTML += infoArticulo

	let buttonArticulo = document.getElementsByClassName("btn-success")
	
	Array.from(buttonArticulo).forEach( b => {
		b.addEventListener("click", function(){
			//console.log(b.id)
			ponArticuloEnCarrito(b.id)
		})
	});

}


function ponArticuloEnCarrito(id){
	let articulo = listaArticulos.find(a => a.codigo == id)
	console.log(articulo)
	miCarrito.anyadeArticulo(articulo)
}


function verCarro() {
	let carritoDialog = document.getElementById("miDialogo")
	if(miCarrito && miCarrito.articulos && miCarrito.articulos.length > 0){
		carritoDialog.showModal()
	}
	else{
		alert("Carrito vacio")
	}

	let btnCloseDialog = document.getElementById("btnCierraDialog")
	btnCloseDialog.addEventListener("click", function(){
		carritoDialog.close()
	});

	let btnSendOrder = document.getElementById("btnEfectuaPedido")
	btnSendOrder.addEventListener("click", function(){
		efectuaPedido()
	});
}

function efectuaPedido() {
	console.log(JSON.stringify(miCarrito))
}




