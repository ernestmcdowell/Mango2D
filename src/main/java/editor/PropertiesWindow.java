package editor;

import imgui.ImGui;
import mango.GameObject;
import mango.MouseListener;
import renderer.PickingTexture;
import scenes.Scene;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class PropertiesWindow {
    private GameObject activeGameobject = null;
    private PickingTexture pickingTexture;

    public PropertiesWindow(PickingTexture pickingTexture){
        this.pickingTexture = pickingTexture;
    }


    public void update(float dt, Scene currentScene) {
        if (MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
            int x = (int)MouseListener.getScreenX();
            int y = (int)MouseListener.getScreenY();
            System.out.println(pickingTexture.readPixel(x, y));
            int gameObjectId = pickingTexture.readPixel(x, y);
            activeGameobject = currentScene.getGameObject(gameObjectId);
        }
    }

    public void imgui() {
        if (activeGameobject != null) {
            ImGui.begin("Inspector");
            activeGameobject.imgui();
            ImGui.end();
        }
    }
}
