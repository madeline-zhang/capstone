package com.mad.capstone.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mad.capstone.SkiesTurnPurple;
import com.mad.capstone.screen.MainScreen;

public class Fyren extends Sprite {
  public boolean isOnGround;
  public World world;
  public Body b2Body;
  private TextureRegion fyrenStand;

  private int updateTicker = 0;

  public Fyren(World world, MainScreen screen) {
    super(screen.getAtlas().findRegion("fyren"));
    this.world = world;
    defineFyren();
    fyrenStand = new TextureRegion(getTexture(), 80, 0, 20, 40);
    setBounds(0, 0, 20 / SkiesTurnPurple.PPM, 40 / SkiesTurnPurple.PPM);
    setRegion(fyrenStand);
    this.isOnGround = true;

  }

  public void update(float dt) {
    updateTicker++;
    setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
//    if (updateTicker >= 80) {
//      updateTicker = 0;
//      System.out.println("Thane's position: " + b2Body.getPosition().x + ", " + b2Body.getPosition().y);
//    }
  }

  public void defineFyren() {
    BodyDef bDef = new BodyDef();
    bDef.position.set(260 / SkiesTurnPurple.PPM, 190 / SkiesTurnPurple.PPM);
    bDef.type = BodyDef.BodyType.DynamicBody;
    b2Body = world.createBody(bDef);

    FixtureDef fDef = new FixtureDef();
    PolygonShape polygon = new PolygonShape();
    polygon.setAsBox(10 / SkiesTurnPurple.PPM, 20 / SkiesTurnPurple.PPM);
    fDef.shape = polygon;
    b2Body.createFixture(fDef).setUserData("fyren");

    polygon.dispose();
  }

  public void setOnGround(boolean bool) {
    isOnGround = bool;
  }

  public void dispose() {
    getTexture().dispose();

  }

}