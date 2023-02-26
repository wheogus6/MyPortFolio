
//캔버스 세팅
let canvas;
let ctx;
canvas = document.createElement("canvas");
ctx = canvas.getContext("2d");
canvas.width=600;
canvas.height=600;
document.body.appendChild(canvas);   //html body 태그에 캔버스 넣기



let backgroundImage, spaceshipImage, bulletImage, alienImage, gameoverImage;

let gameover = false // true 이면 게임 끝, false 이면 게임 계속

let score = 0;

//우주선 좌표
let spaceshipX = canvas.width/2-30                   // 캔버스 넚이 나누기 2 하고 우주선의 반픽셀(30px) 뺀값    
let spaceshipY = canvas.height-60                    // 캔버스 높이에서 우주선픽셀(60px) 뺀값



let bulletlist = [] //총알 저장 리스트

function bullet(){
    this.x = 0;
    this.y = 0;
    this.init = function(){
        this.x = spaceshipX+19;
        this.y = spaceshipY;

        this.alive = true; // true면 살아있는 총알 false 이면 죽은 총알

        bulletlist.push(this)
    };
    this.update = function(){
        this.y -=9;      
    }
    this.checkHit = function(){
        for(let i =0; i<enemylist.length; i++){
            // 총알y <= 적군y and  총알x >= 적군x and 총알x <= 적군x+40px(이미지 넓이) -> 닿았다 - 총알 죽게됨 적군 우주선이 없어짐 -> 점수 획득 ****
            if(this.y <= enemylist[i].y && this.x >= enemylist[i].x && this.x <= enemylist[i].x+40){
                // 총알 없어짐 적군 우주선 없어짐, 점수 획득
                score++; 
                this.alive = false; // 죽은 총알
                enemylist.splice(i,1);
            }
        }
    }
    this.endBullet = function (){
        if (this.y <= canvas.height - 595){
            this.alive = false;
        }
    }

    }


function generateRandom(min, max){
    let randomNum = Math.floor(Math.random()*(max-min+1))+min
    return randomNum
}

let enemylist=[]

function enemy() {
    this.x = 0;
    this.y = 0;
    this.init =function(){
        this.y = 0;
        this.x = generateRandom(0,canvas.width-40)
        enemylist.push(this)
    };
    this.update = function(){    
        this.y += 4;   //적군의 속도 조절
    if(score >= 10){
        this.y += 4.1;
    }
    if(score >= 20){
        this.y += 4.2;
    }
    if(score >= 30){
        this.y += 4.3;
    }
    if(this.y >= canvas.height - 40){
        gameover = true;
        console.log("gameover"); 
    }

}
}

function loadimage(){
    backgroundImage = new Image();
    // backgroundImage.src="main/webapp/resources/img/background.jpg";
    backgroundImage.src="img/background.jpg";

    spaceshipImage = new Image();
    spaceshipImage.src="img/spaceship.png";

    alienImage = new Image();
    alienImage.src="img/alien.png";

    bulletImage = new Image();
    bulletImage.src="img/bullet.png";

    gameoverImage = new Image();
    gameoverImage.src="img/gameover.png";
}

let keysDown={};
function setupKeyboardListner(){
    document.addEventListener("keydown", function(event){
       keysDown[event.key] = true;                           //누른 키 값 let keysDown={}에 저장
       console.log(keysDown);
    });
    document.addEventListener("keyup", function(event){
        delete keysDown[event.key];                          // 눌렀다 뗀 키 값 let keysDown={}에서 삭제
        
        if(event.key == ' '){
            createbullet() // 총알 생성
    }
        
        })     
    }                                                 


function createbullet(){
    console.log('총알 생성');
    let b = new bullet(); // 총알 하나 생성
    b.init();
}
function createEnemy(){
    const interval = setInterval(function(){
        let e = new enemy()
        e.init()
    },1000)
}

function update(){
    if('ArrowRight' in keysDown){                        // 오른쪽 키 값이 let keysDown={}에 저장되있으면 우주선 x좌표를 +5 증가
        spaceshipX +=6.5;
    }
    if('ArrowLeft' in keysDown){                         // 왼쪽 키 값이 let keysDown={}에 저장되있으면 우주선 x좌표를 -5 감소
        spaceshipX -=6.5;
    }
    if(spaceshipX <= 0){                                 // 우주선 x 좌표가 0보다 작거나 같다면 우주선 x좌표는 0이다
        spaceshipX = 0;
    }
    if(spaceshipX >= canvas.width-60){                   // 우주선 x 좌표가 캔버스의 넓이 -60(픽셀크기) 보다 크거나 같다면 우주선 x좌표는 캔버스의 넓이 -60(픽셀크기)이다
        spaceshipX = canvas.width-60;
    }
    //총알의 y좌표 업데이트하는 함수
    for(let i=0; i<bulletlist.length;i++){
        if(bulletlist[i].alive){
        bulletlist[i].update()
        bulletlist[i].checkHit();
        bulletlist[i].endBullet();
    }
    }
    for(let i=0; i<enemylist.length; i++){
        enemylist[i].update()
    }
}


function render(){
    ctx.drawImage(backgroundImage, 0, 0, canvas.width, canvas.height);
    ctx.drawImage(spaceshipImage, spaceshipX, spaceshipY);
    ctx.fillText(`Score:${score}`, 20, 20);
    ctx.fillStyle = "white";
    ctx.font = "20px Arial";
    for(let i =0; i<bulletlist.length;i++){
        if(bulletlist[i].alive){
        ctx.drawImage(bulletImage, bulletlist[i].x, bulletlist[i].y);
    }
    }
    for(let i =0; i<enemylist.length;i++){
        ctx.drawImage(alienImage, enemylist[i].x, enemylist[i].y);
    }
}  

function main(){
    if(!gameover){
    update();
    render();
    requestAnimationFrame(main);}
    else{
        ctx.drawImage(gameoverImage, 115,80,360,300);
    }
}

loadimage();
setupKeyboardListner();
createEnemy();
main();

// 방향키를 누르면 우주선의 xy좌표가 바뀌고 다시 render 그려준다.\

//총알 만들기
// 1. 스페이스바를 누르면 총알 발사
// 2. 총알이 발사 = 총알의 y값이-- , 총알의 x값은? 스페이스를 누른 순간의 우주선의 x좌표
// 3. 발사된 총알들은 총알 배열에 저장을 한다.
// 4. 모든 총알들은 x,y 좌표값이 있어야 한다.
// 5. 총알 배열을 가지고 render 그려준다.


//적군 만들기
// x,y, init, update
// 적군은 위치가 랜덤하다.
// 적군은 밑으로 내려온다 = y좌표가 증가한다
// 1초마다 하나씩 적군이 나온다
// 적군의 우주선이 바닥에 닿으면 게임 오버
// 적군과 총알이 만나면 우주선이 사라진다. (1점 획득)

// 총알y <= 적군y and  총알x >= 적군x and 총알x <= 적군x+40px(이미지 넓이) -> 닿았다 - 총알 죽게됨 적군 우주선이 없어짐 -> 점수 획득 ****