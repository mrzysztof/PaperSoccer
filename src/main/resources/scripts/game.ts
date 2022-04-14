const canvas = document.getElementById("board") as HTMLCanvasElement;
const context = canvas.getContext('2d');

let redTurn: boolean;

function init(){
    const query = window.location.search;
    const params = new URLSearchParams(query);
    redTurn = true;    
}

function initGrid() {
    

}