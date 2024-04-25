package com.mygdx.game.Apple;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import java.util.Random;

public class Apple {
    private Texture texture;
    private Circle circle;
    private float x;
    private float y;
    private final float APPLE_SIZE = 30;

    public Apple(Texture texture, float telaLargura, float telaAltura) {
        this.texture = texture;
        this.circle = new Circle();
        this.criar(telaLargura, telaAltura);
    }

    public void criar(float telaLargura, float telaAltura) {
        Random random = new Random();
        x = random.nextInt((int) telaLargura -100);
        y = random.nextInt((int) telaAltura -100);
        circle.set(x , y , 30);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, APPLE_SIZE, APPLE_SIZE);
    }

    public Circle getCircle() {
        return circle;
    }

    public void dispose() {
        texture.dispose();
    }
}
