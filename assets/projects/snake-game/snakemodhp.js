const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");

const squareSize = 25;
const rows = 36;
const columns = 36;
const canvasCenterY = canvas.height / 2;
const chompAudio = new Audio("chomp.wav");
const dieAudio = new Audio("DIE.ogg");

chompAudio.load();
dieAudio.load();

let headColor;
let snake = [];
let horizontalStep = squareSize;
let verticalStep = 0;
let apple = { x: 0, y: 0 };
let appleColor = "red";
let snakeLength = 5;
let startTime = Date.now();
let animationId = null;
let gameStarted = false;
let difficultySelected = false;

snake = Array.from({ length: 5 }, (_, i) => ({
    x: (4 - i) * squareSize,
    y: canvasCenterY,
}));

function setGameSpeed(selectedDifficulty) {
    const difficultySpeeds = { easy: 200, medium: 130, hard: 70 };
    speed = difficultySpeeds[selectedDifficulty] || 130;
}

let touchStartX = 0;
let touchStartY = 0;
let touchEndX = 0;
let touchEndY = 0;

canvas.addEventListener("touchstart", (event) => {
    touchStartX = event.touches[0].clientX;
    touchStartY = event.touches[0].clientY;
});

canvas.addEventListener("touchend", (event) => {
    touchEndX = event.changedTouches[0].clientX;
    touchEndY = event.changedTouches[0].clientY;
    handleSwipe();
});

function handleSwipe() {
    const deltaX = touchEndX - touchStartX;
    const deltaY = touchEndY - touchStartY;
    const horizontalThreshold = 50;
    const verticalThreshold = 50;

    if (
        Math.abs(deltaX) > horizontalThreshold ||
        Math.abs(deltaY) > verticalThreshold
    ) {
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // Horizontal swipe
            if (deltaX > 0 && horizontalStep === 0) {
                verticalStep = 0;
                horizontalStep = squareSize;
            } else if (deltaX < 0 && horizontalStep === 0) {
                verticalStep = 0;
                horizontalStep = -squareSize;
            }
        } else {
            // Vertical swipe
            if (deltaY > 0 && verticalStep === 0) {
                verticalStep = squareSize;
                horizontalStep = 0;
            } else if (deltaY < 0 && verticalStep === 0) {
                verticalStep = -squareSize;
                horizontalStep = 0;
            }
        }
    }
}

const startButton = document.getElementById("startButton");
const difficultySelect = document.getElementById("difficulty");
const restartButton = document.getElementById("restartButton");
const scoreboardElement = document.getElementById("scoreboard");

startButton.addEventListener("click", () => {
    if (gameStarted || !difficultySelected) return;

    const selectedDifficulty = difficultySelect.value;
    setGameSpeed(selectedDifficulty);

    difficultySelect.disabled = true;
    resetGame();
    gameStarted = true;

    const difficultyContainer = document.getElementById("difficultyContainer");
    difficultyContainer.style.display = "none";
});

startButton.disabled = true;

difficultySelect.addEventListener("change", () => {
    difficultySelected = true;
    startButton.disabled = false;

    const difficultyContainer = document.getElementById("difficultyContainer");
    difficultyContainer.style.display = "none";
});

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

function resetHighScores(forceReset) {
    for (let i = 1; i <= 10; i++) {
        localStorage.setItem(`highScore${i}`, 0);
        localStorage.setItem(`highScoreName${i}`, "");
    }

    const currentScore = snakeLength - 5;
    if (forceReset || currentScore === 0) {
        drawScoreboard();
    } else {
        const playerName = "Player";
        saveHighScore(playerName, currentScore);
        drawScoreboard();
    }
}

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
    const formattedTime = `${minutes.toString().padStart(2, "0")}:${seconds
        .toString()
        .padStart(2, "0")}`;
    const currentScore = snakeLength - 5;
    const highScore = getHighScore();

    if (currentScore > highScore) {
        saveHighScore("Player", currentScore);
    }

    scoreboardElement.innerHTML = `
        <div style="background-color: #4CAF50; padding: 10px; border-radius: 5px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
            <div style="font-size: 24px; color: white; margin-bottom: 10px;">Score: ${
                snakeLength - 5
            }</div>
            <div style="font-size: 18px; color: white;">Time: ${formattedTime}</div>
            <div style="font-size: 18px; color: white;">High Score: ${highScore}</div>
        </div>
        <br>
    `;
}

function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawCanvas();
    drawSnake();
    drawScoreboard();
}

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
        chompAudio.volume = 0.15;
        chompAudio.play();
    } else if (snake.length > snakeLength) {
        snake.pop();
    }
    if (checkCollision()) return;
    draw();
}

let playerName = "";

function getPlayerName() {
    if (!playerName) {
        playerName = prompt("Masukkan nama pemain:");
        if (!playerName || playerName.trim() === "") {
            playerName = "Player";
        }
    }
    return playerName;
}

window.onload = function () {
    const playerName = getPlayerName();
    const playerNameElement = document.getElementById("playerName");
    playerNameElement.textContent = `Hi, ${playerName}!`;
};

function checkCollision() {
    const head = snake[0];
    const isColliding = snake.some(
        (segment, index) =>
            index !== 0 && segment.x === head.x && segment.y === head.y
    );
    if (isColliding) {
        clearInterval(animationId);
        dieAudio.volume = 0.15;
        dieAudio.play();
        enableRestartButton();

        const currentScore = snakeLength - 5;
        const gameOverText = `Game Over, ${playerName}!\nYour Score: ${currentScore}`;
        window.alert(gameOverText);
    }
    return isColliding;
}

function enableRestartButton() {
    restartButton.disabled = false;
}

function disableRestartButton() {
    restartButton.disabled = true;
}

function resetGame() {
    snake = Array.from({ length: 5 }, (_, i) => ({
        x: (4 - i) * squareSize,
        y: canvasCenterY,
    }));
    horizontalStep = squareSize;
    verticalStep = 0;
    apple = { x: 0, y: 0 };
    snakeLength = 5;
    startTime = Date.now();
    difficultySelect.disabled = false;
    disableRestartButton();
    placeApple();
    animate();
}

restartButton.addEventListener("click", () => {
    if (!restartButton.disabled) {
        resetGame();
        disableRestartButton();
    }
});

const resetHighScoresButton = document.getElementById("resetHighScoresButton");
resetHighScoresButton.addEventListener("click", () => {
    resetHighScores();
    drawScoreboard();
});

function animate() {
    if (difficultySelected) {
        draw();
        animationId = setInterval(move, speed);
        startButton.disabled = true;
    }
}

placeApple();
animate();
