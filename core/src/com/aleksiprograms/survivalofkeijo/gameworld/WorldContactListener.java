package com.aleksiprograms.survivalofkeijo.gameworld;

import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Ammunition;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.collectibles.Coin;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SolidObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Player;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;

public class WorldContactListener extends ContactListener {

    public WorldContactListener() {
    }

    @Override
    public void onContactStarted(btCollisionObject colObj0, btCollisionObject colObj1) {
        if (colObj0.userData instanceof Player && colObj1.userData instanceof Coin) {
            if (!(((Coin) colObj1.userData).isCollected())) {
                ((Coin) colObj1.userData).onCollect();
            }
        }
        if (colObj0.userData instanceof Coin && colObj1.userData instanceof Player) {
            if (!(((Coin) colObj0.userData).isCollected())) {
                ((Coin) colObj0.userData).onCollect();
            }
        }

        if (colObj0.userData instanceof Ammunition && colObj1.userData instanceof Person) {
            if (!(((Ammunition) colObj0.userData).isHit())) {
                ((Person) colObj1.userData).onHit(((Ammunition) colObj0.userData).getDamage());
                ((Ammunition) colObj0.userData).onHit(colObj0.getWorldTransform()
                        .getTranslation(new Vector3()), (PhysicalObject) colObj1.userData);
            }
        }
        if (colObj0.userData instanceof Person && colObj1.userData instanceof Ammunition) {
            if (!(((Ammunition) colObj1.userData).isHit())) {
                ((Person) colObj0.userData).onHit(((Ammunition) colObj1.userData).getDamage());
                ((Ammunition) colObj1.userData).onHit(colObj1.getWorldTransform()
                        .getTranslation(new Vector3()), (PhysicalObject) colObj0.userData);
            }
        }

        if (colObj0.userData instanceof Ammunition && colObj1.userData instanceof SolidObject) {
            ((Ammunition) colObj0.userData).onHit(colObj0.getWorldTransform()
                    .getTranslation(new Vector3()), (PhysicalObject) colObj1.userData);
        }
        if (colObj0.userData instanceof SolidObject && colObj1.userData instanceof Ammunition) {
            ((Ammunition) colObj1.userData).onHit(colObj1.getWorldTransform()
                    .getTranslation(new Vector3()), (PhysicalObject) colObj0.userData);
        }
    }

    @Override
    public void onContactEnded(btCollisionObject colObj0, btCollisionObject colObj1) {
    }
}