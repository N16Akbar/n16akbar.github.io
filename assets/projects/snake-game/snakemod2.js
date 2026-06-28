const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");
const squareSize = 25;
const rows = 36;
const columns = 36;
const speed = 130;

let headColor;
let snake = [
    { x: 4 * squareSize, y: 0 },
    { x: 3 * squareSize, y: 0 },
    { x: 2 * squareSize, y: 0 },
    { x: squareSize, y: 0 },
    { x: 0, y: 0 },
];
let horizontalStep = squareSize;
let verticalStep = 0;
let apple = { x: 0, y: 0 };
let appleColor = "red";
let snakeLength = 5;
let startTime = Date.now();

const canvasCenterY = canvas.height / 2;
snake.forEach((segment) => {
    segment.y = canvasCenterY;
});

function resetHighScores() {
    for (let i = 1; i <= 10; i++) {
        localStorage.removeItem(`highScore${i}`);
        localStorage.removeItem(`highScoreName${i}`);
    }
}

const resetHighScoresButton = document.getElementById("resetHighScoresButton");
resetHighScoresButton.addEventListener("click", () => {
    resetHighScores();
    displayHighScores(); // Tampilkan daftar highscore yang sudah di-reset
});

function getRandomColor() {
    var letters = "0123456789ABCDEF";
    let color = "#";
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function drawApple() {
    ctx.fillStyle = appleColor;
    ctx.fillRect(apple.x, apple.y, squareSize, squareSize);
}

function drawCanvas() {
    for (let row = 0; row < rows; row++) {
        for (let col = 0; col < columns; col++) {
            const x = col * squareSize;
            const y = row * squareSize;
            const isEvenSquare = (row + col) % 2 === 0;
            ctx.fillStyle = isEvenSquare ? "#cccccc" : "#737373";
            ctx.fillRect(x, y, squareSize, squareSize);
        }
    }
    drawApple();
}

function randomPosition() {
    return Math.floor(Math.random() * (columns - 1)) * squareSize;
}

function placeApple() {
    let validPosition = false;
    while (!validPosition) {
        apple.x = randomPosition();
        apple.y = randomPosition();
        if (
            apple.x >= 0 &&
            apple.x < canvas.width &&
            apple.y >= 0 &&
            apple.y < canvas.height
        )
            validPosition = true;
    }
}

function getHighScore() {
    let highestScore = 0;

    for (let i = 1; i <= 10; i++) {
        const score = localStorage.getItem(`highScore${i}`);
        if (score) {
            const parsedScore = parseInt(score);
            if (parsedScore > highestScore) {
                highestScore = parsedScore;
            }
        } else {
            break;
        }
    }

    return highestScore;
}

function saveHighScore(name, score) {
    const highScores = [];
    const highScoreNames = [];

    for (let i = 1; i <= 10; i++) {
        const scoreValue = localStorage.getItem(`highScore${i}`);
        const nameValue = localStorage.getItem(`highScoreName${i}`);
        if (scoreValue && nameValue) {
            highScores.push(parseInt(scoreValue));
            highScoreNames.push(nameValue);
        } else {
            break;
        }
    }

    highScores.push(score);
    highScoreNames.push(name);

    const scoresCount = highScores.length;
    for (let i = 1; i <= scoresCount; i++) {
        localStorage.setItem(`highScore${i}`, highScores[scoresCount - i]);
        localStorage.setItem(
            `highScoreName${i}`,
            highScoreNames[scoresCount - i]
        );
    }
}

function formatHighScore(name, score, rank) {
    return `#${rank}: ${name} (${score})`;
}

function displayHighScores() {
    const highScoreList = document.getElementById("highScoreList");
    highScoreList.innerHTML = "";

    for (let i = 1; i <= 10; i++) {
        const score = localStorage.getItem(`highScore${i}`);
        const name = localStorage.getItem(`highScoreName${i}`);
        if (score && name) {
            const listItem = document.createElement("li");
            listItem.textContent = formatHighScore(name, score, i);
            highScoreList.appendChild(listItem);
        } else {
            break;
        }
    }
}

function enableRestartButton() {
    restartButton.disabled = false;
}

function disableRestartButton() {
    restartButton.disabled = true;
}

function showInputNamePrompt(score) {
    const playerName = prompt("Game Over! Enter your name:");
    if (playerName) {
        saveHighScore(playerName, score);
        displayHighScores();
    }
}

function checkCollision() {
    const head = snake[0];
    const isColliding = snake.some(
        (segment, index) =>
            index !== 0 && segment.x === head.x && segment.y === head.y
    );
    if (isColliding) {
        clearInterval(animationId);
        ctx.fillStyle = "rgba(0, 0, 0, 0.7)";
        ctx.fillRect(0, 0, canvas.width, canvas.height);
        ctx.font = "40px Arial";
        ctx.textAlign = "center";
        ctx.fillStyle = "white";
        ctx.fillText("Game Over!", canvas.width / 2, canvas.height / 2);
        const dieAudio = document.getElementById("die");
        dieAudio.volume = 0.15;
        dieAudio.play();
        enableRestartButton();
        showInputNamePrompt(snakeLength - 5);
    }
    return isColliding;
}

function resetGame() {
    snake = [
        { x: 4 * squareSize, y: 0 },
        { x: 3 * squareSize, y: 0 },
        { x: 2 * squareSize, y: 0 },
        { x: squareSize, y: 0 },
        { x: 0, y: 0 },
    ];
    horizontalStep = squareSize;
    verticalStep = 0;
    apple = { x: 0, y: 0 };
    snakeLength = 5;
    startTime = Date.now();
    disableRestartButton();
    placeApple();
    animate();
}

const restartButton = document.getElementById("restartButton");
restartButton.addEventListener("click", () => {
    if (!restartButton.disabled) {
        resetGame();
        disableRestartButton();
    }
});

function move() {
    let newHeadX = snake[0].x + horizontalStep;
    let newHeadY = snake[0].y + verticalStep;
    if (newHeadX >= canvas.width) newHeadX = 0;
    else if (newHeadX < 0) newHeadX = canvas.width - squareSize;
    if (newHeadY >= canvas.height) newHeadY = 0;
    else if (newHeadY < 0) newHeadY = canvas.height - squareSize;
    const head = { x: newHeadX, y: newHeadY };
    snake.unshift(head);
    if (head.x === apple.x && head.y === apple.y) {
        placeApple();
        snakeLength++;
        const eatAudio = document.getElementById("chomp");
        eatAudio.volume = 0.15;
        eatAudio.play();
    } else if (snake.length > snakeLength) snake.pop();
    if (checkCollision()) {
        return;
    }
    draw();
}

document.addEventListener("keydown", (event) => {
    const keyPressed = event.key;
    if (keyPressed === "ArrowUp" && verticalStep === 0) {
        verticalStep = -squareSize;
        horizontalStep = 0;
    } else if (keyPressed === "ArrowDown" && verticalStep === 0) {
        verticalStep = squareSize;
        horizontalStep = 0;
    } else if (keyPressed === "ArrowLeft" && horizontalStep === 0) {
        verticalStep = 0;
        horizontalStep = -squareSize;
    } else if (keyPressed === "ArrowRight" && horizontalStep === 0) {
        verticalStep = 0;
        horizontalStep = squareSize;
    }
});

function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawCanvas();
    drawSnake();
    drawScoreboard();
}

function drawSnake() {
    headColor = getRandomColor();
    snake.forEach((movement, index) => {
        const fillStyle = index === 0 ? headColor : "#d4ff00";
        ctx.fillStyle = fillStyle;
        ctx.fillRect(movement.x, movement.y, squareSize, squareSize);
    });
}

function drawScoreboard() {
    const elapsedTimeInSeconds = Math.floor((Date.now() - startTime) / 1000);
    const minutes = Math.floor(elapsedTimeInSeconds / 60);
    const seconds = elapsedTimeInSeconds % 60;
    const scoreboardElement = document.getElementById("scoreboard");
    const formattedTime = `${minutes.toString().padStart(2, "0")}:${seconds
        .toString()
        .padStart(2, "0")}`;
    const currentScore = snakeLength - 5;
    const highScore = getHighScore();

    if (currentScore > highScore) {
        saveHighScore("Player", currentScore);
    }

    scoreboardElement.innerText = `Score: ${
        snakeLength - 5
    } | Time: ${formattedTime} | High Score: ${getHighScore()}`;
}

function animate() {
    draw();
    animationId = setInterval(move, speed);
}

placeApple();
animate();
displayHighScores();
