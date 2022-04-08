
fetch("https://carsnew.azurewebsites.net/api/cars")
    .then(res => res.json())
    .then(cars => {
        cars.forEach(car => {
            const myCars = document.createElement("h3")
            console.log(car.brand)
            myCars.innerText = `Brand: ${car.brand} Model: ${car.model} price pr day: ${car.pricePrDay}`
            document.getElementById("cars").appendChild(myCars)
    })
    })

