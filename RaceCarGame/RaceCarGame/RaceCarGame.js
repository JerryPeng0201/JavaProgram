
/*
* Introduction:
* This is our team final project.
* This final project is a race car game. The user need to use keyboard to
* control the race car to move forward, move backward, left or right. It has
* a initial page. When the user press "p" button, the game will start. When the
* car hits the obstruction on the road, the car will lose health point and the
* obstruction will be crashed away.
*/

//Let's begin!

//First, we need to declare some variables
// in the animation code
var scene, renderer;  // all threejs programs need these
var camera, avatarCam, edgeCam;  // we have two cameras in the main scene
var avatar; //This is the race car we control to play
var startingLine;

var obstruction1;

var controls =
     {fwd:false, bwd:false, left:false, right:false,
      speed:10, reset:false, go:false, back:false, goLeft:false, goRight:false,
      camera:camera}

var gameState =
     {score:0, health:3, scene:'main', camera:'none' }



// Here is the main game control
init(); //
initControls();
animate();  // start the animation loop!


/**
  To initialize the scene, we initialize each of its components
*/
function init(){
    initPhysijs();
    scene = initScene();
    initRenderer();
    createMainScene();
}

//------------------------------------------------------------------------------

// This part creats some very basic elements of the game
// When you add something in this program, remeber to add it in this main method

