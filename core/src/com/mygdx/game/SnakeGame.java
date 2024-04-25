package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Apple.Apple;
import com.mygdx.game.Snake.Snake;

import java.util.ArrayList;

public class SnakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	private BitmapFont fonte;
	private BitmapFont mensagem;
	private Snake snake;
	private Apple apple;
	private Texture snakeImage;
	private Texture appleImage;
	private Texture fundo;
	private float deltaTime;
	private int pontuacao;
	private int estadoJogo;
	private OrthographicCamera camera;
	private Viewport viewport;
	private float posicaoInicialVertical;
	private float posicaoInicialHorizontal;
	private float larguraDispositivo;
	private float alturaDispositivo;
	private final float VIRTUAL_WIDTH = 768;
	private final float VIRTUAL_HEIGHT = 1024;

	@Override
	public void create () {
		batch = new SpriteBatch();

		fonte = new BitmapFont();
		fonte.setColor(Color.WHITE);
		fonte.getData().setScale(4);

		mensagem = new BitmapFont();
		mensagem.setColor(Color.WHITE);
		mensagem.getData().setScale(3);

		camera = new OrthographicCamera();
		camera.position.set(VIRTUAL_WIDTH/2, VIRTUAL_HEIGHT/2, 0);
		viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);

		larguraDispositivo = VIRTUAL_WIDTH;
		alturaDispositivo = VIRTUAL_HEIGHT;

		posicaoInicialVertical = alturaDispositivo/2;
		posicaoInicialHorizontal = larguraDispositivo/2;

		snakeImage = new Texture("HeadSnake.png");
		appleImage = new Texture("apple.png");
		fundo = new Texture("background.jpg");

		snake = new Snake(snakeImage, posicaoInicialHorizontal, posicaoInicialVertical );
		apple = new Apple(appleImage, larguraDispositivo, larguraDispositivo);

	}

	private void snakeUpdate() {
		snake.listenKey();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		camera.update();

		deltaTime = Gdx.graphics.getDeltaTime() ;

		if (estadoJogo == 0) {

			if(Gdx.input.justTouched()){
				estadoJogo = 1;
			}

		} else {

			if (estadoJogo == 1) {
				snakeUpdate();

			} else {

				if(Gdx.input.justTouched()){

					estadoJogo = 0;
					snake.setPosicaoY(posicaoInicialVertical);
					snake.setPosicaoX(posicaoInicialHorizontal);
					snake.setVelocidade(3f);
					pontuacao = 0;
				}
			}
		}

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(fundo, 0,0, larguraDispositivo, alturaDispositivo);
		fonte.draw(batch, String.valueOf(pontuacao), larguraDispositivo/2, alturaDispositivo-50);
		snake.render(batch);
		for (Rectangle segmento : snake.getSegmentos()) {
			batch.draw(snakeImage, segmento.getX(), segmento.getY());
		}
		apple.render(batch);

		if ( ( snake.getPosicaoY() <= 0 || snake.getPosicaoY() >= alturaDispositivo ) || (snake.getPosicaoX() <= 0 || snake.getPosicaoX() >= larguraDispositivo) ) {
			estadoJogo = 2;
		}

		if (Intersector.overlaps(snake.getCaixaJogador(), apple.getCircle())) {

			if (snake.getVelocidade() <= 5) {
				pontuacao += 10;
			} else if (snake.getVelocidade() <= 10){
				pontuacao += 15;
			} else {
				pontuacao += 20;
			}

			snake.setVelocidade( snake.getVelocidade() + 1f);


			apple.criar(larguraDispositivo, alturaDispositivo);
			snake.setComeuApple(true);
		}

		if(estadoJogo == 2){
			mensagem.draw(batch, "Você Perdeu" , larguraDispositivo/2 - 130, 550);
			mensagem.draw(batch, "Clique Para Reiniciar" , larguraDispositivo/2 - 190, 500);
		}

		if (estadoJogo == 0) {
			mensagem.draw(batch, "Clique Para Iniciar" , larguraDispositivo/2 - 190, 500);
		}

		batch.end();

	}

	public void resize(int width, int height){
		viewport.update(width,height);
	}

	@Override
	public void dispose () {
		batch.dispose();
		snake.dispose();
		mensagem.dispose();
	}
}
