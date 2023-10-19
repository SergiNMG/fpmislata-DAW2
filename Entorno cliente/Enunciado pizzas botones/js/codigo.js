let buttonsDiv = document.getElementById("botones")
let pizzaImg = document.getElementById("fotopizza")
let IngredientsDiv = document.getElementById("ingredientes")

pizzas.forEach((pizza, i) => {
    let pizzaButton = document.createElement("button")
    let pizzaId

    pizzaButton.innerHTML = pizza.nombre
    pizzaId = pizza.codigo
    buttonsDiv.appendChild(pizzaButton)
    
    //console.log(pizza)
    //console.log(pizza.codigo)
    //console.log(pizzaId) 

    pizzaButton.addEventListener("click", function () {
        let pizzaSelected = pizzas.find(p => p.codigo == pizzaId)

        let pizzaIdImg = document.createElement("img")
        pizzaIdImg.className = "imagencss"
        pizzaIdImg.src = `assets/${pizzaSelected.codigo}.jpg`
        console.log(pizzaSelected)
        console.log(pizzaIdImg.src)

        pizzaImg.append(pizzaIdImg)

        let pizzaToppings = pizzaSelected.map(p => p.ingredientes.ingrediente)
        console.log(pizzaToppings)
    });
});

