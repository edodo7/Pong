package com.gdx.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Pong extends ApplicationAdapter {
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private ShapeRenderer dottedLineRenderer;
	private float[] arrayOfDirections;
	private Vector2 direction;
	private Vector2 ballPosition;
	private Vector2 paddlePositon;
	private Music ballOutside;
	private Music hitPaddle;
	private Music hitWall;
	private Random random;
	private final int PADDLE_WIDTH = 20;
	private final int PADDLE_HEIGHT = 100;
	private final int BALL_RADIUS = 12;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		shapeRenderer = new ShapeRenderer();
		dottedLineRenderer = new ShapeRenderer();
		direction = new Vector2();
		arrayOfDirections = new float[]{6f,-6f};
		random = new Random();
		chooseRandomDirection();
		ballPosition = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		paddlePositon = new Vector2(150, Gdx.graphics.getHeight()/2);
		ballOutside = Gdx.audio.newMusic(Gdx.files.internal("ballOutside.mp3"));
		hitPaddle = Gdx.audio.newMusic(Gdx.files.internal("hitPaddle.wav"));
		hitWall = Gdx.audio.newMusic(Gdx.files.internal("hitWall.wav"));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(255,255,255,1);
		drawDottedLine(dottedLineRenderer, 20);
		shapeRenderer.circle(ballPosition.x,ballPosition.y, BALL_RADIUS);
		shapeRenderer.rect(paddlePositon.x,paddlePositon.y,PADDLE_WIDTH,PADDLE_HEIGHT);
		shapeRenderer.end();
		if(ballPosition.x >= Gdx.graphics.getWidth() || ballPosition.x <= 0) {
			hitWall.play();
			direction.x = -direction.x;
		}
		if (ballPosition.y >= Gdx.graphics.getHeight() || ballPosition.y <= 0 ) {
			hitWall.play();
			direction.y = -direction.y;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			if( (paddlePositon.y + PADDLE_HEIGHT) < Gdx.graphics.getHeight() )
				paddlePositon.y += 7f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			if (paddlePositon.y > 0)
				paddlePositon.y -= 7f;
		}
		if (( ballPosition.x + 40 ) < paddlePositon.x  ){
			ballOutside.play();
			ballPosition.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			chooseRandomDirection();
		}
		if(isCollisonDetected(paddlePositon,ballPosition)){
			hitPaddle.play();
			direction.x = -direction.x;
		}
		ballPosition.add(direction);
	}


	private boolean isCollisonDetected(Vector2 paddlePos, Vector2 ballPos) {
		float ballEdge = ballPos.x - BALL_RADIUS;
		float paddleEdge = paddlePos.x + PADDLE_WIDTH;
		return ((ballEdge <= paddleEdge) && ballWithinPaddleHeight(paddlePos,ballPos));
	}

	private boolean ballWithinPaddleHeight(Vector2 paddlePos, Vector2 ballPosition){
		return ((paddlePos.y + PADDLE_HEIGHT >= ballPosition.y + BALL_RADIUS) &&
				(paddlePos.y  <= ballPosition.y - BALL_RADIUS) );
	}

	private void chooseRandomDirection(){
		float xDirection = arrayOfDirections[random.nextInt(2)];
		float yDirection = arrayOfDirections[random.nextInt(2)];
		direction.set(xDirection,yDirection);
	}


	private void drawDottedLine(ShapeRenderer shapeRenderer, int dotDist) {
		shapeRenderer.begin(ShapeType.Line);
		for(float i = 0; i < Gdx.graphics.getHeight(); i += dotDist + 15) {
			shapeRenderer.line(Gdx.graphics.getWidth()/2,i,Gdx.graphics.getWidth()/2,i + 15);
		}
		shapeRenderer.end();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		ballOutside.dispose();
		hitWall.dispose();
		hitPaddle.dispose();
	}
}