function createMainScene(){
  // setup lighting
  var light1 = createPointLight();
  light1.position.set(0,200,20);
  scene.add(light1);
  var light0 = new THREE.AmbientLight( 0xffffff,0.25);
  scene.add(light0);


  // create main camera
  camera = new THREE.PerspectiveCamera( 90, window.innerWidth / window.innerHeight, 0.1, 1000 );
  camera.position.set(0,150,0);
  camera.lookAt(0,0,0);
  gameState.camera = camera;

  // create the ground and the skybox
  var ground = createGround('land.png');
  scene.add(ground);
  var skybox = createSkyBox('sky.jpg',1);
  scene.add(skybox);

  edgeCam = new THREE.PerspectiveCamera( 60, window.innerWidth / window.innerHeight, 0.1, 1000 );
  edgeCam.position.set(20,20,10);

  //create the avatar as the replacement of the race car
  avatarCam = new THREE.PerspectiveCamera( 60, window.innerWidth / window.innerHeight, 0.1, 1000 );
  avatar = createAvatar();
  avatar.position.set(-66,1,0);
  avatar.translateY(20);
  avatarCam.translateY(-4);
  avatarCam.translateZ(3);
  scene.add(avatar);
  gameState.camera = avatarCam;

  var outsideWall1 = new createOutsideWall(0x0000ff);
  outsideWall1.scale.set(66.5,5,1);
  outsideWall1.position.set(-70,1,-10);
  outsideWall1.receiveShadow = false;
  outsideWall1.rotateY(Math.PI/2);
  scene.add(outsideWall1);

  var outsideWall2 = new createOutsideWall(0x0000ff);
  outsideWall2.scale.set(20,5,1);
  outsideWall2.position.set(-70,1,15);
  outsideWall2.receiveShadow = false;
  outsideWall2.rotateY(Math.PI/2);
  scene.add(outsideWall2);

  var outsideWall3 = new createOutsideWall(0x0000ff);
  outsideWall3.scale.set(20,5,1);
  outsideWall3.position.set(-65,1,33);
  outsideWall3.receiveShadow = false;
  outsideWall3.rotateY(2*Math.PI/3);
  scene.add(outsideWall3);

  var outsideWall4 = new createOutsideWall(0x0000ff);
  outsideWall4.scale.set(50,5,1);
  outsideWall4.position.set(-47.75,1,63);
  outsideWall4.receiveShadow = false;
  outsideWall4.rotateY(2*Math.PI/3);
  scene.add(outsideWall4);

  var outsideWall5 = new createOutsideWall(0x0000ff);
  outsideWall5.scale.set(100,5,1);
  outsideWall5.position.set(-23.25,1,65.25);
  outsideWall5.receiveShadow = false;
  outsideWall5.rotateY(Math.PI);
  scene.add(outsideWall5);

  var outsideWall6 = new createOutsideWall(0x0000ff);
  outsideWall6.scale.set(25,5,1);
  outsideWall6.position.set(1.5,1,53.5);
  outsideWall6.receiveShadow = false;
  outsideWall6.rotateY(Math.PI/2);
  scene.add(outsideWall6);

  var insideWall1 = new createOutsideWall(0x0000ff);
  insideWall1.scale.set(50,5,1);
  insideWall1.position.set(-60,1,-3.5);
  insideWall1.receiveShadow = false;
  insideWall1.rotateY(Math.PI/2);
  scene.add(insideWall1);

  var insideWall2 = new createOutsideWall(0x0000ff);
  insideWall2.scale.set(40,5,1);
  insideWall2.position.set(-50,1,37.75);
  insideWall2.receiveShadow = false;
  insideWall2.rotateY(2*Math.PI/3);
  scene.add(insideWall2);

  var insideWall3 = new createOutsideWall(0x0000ff);
  insideWall3.scale.set(30,5,1);
  insideWall3.position.set(-25.35,1,55.25);
  insideWall3.receiveShadow = false;
  insideWall3.rotateY(Math.PI);
  scene.add(insideWall3);

  var insideWall4 = new createOutsideWall(0x0000ff);
  insideWall4.scale.set(25,5,1);
  insideWall4.position.set(-10,1,43.25);
  insideWall4.receiveShadow = false;
  insideWall4.rotateY(Math.PI/2);
  scene.add(insideWall4);

  var insideWall5 = new createOutsideWall(0x0000ff);
  insideWall5.scale.set(50,5,1);
  insideWall5.position.set(14.5,1,30.25);
  insideWall5.receiveShadow = false;
  insideWall5.rotateY(Math.PI);
  scene.add(insideWall5);

  var insideWall6 = new createOutsideWall(0x0000ff);
  insideWall6.scale.set(25,5,1);
  insideWall6.position.set(14,1,43.25);
  insideWall6.receiveShadow = false;
  insideWall6.rotateY(Math.PI/2);
  scene.add(insideWall6);

  var insideWall7 = new createOutsideWall(0x0000ff);
  insideWall7.scale.set(25,5,1);
  insideWall7.position.set(26.5,1,52.25);
  insideWall7.receiveShadow = false;
  insideWall7.rotateY(Math.PI/2);
  scene.add(insideWall7);

  var insideWall8 = new createOutsideWall(0x0000ff);
  insideWall8.scale.set(15,5,1);
  insideWall8.position.set(39,1,37.25);
  insideWall8.receiveShadow = false;
  insideWall8.rotateY(Math.PI/2);
  scene.add(insideWall8);

  var insideWall9 = new createOutsideWall(0x0000ff);
  insideWall9.scale.set(40,5,1);
  insideWall9.position.set(43,1,55.25);
  insideWall9.receiveShadow = false;
  insideWall9.rotateY(Math.PI/6);
  scene.add(insideWall9);

  var outsideWall7 = new createOutsideWall(0x0000ff);
  outsideWall7.scale.set(70,5,1);
  outsideWall7.position.set(60,1,13.5);
  outsideWall7.receiveShadow = false;
  outsideWall7.rotateY(Math.PI/2);
  scene.add(outsideWall7);

  var insideWall10 = new createOutsideWall(0x0000ff);
  insideWall10.scale.set(40,5,1);
  insideWall10.position.set(28,1,10.25);
  insideWall10.receiveShadow = false;
  insideWall10.rotateY(Math.PI/2);
  scene.add(insideWall10);

  var insideWall11 = new createOutsideWall(0x0000ff);
  insideWall11.scale.set(20,5,1);
  insideWall11.position.set(37.5,1,-10.5);
  insideWall11.receiveShadow = false;
  insideWall11.rotateY(Math.PI);
  scene.add(insideWall11);

  var insideWall12 = new createOutsideWall(0x0000ff);
  insideWall12.scale.set(25,5,1);
  insideWall12.position.set(47.5,1,-23.5);
  insideWall12.receiveShadow = false;
  insideWall12.rotateY(Math.PI/2);
  scene.add(insideWall12);

  var insideWall13 = new createOutsideWall(0x0000ff);
  insideWall13.scale.set(35,5,1);
  insideWall13.position.set(65.5,1,-35.5);
  insideWall13.receiveShadow = false;
  insideWall13.rotateY(Math.PI);
  scene.add(insideWall13);

  var outsideWall8 = new createOutsideWall(0x0000ff);
  outsideWall8.scale.set(40,5,1);
  outsideWall8.position.set(79.5,1,-22.5);
  outsideWall8.receiveShadow = false;
  outsideWall8.rotateY(Math.PI);
  scene.add(outsideWall8);

  var outsideWall9 = new createOutsideWall(0x0000ff);
  outsideWall9.scale.set(60,5,1);
  outsideWall9.position.set(100.5,1,-52.5);
  outsideWall9.receiveShadow = false;
  outsideWall9.rotateY(Math.PI/2);
  scene.add(outsideWall9);

  var insideWall14 = new createOutsideWall(0x0000ff);
  insideWall14.scale.set(30,5,1);
  insideWall14.position.set(83.5,1,-50.5);
  insideWall14.receiveShadow = false;
  insideWall14.rotateY(Math.PI/2);
  scene.add(insideWall14);

  var outsideWall10 = new createOutsideWall(0x0000ff);
  outsideWall10.scale.set(60,5,1);
  outsideWall10.position.set(70.5,1,-82.5);
  outsideWall10.receiveShadow = false;
  outsideWall10.rotateY(Math.PI);
  scene.add(outsideWall10);

  var insideWall15 = new createOutsideWall(0x0000ff);
  insideWall15.scale.set(25,5,1);
  insideWall15.position.set(71.5,1,-65.5);
  insideWall15.receiveShadow = false;
  insideWall15.rotateY(Math.PI);
  scene.add(insideWall15);

  var outsideWall11 = new createOutsideWall(0x0000ff);
  outsideWall11.scale.set(80,5,1);
  outsideWall11.position.set(20.5,1,-47.5);
  outsideWall11.receiveShadow = false;
  outsideWall11.rotateY(Math.PI/3);
  scene.add(outsideWall11);

  var insideWall16 = new createOutsideWall(0x0000ff);
  insideWall16.scale.set(63,5,1);
  insideWall16.position.set(43.5,1,-37.5);
  insideWall16.receiveShadow = false;
  insideWall16.rotateY(Math.PI/3);
  scene.add(insideWall16);

  var outsideWall12 = new createOutsideWall(0x0000ff);
  outsideWall12.scale.set(30,5,1);
  outsideWall12.position.set(0.5,1,0.5);
  outsideWall12.receiveShadow = false;
  outsideWall12.rotateY(Math.PI/2);
  scene.add(outsideWall12);

  var insideWall17 = new createOutsideWall(0x0000ff);
  insideWall17.scale.set(20,5,1);
  insideWall17.position.set(-19.5,1,30.25);
  insideWall17.receiveShadow = false;
  insideWall17.rotateY(Math.PI);
  scene.add(insideWall17);

  var outsideWall13 = new createOutsideWall(0x0000ff);
  outsideWall13.scale.set(15,5,1);
  outsideWall13.position.set(-7.5,1,-13.5);
  outsideWall13.receiveShadow = false;
  outsideWall13.rotateY(Math.PI);
  scene.add(outsideWall13);

  var insideWall18 = new createOutsideWall(0x0000ff);
  insideWall18.scale.set(80,5,1);
  insideWall18.position.set(-29.5,1,-9.5);
  insideWall18.receiveShadow = false;
  insideWall18.rotateY(Math.PI/2);
  scene.add(insideWall18);

  var outsideWall14 = new createOutsideWall(0x0000ff);
  outsideWall14.scale.set(55,5,1);
  outsideWall14.position.set(-15.5,1,-40.5);
  outsideWall14.receiveShadow = false;
  outsideWall14.rotateY(Math.PI/2);
  scene.add(outsideWall14);

  var insideWall18 = new createOutsideWall(0x0000ff);
  insideWall18.scale.set(10,5,1);
  insideWall18.position.set(-34.5,1,-50.5);
  insideWall18.receiveShadow = false;
  insideWall18.rotateY(Math.PI);
  scene.add(insideWall18);

  var outsideWall15 = new createOutsideWall(0x0000ff);
  outsideWall15.scale.set(35,5,1);
  outsideWall15.position.set(-32.5,1,-69.5);
  outsideWall15.receiveShadow = false;
  outsideWall15.rotateY(Math.PI);
  scene.add(outsideWall15);

  var insideWall18 = new createOutsideWall(0x0000ff);
  insideWall18.scale.set(23,5,1);
  insideWall18.position.set(-39.5,1,-38.5);
  insideWall18.receiveShadow = false;
  insideWall18.rotateY(Math.PI/2);
  scene.add(insideWall18);

  var outsideWall16 = new createOutsideWall(0x0000ff);
  outsideWall16.scale.set(28,5,1);
  outsideWall16.position.set(-50.5,1,-55.5);
  outsideWall16.receiveShadow = false;
  outsideWall16.rotateY(Math.PI/2);
  scene.add(outsideWall16);

  var insideWall19 = new createOutsideWall(0x0000ff);
  insideWall19.scale.set(20,5,1);
  insideWall19.position.set(-60.5,1,-41.5);
  insideWall19.receiveShadow = false;
  insideWall19.rotateY(Math.PI);
  scene.add(insideWall19);

  var outsideWall17 = new createOutsideWall(0x0000ff);
  outsideWall17.scale.set(20,5,1);
  outsideWall17.position.set(-50.5,1,-28.5);
  outsideWall17.receiveShadow = false;
  outsideWall17.rotateY(Math.PI);
  scene.add(outsideWall17);

  startingLine = new createStartingLine(0x00ff00);
  startingLine.scale.set(10,1,2);
  startingLine.position.set(-66,-0.45,5);
  startingLine.receiveShadow = false;
  outsideWall17.rotateX(Math.PI);
  scene.add(startingLine);


  obstruction1 = new createObstruction(0xff0000);
  obstruction1.scale.set(10,10,5);
  obstruction1.position.set(20,1,20);
  obstruction1.receiveShadow = false;
  scene.add(obstruction1);




}

