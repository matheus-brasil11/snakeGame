package com.mygdx.game.Snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Snake {
    private Circle caixaJogador;
    private Vector2 posicao;
    private Texture texture;
    private float velocidade;
    private boolean y_up = false;
    private boolean y_down = false;
    private boolean x_up = false;
    private boolean x_down = false;

    public Snake(Texture texture, float x, float y) {
        this.texture = texture;
        this.caixaJogador = new Circle();
        this.posicao = new Vector2(x, y); // Inicializa a posição da cobra
        this.velocidade = 3f;
    }

    private void imobilizaCobra(){
        y_up = false;
        y_down = false;
        x_up = false;
        x_down = false;
    }

    public void listenKey(){

        if (y_up) {
            posicao.y += velocidade;
        }

        if (y_down) {
            posicao.y -= velocidade;
        }
        if (x_up) {
            posicao.x += velocidade;
        }

        if (x_down) {
            posicao.x -= velocidade;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            imobilizaCobra();
            y_up = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            imobilizaCobra();
            y_down = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            imobilizaCobra();
            x_up = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            imobilizaCobra();
            x_down = true;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        caixaJogador.setPosition(posicao);
    }

    public void render(SpriteBatch batch ){
        batch.draw(texture, posicao.x,  posicao.y );
    }

    public void dispose(){
        texture.dispose();
    }


    public Circle getCaixaJogador() {
        return caixaJogador;
    }

    public void setCaixaJogador(Circle caixaJogador) {
        this.caixaJogador = caixaJogador;
    }

    public float getPosicaoY() {
        return posicao.y;
    }

    public void setPosicaoY(float posicao) {
        this.posicao.y = posicao;
    }

    public float getPosicaoX() {
        return posicao.x;
    }

    public void setPosicaoX(float posicao) {
        this.posicao.x = posicao;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public boolean isY_up() {
        return y_up;
    }

    public void setY_up(boolean y_up) {
        this.y_up = y_up;
    }

    public boolean isY_down() {
        return y_down;
    }

    public void setY_down(boolean y_down) {
        this.y_down = y_down;
    }

    public boolean isX_up() {
        return x_up;
    }

    public void setX_up(boolean x_up) {
        this.x_up = x_up;
    }

    public boolean isX_down() {
        return x_down;
    }

    public void setX_down(boolean x_down) {
        this.x_down = x_down;
    }
}