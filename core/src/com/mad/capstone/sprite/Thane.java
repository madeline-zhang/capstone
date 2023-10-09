package com.mad.capstone.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mad.capstone.MyGdxGame;

public class Thane extends Sprite {
  public World world;
  public Body b2Body;

  public Thane(World world) {
    defineMario();
  }

  public void defineMario() {
    BodyDef bDef = new BodyDef();
    bDef.position.set(50/MyGdxGame.PPM, 50/MyGdxGame.PPM);
    bDef.type = BodyDef.BodyType.DynamicBody;
    b2Body = world.createBody (bDef);

    FixtureDef fDef = new FixtureDef();
    CircleShape shape = new CircleShape();
    shape.setRadius(50/ MyGdxGame.PPM);

    fDef.shape = shape;
    b2Body.createFixture(fDef);
  }
}
