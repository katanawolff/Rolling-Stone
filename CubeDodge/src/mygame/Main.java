package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */


public class Main extends SimpleApplication {

    Scene sc;
    Player p;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        //flyCam.setEnabled(false);
        //cam.setLocation(new Vector3f(0, 15, 3));
        //cam.lookAtDirection(new Vector3f(0,0,0), new Vector3f(0,1,0));
        
        flyCam.setZoomSpeed(20);
        flyCam.setMoveSpeed(20);

        Node map = new Node();
        Node playerSphere = new Node();
        
        Sphere s = new Sphere(105,20,5);
        Geometry pgeom = new Geometry("Sphere", s);
        Material pmat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        pmat.setColor("Color", ColorRGBA.Blue);
        pgeom.setMaterial(pmat);
        playerSphere.attachChild(pgeom);
        
        p = new Player(playerSphere, inputManager);
        
        Box floor = new Box(240, .2f, 240);
        Geometry fl = new Geometry("Box", floor);
        Material flMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        flMat.setColor("Color", ColorRGBA.White);
        fl.setMaterial(flMat);
        map.attachChild(fl);
        rootNode.attachChild(map);
        
        
        sc = new Scene(assetManager, map);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