//------------------------------------------------------------------------------
// This part is the default part. All the codes need this part

// DO NOT CHANGE ANYTHING IN THIS PART!!!!

/* We don't do much here, but we could do more!
*/
function initScene(){
  //scene = new THREE.Scene();
  var scene = new Physijs.Scene();
  return scene;
}
// initialize the physijs
function initPhysijs(){
  Physijs.scripts.worker = '/js/physijs_worker.js';
  Physijs.scripts.ammo = '/js/ammo.js';
}
/*
  The renderer needs a size and the actual canvas we draw on
  needs to be added to the body of the webpage. We also specify
  that the renderer will be computing soft shadows
*/
function initRenderer(){
  renderer = new THREE.WebGLRenderer();
  renderer.setSize( window.innerWidth, window.innerHeight-50 );
  document.body.appendChild( renderer.domElement );
  renderer.shadowMap.enabled = true;
  renderer.shadowMap.type = THREE.PCFSoftShadowMap;
}

function createPointLight(){
  var light;
  light = new THREE.PointLight( 0xffffff);
  light.castShadow = true;
  //Set up shadow properties for the light
  light.shadow.mapSize.width = 2048;  // default
  light.shadow.mapSize.height = 2048; // default
  light.shadow.camera.near = 0.5;       // default
  light.shadow.camera.far = 500      // default
  return light;
}

