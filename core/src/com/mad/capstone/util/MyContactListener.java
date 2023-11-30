package com.mad.capstone.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mad.capstone.sprite.Thane;

public class MyContactListener implements ContactListener {
  private Thane thane;

  public MyContactListener(Thane thane){
    this.thane = thane;
  }

  @Override
  public void beginContact(Contact contact) {
//    System.out.println("Contact");
    Fixture fa = contact.getFixtureA();
    Fixture fb = contact.getFixtureB();
//    Fixture feet = fa.getUserData().equals("thane") ? fa : fb;
//    Fixture ground = feet == fa ? fb : fa;
    System.out.println(fa.getUserData()+" has hit "+ fb.getUserData());
//    System.out.println(fb.getUserData());
    thane.setOnGround(true);
  }

  @Override
  public void endContact(Contact contact) {
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
  }
}
