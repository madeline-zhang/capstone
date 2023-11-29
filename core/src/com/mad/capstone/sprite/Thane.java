package com.mad.capstone.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mad.capstone.SkiesTurnPurple;
import com.mad.capstone.screen.MainScreen;

public class Thane extends Sprite {
  public boolean isOnGround;
  public World world;
  public Body b2Body;
  private TextureRegion thaneStand;

  private int updateTicker = 0;

  public Thane(World world, MainScreen screen) {
    super(screen.getAtlas().findRegion("thane"));
    thaneStand = new TextureRegion(getTexture(), 0, 0, 20, 40);
    setBounds(0, 0, 20 / SkiesTurnPurple.PPM, 40 / SkiesTurnPurple.PPM);
    setRegion(thaneStand);

    this.world = world;

    this.isOnGround = true;
    setOrigin(getWidth()/2f, getHeight()/2f);
    defineThane();
  }

  public void update(float dt) {
    updateTicker++;
    setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
    if (updateTicker >= 80) {
      updateTicker = 0;
      System.out.println("Thane's position: " + b2Body.getPosition().x + ", " + b2Body.getPosition().y);
    }
    if(b2Body.getLinearVelocity().y == 0) {
      isOnGround = true;
    }
  }

  public void defineThane() {
    BodyDef bDef = new BodyDef();
    bDef.position.set(240 / SkiesTurnPurple.PPM, 190 / SkiesTurnPurple.PPM);
    bDef.type = BodyDef.BodyType.DynamicBody;
    b2Body = world.createBody(bDef);

    FixtureDef fDef = new FixtureDef();
    CircleShape shape = new CircleShape();
    shape.setRadius(20 / SkiesTurnPurple.PPM);

    fDef.shape = shape;
    b2Body.createFixture(fDef);
  }

  public void setOnGroundFalse() {
    isOnGround = false;
  }

//  public void checkOnGround() {
//    Vector2 raycastBegin = new Vector2(b2Body.getPosition());
//    float innerPlayerWidth = 20 * 0.6f;
//    raycastBegin.sub(innerPlayerWidth/2.0f, 0.0f);
//    Vector2 raycastEnd = new Vector2(raycastBegin).add(0.0f, -0.16f);
//
//    RaycastInfo
//  }
  public void dispose() {
    getTexture().dispose();
  }

}