function createGround(image){
  // creating a textured plane which receives shadows
  var geometry = new THREE.PlaneGeometry( 500, 500, 500 );
  var texture = new THREE.TextureLoader().load( '../images/'+image );
  texture.wrapS = THREE.RepeatWrapping;
  texture.wrapT = THREE.RepeatWrapping;
  texture.repeat.set( 15, 15 );
  var material = new THREE.MeshLambertMaterial( { color: 0xffffff,  map: texture ,side:THREE.DoubleSide} );
  var pmaterial = new Physijs.createMaterial(material,0.9,0.05);
  //var mesh = new THREE.Mesh( geometry, material );
  var mesh = new Physijs.BoxMesh( geometry, pmaterial, 0 );
  mesh.receiveShadow = true;
  mesh.rotateX(Math.PI/2);
  return mesh
  // we need to rotate the mesh 90 degrees to make it horizontal not vertical
}

function createSkyBox(image,k){
  // creating a textured plane which receives shadows
  var geometry = new THREE.SphereGeometry( 160, 160, 160 );
  var texture = new THREE.TextureLoader().load( '../images/'+image );
  texture.wrapS = THREE.RepeatWrapping;
  texture.wrapT = THREE.RepeatWrapping;
  texture.repeat.set( k, k );
  var material = new THREE.MeshLambertMaterial( { color: 0xffffff,  map: texture ,side:THREE.DoubleSide} );
  //var pmaterial = new Physijs.createMaterial(material,0.9,0.5);
  //var mesh = new THREE.Mesh( geometry, material );
  var mesh = new THREE.Mesh( geometry, material, 0 );
  mesh.receiveShadow = false;
  return mesh
  // we need to rotate the mesh 90 degrees to make it horizontal not vertical
}

