//fetch("https://www.narutodb.xyz/api/character?page=1&limit=400")
fetch("https://www.narutodb.xyz/api/clan?page=1&limit=58")
    .then(response => response.json())
    .then(data => {
        let allButtons = document.querySelector(".create-buttons")
        let allClansSection = document.querySelector(".show-clans")

        let allClansButton = document.createElement("button")
        allClansButton.innerHTML = "Show all Clans"
        allButtons.appendChild(allClansButton)

        allClansButton.addEventListener("click", function () {
            data.clans.forEach(clan => {
                let clanSection = document.createElement("div")
                clanSection.className = "clanSection"

                let clanName = document.createElement("h1")
                clanName.innerHTML = clan.name
                clanSection.appendChild(clanName)

                clan.characters.forEach(character => {
                    let characterImg = document.createElement("img")
                    characterImg.className = "c-character__image"
                    characterImg.src = character.images

                    let characterName = document.createElement("p")
                    characterName.innerHTML = character.name

                    clanSection.appendChild(characterImg)
                    clanSection.appendChild(characterName)
                });

                allClansSection.appendChild(clanSection)
            });
        });
    })
    .catch(error => {
        console.error("Error: ", error)
    });
