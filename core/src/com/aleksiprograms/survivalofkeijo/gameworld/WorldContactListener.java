package com.aleksiprograms.survivalofkeijo.gameworld;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.collectibles.Coin;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SolidObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Ammunition;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.DeadAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Player;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.TutorialObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;

public class WorldContactListener extends ContactListener {

    private TheGame game;
    private Vector3 uselessVector;

    public WorldContactListener(TheGame game) {
        this.game = game;
        uselessVector = new Vector3();
    }

    @Override
    public void onContactStarted(btCollisionObject colObj0, btCollisionObject colObj1) {
        // MODEL_PERSON_ENEMY HIT
        /*if (colObj0.userData instanceof Enemy && colObj1.userData instanceof AmmunitionVisual) {
            ((Enemy) (colObj0.userData)).onHit(((AmmunitionVisual) (colObj1.userData)).meleeDamage);
        }
        if (colObj0.userData instanceof AmmunitionVisual && colObj1.userData instanceof Enemy) {
            ((Enemy) (colObj1.userData)).onHit(((AmmunitionVisual) (colObj0.userData)).meleeDamage);
        }*/

        // TUTORIAL
        if (colObj0.userData instanceof Player && colObj1.userData instanceof TutorialObject) {
            Gdx.app.log("HIT", "TUTORIAL");
        }
        if (colObj0.userData instanceof TutorialObject && colObj1.userData instanceof Player) {
            Gdx.app.log("HIT", "TUTORIAL");
        }

        // DEAD AREA
        if (colObj0.userData instanceof Player && colObj1.userData instanceof DeadAreaObject) {
            Gdx.app.log("HIT", "DEAD AREA");
        }
        if (colObj0.userData instanceof DeadAreaObject && colObj1.userData instanceof Player) {
            Gdx.app.log("HIT", "DEAD AREA");
        }

        // COIN
        if (colObj0.userData instanceof Player && colObj1.userData instanceof Coin) {
            if (!(((Coin) colObj1.userData).collected)) {
                ((Coin) colObj1.userData).onCollect();
            }
        }
        if (colObj0.userData instanceof Coin && colObj1.userData instanceof Player) {
            if (!(((Coin) colObj0.userData).collected)) {
                ((Coin) colObj0.userData).onCollect();
            }
        }

        if (colObj0.userData instanceof Ammunition && colObj1.userData instanceof Person) {
            if (!(((Ammunition) colObj0.userData).hit)) {
                ((Person) colObj1.userData).onHit(((Ammunition) colObj0.userData).damage);
                ((Ammunition) colObj0.userData).onHit(colObj0.getWorldTransform().getTranslation(uselessVector), (PhysicalObject) colObj1.userData);
            }
        }
        if (colObj0.userData instanceof Person && colObj1.userData instanceof Ammunition) {
            if (!(((Ammunition) colObj1.userData).hit)) {
                ((Person) colObj0.userData).onHit(((Ammunition) colObj1.userData).damage);
                ((Ammunition) colObj1.userData).onHit(colObj1.getWorldTransform().getTranslation(uselessVector), (PhysicalObject) colObj0.userData);
            }
        }

        if (colObj0.userData instanceof Ammunition && colObj1.userData instanceof SolidObject) {
            ((Ammunition) colObj0.userData).onHit(colObj0.getWorldTransform().getTranslation(uselessVector), (PhysicalObject) colObj1.userData);
        }
        if (colObj0.userData instanceof SolidObject && colObj1.userData instanceof Ammunition) {
            ((Ammunition) colObj1.userData).onHit(colObj1.getWorldTransform().getTranslation(uselessVector), (PhysicalObject) colObj0.userData);
        }
        /*if (colObj0.userData instanceof PhysicalObject && colObj1.userData instanceof PhysicalObject) {
            ((PhysicalObject) colObj0.userData).onCollision(colObj0.getInterpolationLinearVelocity());
            ((PhysicalObject) colObj1.userData).onCollision(colObj1.getInterpolationLinearVelocity());
        }*/
    }

    @Override
    public void onContactEnded(btCollisionObject colObj0, btCollisionObject colObj1) {}
}