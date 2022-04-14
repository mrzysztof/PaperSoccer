const modal = document.getElementById("game-params-modal");
const timeModeButton = document.getElementById("time-mode-button") as HTMLButtonElement;
const bofModeButton = document.getElementById("bof-mode-button") as HTMLButtonElement;
const sizeButtons = 
    Array.from(document.getElementsByClassName("size-button")) as HTMLButtonElement[];
const durationButtons =
    Array.from(document.getElementsByClassName("time-adj-button")) as HTMLButtonElement[];
const nOfGamesButtons =
    Array.from(document.getElementsByClassName("bof-adj-button")) as HTMLButtonElement[];

const modeInput = document.getElementById("mode-input") as HTMLInputElement;
const gameLenInput = document.getElementById("game-len-input") as HTMLInputElement;
const sizeInput = document.getElementById("size-input") as HTMLInputElement;

const defColor = "#FFFFFF";
const selectedColor = "#DDDDDD";

const durationStep = 30;
const nOfGamesStep = 2;

let gameDuration = 300; //in seconds
let nOfGames = 1;

window.onclick = function (event) {
    if (event.target == modal) {
        if (modal) modal.style.display = "none";
    }
}

document.getElementById("offline")?.addEventListener("click", () => {
    if (modal) modal.style.display = "block";    
});

document.getElementById("time-mode-button")!
        .addEventListener("click", () => {
            bofModeButton.style.backgroundColor = defColor;
            timeModeButton.style.backgroundColor = selectedColor;
            modeInput.value = "time";
            gameLenInput.value = gameDuration.toString();
            document.getElementById("len-adjustment-bof")!.style.display = "none";
            document.getElementById("len-adjustment-time")!.style.display = "flex";
        }
    )

document.getElementById("bof-mode-button")!
        .addEventListener("click", () => {
            timeModeButton.style.backgroundColor = defColor;
            bofModeButton.style.backgroundColor = selectedColor;
            modeInput.value = "bof";
            gameLenInput.value = nOfGames.toString();
            document.getElementById("len-adjustment-bof")!.style.display = "flex";
            document.getElementById("len-adjustment-time")!.style.display = "none";
        }
    )

sizeButtons.forEach(
    btn => btn.addEventListener("click", () => {
        sizeButtons.forEach(b => b.style.backgroundColor = defColor);
        btn.style.backgroundColor = selectedColor;
        sizeInput.value = btn.value;
    })
);

durationButtons.forEach(
    btn => btn.addEventListener("click", () => {
        if(btn.value === "-"){
            if(gameDuration > durationStep) gameDuration -= durationStep;
        }
        else gameDuration += durationStep;
        (document.getElementById("game-len-ui-time") as HTMLInputElement)
                                                   .value = toTimeString(gameDuration);
        gameLenInput.value = gameDuration.toString();
    })
)

nOfGamesButtons.forEach(
    btn => btn.addEventListener("click", () => {
        if(btn.value === "-"){
            if(nOfGames > nOfGamesStep) nOfGames -= nOfGamesStep;
        }
        else nOfGames += nOfGamesStep;
        (document.getElementById("game-len-ui-bof") as HTMLInputElement)
                                                   .value = nOfGames.toString();
        gameLenInput.value = nOfGames.toString();
    })
)

function toTimeString(time: number): string{
    const minutes = (Math.floor(time/60)).toString();
    const seconds = time % 60 > 10 ? time % 60 : "0" + time % 60;
    return minutes + ":" + seconds;
}

function initModal(){
    document.getElementById("time-mode-button")?.click();
    document.getElementById("def-size-button")?.click();
}

//-------------------------------------------------------
initModal();