class Scene {
	int sceneNum; 
	
	PImage bg;
	PVector bgPos = new PVector();
	PImage[] img;

	PGraphics main;
	
	Gif testAnim;

	color bgColor;

	Scene(int index){
		sceneNum = index;
		//bg = loadImage("scene"+sceneNum+"/move.gif");
		//testAnim = new Gif(antoje.this, "scene"+sceneNum+"/move.gif");
		//testAnim.play();
		main = createGraphics(width, height);
		bgColor = getRandomColor();
		main.beginDraw();
		main.background(bgColor);
		main.endDraw();
	}

	void display() {
		drawBG();
		drawID();
	}

	void drawBG() {
		//imageMode(CENTER);
		//image(testAnim,width/2,height/2);
		image(main, 0, 0);
	}

	void drawID() {
		text(sceneNum, width - 25, 25);
	}

	PVector getCenterPosition(PImage theImage) {
		PVector size = new PVector(theImage.width, theImage.height);
		size.sub(height, width, 0);
		return size;
	}
}