//------------------------------------------------------------------------------
// Now let's creat the race track and the background
// This part builds some elements of the game

function createOutsideWall(color,w,h,d){
  var geometry = new THREE.BoxGeometry( w, h, d);
  var material = new THREE.MeshLambertMaterial( { color: color} );
  mesh = new Physijs.BoxMesh( geometry, material, 0 );
  //mesh = new Physijs.BoxMesh( geometry, material,0 );
  mesh.castShadow = true;
  return mesh;
}

function createAvatar(other_object){
  //var geometry = new THREE.SphereGeometry( 4, 20, 20);
  var geometry = new THREE.BoxGeometry( 5, 5, 6);
  var material = new THREE.MeshLambertMaterial( { color: 0xffff00} );
  var pmaterial = new Physijs.createMaterial(material,0.9,0.5);
  //var mesh = new THREE.Mesh( geometry, material );
  var mesh = new Physijs.BoxMesh( geometry, pmaterial );
  mesh.setDamping(0.1,0.1);
  mesh.castShadow = true;
  avatarCam.position.set(0,4,0);
  avatarCam.lookAt(0,4,10);
  mesh.add(avatarCam);
  return mesh;
}

function createStartingLine(color,w,h,d){
  var geometry = new THREE.BoxGeometry( w, h, d);
  var material = new THREE.MeshLambertMaterial( { color: color} );
  mesh = new Physijs.BoxMesh( geometry, material, 0 );
  //mesh = new Physijs.BoxMesh( geometry, material,0 );
  mesh.castShadow = true;
  return mesh;
}

function createObstruction(color,w,h,d){
  var geometry = new THREE.BoxGeometry( w, h, d);
  var material = new THREE.MeshBasicMaterial( { color: color} );
  mesh = new Physijs.BoxMesh( geometry, material, 0 );
  //mesh = new Physijs.BoxMesh( geometry, material,0 );
  mesh.castShadow = true;
  return mesh;
}


//------------------------------------------------------------------------------
// This part allows the user to control the race car via keyboard

var clock;
function initControls(){
  // here is where we create the eventListeners to respond to operations
    //create a clock for the time-based animation ...
    clock = new THREE.Clock();
    clock.start();

    window.addEventListener( 'keydown', keydown);
    window.addEventListener( 'keyup',   keyup );
}

