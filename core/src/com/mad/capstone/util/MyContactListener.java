package com.mad.capstone.util;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;

//public class MyContactListener implements ContactListener {
//  private B2d parent;
//
//  public B2dContactListener(B2dModel parent){
//    this.parent = parent;
//  }
//
//  @Override
//  public void beginContact(Contact contact) {
//    System.out.println("Contact");
//    Fixture fa = contact.getFixtureA();
//    Fixture fb = contact.getFixtureB();
//    System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
//  }
//
//  @Override
//  public void endContact(Contact contact) {
//  }
//
//  @Override
//  public void preSolve(Contact contact, Manifold oldManifold) {
//  }
//
//  @Override
//  public void postSolve(Contact contact, ContactImpulse impulse) {
//  }
//}
