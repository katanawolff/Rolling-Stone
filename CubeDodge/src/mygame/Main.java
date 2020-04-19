package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
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
    static Main app;

    public static void main(String[] args) {
        app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        //flyCam.setEnabled(false);
        cam.setLocation(new Vector3f(0, 5, 20));
        
        flyCam.setZoomSpeed(20);
        flyCam.setMoveSpeed(20);

        Node map = new Node();
        Node playerSphere = new Node();
        Node treeNode = new Node();
        
        Sphere s = new Sphere(20,20,1);
        Geometry pgeom = new Geometry("Sphere", s);
        Material pmat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        pmat.setColor("Color", ColorRGBA.Blue);
        pgeom.setMaterial(pmat);
        playerSphere.setLocalTranslation(0, -2.5f, 0);
        playerSphere.attachChild(pgeom);
        
        p = new Player(playerSphere, inputManager);
        
        Box floor = new Box(240, .2f, 240);
        Geometry fl = new Geometry("Box", floor);
        Material flMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        flMat.setColor("Color", ColorRGBA.White);
        fl.setMaterial(flMat);
        map.attachChild(fl);
        
        rootNode.attachChild(treeNode);
        rootNode.attachChild(map);
        rootNode.attachChild(playerSphere);
        
        
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText scoreText = new BitmapText(guiFont, false);
        scoreText.setSize(guiFont.getCharSet().getRenderedSize());
        scoreText.setLocalTranslation(300, scoreText.getLineHeight(), 0);
        scoreText.setColor(ColorRGBA.Black);
        guiNode.attachChild(scoreText);
        
        sc = new Scene(assetManager, map, treeNode, p, scoreText, app);

        scoreText.setText("Current Score: ");
    }

    @Override
    public void simpleUpdate(float tpf) {
        Vector3f v1 = p.sphere.getLocalTranslation();
        Vector3f v2 = cam.getLocation();
        Vector3f v3 = v1.subtract(v2);
        sc.update(tpf);
        p.update(tpf);
        cam.lookAt(new Vector3f(v1.x, v1.y, v1.z - 240), new Vector3f(0, 1, 0));
        cam.setLocation(new Vector3f(v1.x, v1.y + 5, v1.z + 20));
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