function keydown(event){
  console.log("Keydown: '"+event.key+"'");
  //console.dir(event);
  // first we handle the "play again" key in the "youwon" scene
  if (gameState.scene == 'youwon' && event.key=='r') {
    gameState.scene = 'main';
    gameState.score = 0;
    addBalls();
    return;
  }

  if(gameState.scene == 'initialPicture' && event.key=='p'){
    gameState.scene = 'main';
    gameState.score = 0;
    return;
  }

  if(gameState.scene=='youlose'&&event.key=='r'){
    gameState.scene = 'main';
    gameState.score = 0;
    return;
  }

  // this is the regular scene
  switch (event.key){
    // change the way the avatar is moving
    case "w": controls.fwd = true;  break;
    case "s": controls.bwd = true; break;
    case "a": controls.left = true; break;
    case "d": controls.right = true; break;
    case "r": controls.up = true; break;
    case "f": controls.down = true; break;
    case "m": controls.speed = 30; break;
    case "h": controls.reset = true; break;
    case "q": controls.rotateToLeft = true; break;
    case "e": controls.rotateToRight = true; break;
    // switch cameras
    case "1": gameState.camera = camera; break;
    case "2": gameState.camera = avatarCam; break;
    case "3": gameState.camera = edgeCam; break;
    // move the camera around, relative to the avatar
    case "ArrowLeft": avatarCam.translateY(1);break;
    case "ArrowRight": avatarCam.translateY(-1);break;
    case "ArrowUp": avatarCam.translateZ(-1);break;
    case "ArrowDown": avatarCam.translateZ(1);break;

  }
}

function keyup(event){
  //console.log("Keydown:"+event.key);
  //console.dir(event);
  switch (event.key){
    case "w": controls.fwd   = false;  break;
    case "s": controls.bwd   = false; break;
    case "a": controls.left  = false; break;
    case "d": controls.right = false; break;
    case "r": controls.up    = false; break;
    case "f": controls.down  = false; break;
    case "m": controls.speed = 10; break;
    case "h": controls.reset = false; break;
    case "q": controls.rotateToLeft = false; break;
    case "e": controls.rotateToRight = false; break;

  }
}

function updateAvatar(){
  "change the avatar's linear or angular velocity based on controls state (set by WSAD key presses)"

  var forward = avatar.getWorldDirection();
  var goLeft = avatar.getWorldDirection();
  // When the user press forward & backward
  if (controls.fwd){
    avatar.setLinearVelocity(forward.multiplyScalar(controls.speed));
  } else if (controls.bwd){
    avatar.setLinearVelocity(forward.multiplyScalar(-controls.speed));
  } else {
    var velocity = avatar.getLinearVelocity();
    velocity.x=velocity.z=0;
    avatar.setLinearVelocity(velocity); //stop the xz motion
  }
  //When the user press the left & right
  if (controls.left){
    avatar.setAngularVelocity(new THREE.Vector3(0,controls.speed*0.1,0));
  } else if (controls.right){
    avatar.setAngularVelocity(new THREE.Vector3(0,-controls.speed*0.1,0));
  }
  //When the user tryies to move the camera
  if(controls.rotateToLeft){
    avatarCam.rotateY(0.01);
  }else if(controls.rotateToRight){
    avatarCam.rotateY(-0.01);
  }
  //Whehn the user wants to reset the game
  if (controls.reset){
    avatar.__dirtyPosition = true;
    avatar.position.set(40,10,40);
  }

}

//------------------------------------------------------------------------------

function animate() {
  var counter = 0;
  requestAnimationFrame( animate );


  switch(gameState.scene) {
/*
    case "youwon":
      endText.rotateY(0.005);
      renderer.render( endScene, endCamera );
      break;
*/
    case "main":
      updateAvatar();
      scene.simulate();
      if (gameState.camera!= 'none'){
        renderer.render( scene, gameState.camera );
      }
      break;

    default:
      console.log("don't know the scene "+gameState.scene);
  }
  //draw heads up display ..
  var info = document.getElementById("info");
  info.innerHTML='<div style="font-size:24pt">Score: ' + gameState.score + '</div>';
}
