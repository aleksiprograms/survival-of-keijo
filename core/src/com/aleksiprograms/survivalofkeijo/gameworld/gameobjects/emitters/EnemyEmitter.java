package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.emitters;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.RenderableObject;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;

public class EnemyEmitter extends RenderableObject {

    private final btRigidBody rigidBodyFloor;
    private final btRigidBody rigidBodyForPlayer;
    private float x;
    private float y;
    private float z;
    private Vector3 objectPosition;
    private Quaternion objectQuaternion;
    private Vector3 objectScale;
    private Matrix4 objectTransform;

    public EnemyEmitter(TheGame game) {

        super(
                game,
                new ModelInstance(game.getAssetManager()
                        .get(Constants.MODEL_BUILDING_ENEMY_EMITTER, Model.class)));

        objectPosition = new Vector3();
        objectQuaternion = new Quaternion();
        objectScale = new Vector3(1, 1, 1);
        objectTransform = new Matrix4();
        Vector3 localInertia = new Vector3();
        localInertia.set(0, 0, 0);
        btRigidBody.btRigidBodyConstructionInfo constructionInfoFloor;
        constructionInfoFloor = new btRigidBody.btRigidBodyConstructionInfo(
                0,
                null,
                new btBoxShape(new Vector3(1.5f / 2f, 0.1f / 2f, 1.5f / 2f)),
                localInertia);
        rigidBodyFloor = new btRigidBody(constructionInfoFloor);
        constructionInfoFloor.dispose();
        btRigidBody.btRigidBodyConstructionInfo constructionInfoForPlayer;
        constructionInfoForPlayer = new btRigidBody.btRigidBodyConstructionInfo(
                0,
                null,
                new btBoxShape(new Vector3(1.5f / 2f, 2f / 2f, 1.5f / 2f)),
                localInertia);
        rigidBodyForPlayer = new btRigidBody(constructionInfoForPlayer);
        constructionInfoForPlayer.dispose();
    }

    public void init(float x, float y, float z, float angle) {
        this.x = x;
        this.y = y;
        this.z = z;

        game.getGameWorld().getDynamicsWorld().addRigidBody(
                rigidBodyFloor, Constants.CATEGORY_SOLID, Constants.MASK_SOLID);
        objectPosition.set(x, y - 0.95f, z);
        objectQuaternion.set(0, 0, 0, 0);
        objectQuaternion.set(Vector3.Z, angle);
        objectTransform.set(objectPosition, objectQuaternion, objectScale);
        rigidBodyFloor.setWorldTransform(objectTransform);

        game.getGameWorld().getDynamicsWorld().addRigidBody(
                rigidBodyForPlayer, Constants.CATEGORY_ENEMY_EMITTER, Constants.MASK_ENEMY_EMITTER);
        objectPosition.set(x, y, z);
        objectQuaternion.set(0, 0, 0, 0);
        objectQuaternion.set(Vector3.Z, angle);
        objectTransform.set(objectPosition, objectQuaternion, objectScale);
        rigidBodyForPlayer.setWorldTransform(objectTransform);

        transform.set(rigidBodyForPlayer.getWorldTransform());
    }

    public void emitEnemy(int weaponID, int health) {
        game.getGameWorld().addEnemy(game.getGameObjectPools()
                .getEnemyPool().obtain(), x, y, z, weaponID, health);
    }

    @Override
    public void reset() {
        super.reset();
        game.getGameWorld().getDynamicsWorld().removeRigidBody(rigidBodyFloor);
        game.getGameWorld().getDynamicsWorld().removeRigidBody(rigidBodyForPlayer);
    }
}