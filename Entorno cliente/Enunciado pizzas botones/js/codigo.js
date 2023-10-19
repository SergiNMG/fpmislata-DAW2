let buttonSection = document.getElementById("botones")
let imgPizza = document.getElementById("fotopizza")
let topingSection = document.getElementById("ingredientes")
let priceSection = document.getElementById("importe")

pizzas.forEach((pizza, i) => {

    let pizzaButton = document.createElement("button")
    let pizzaId = pizza.codigo

    pizzaButton.innerHTML = pizza.nombre
    buttonSection.appendChild(pizzaButton)

    pizzaButton.addEventListener("click", function(){
        topingSection.textContent = null
        priceSection.textContent = null
        //let pizzaSelected = pizzas.find(p => p.codigo == pizzaId)
        let pizzaSelectedImg = document.createElement("img")
        pizzaSelectedImg.className = "imagencss"
        pizzaSelectedImg.src = `assets/${pizza.codigo}.jpg`

        imgPizza.appendChild(pizzaSelectedImg)

        let ingredients = pizza.ingredientes
        console.log(ingredients)
        let pizzaTopping = ingredients.map(p => p.ingrediente)
        //console.log(pizzaTopping)

        let pizzaToppings = document.createElement("p")
        pizzaToppings.textContent = pizzaTopping.join(", ")
        topingSection.appendChild(pizzaToppings)

        let prices = ingredients.map(p => parseFloat(p.precio))
        console.log(prices)
        let pricePizzaSelected = prices.reduce((a, b) => {
            return a + b; 
        },5);
        
        let pizzaPrice = document.createElement("p")
        pizzaPrice.textContent = pricePizzaSelected
        priceSection.appendChild(pizzaPrice)
    });
});
