package editor;

import imgui.ImGui;
import imgui.ImGuiOnceUponAFrame;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiStyleVar;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class MImGui {

    private static float deafaultColumnWidth = 220.0f;

    public static void drawVec2Control(String label, Vector2f values){
        drawVec2Control(label, values, 0.0f, deafaultColumnWidth);
    }

    public static void drawVec2Control(String label, Vector2f values, float resetvalue){
        drawVec2Control(label, values, resetvalue, deafaultColumnWidth);

    }

    public static void drawVec2Control(String label, Vector2f values, float resetValue, float columnWidth){
        ImGui.pushID(label);

        ImGui.columns(2);
        ImGui.setColumnWidth(0, columnWidth);
        ImGui.text(label);
        ImGui.nextColumn();

        ImGui.pushStyleVar(ImGuiStyleVar.ItemSpacing, 0, 0);

        float lineHeight = ImGui.getFontSize() + ImGui.getStyle().getFramePaddingY() * 2.0f;
        Vector2f buttonSize = new Vector2f(lineHeight + 3.0f, lineHeight);
        float widthEach = (ImGui.calcItemWidth() - buttonSize.x * 2.0f) / 2.0f;

        ImGui.pushItemWidth(widthEach);
        ImGui.pushStyleColor(ImGuiCol.Button, 0.8f, 0.1f, 0.15f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.Button, 0.8f, 0.3f, 0.3f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.Button, 0.7f, 0.2f, 0.2f, 1.0f);
        if(ImGui.button("X", buttonSize.x, buttonSize.y)){
            values.x = resetValue;
        }
        ImGui.popStyleColor(3);

        ImGui.sameLine();
        float[] vecValuesX = {values.x};
        ImGui.dragFloat("##X", vecValuesX, 0.1f);
        ImGui.popItemWidth();
        ImGui.sameLine();


        ImGui.pushItemWidth(widthEach);
        ImGui.pushStyleColor(ImGuiCol.Button, 0.1f, 0.8f, 0.1f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.Button, 0.1f, 0.9f, 0.1f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.Button, 0.1f, 0.8f, 0.1f, 1.0f);
        if(ImGui.button("Y", buttonSize.x, buttonSize.y)){
            values.y = resetValue;
        }
        ImGui.popStyleColor(3);

        ImGui.sameLine();
        float[] vecValuesY = {values.y};
        ImGui.dragFloat("##Y", vecValuesY, 0.1f);
        ImGui.popItemWidth();
        ImGui.sameLine();

        ImGui.nextColumn();

        values.x = vecValuesX[0];
        values.y = vecValuesY[0];


        ImGui.popStyleVar();
        ImGui.columns(1);
        ImGui.popID();
    }

    public static float dragFloat(String label, float values){
        ImGui.pushID(label);

        ImGui.columns(2);
        ImGui.setColumnWidth(0, deafaultColumnWidth);
        ImGui.text(label);
        ImGui.nextColumn();

        float[] valArray = {values};
        ImGui.dragFloat("##dragFloat", valArray, 0.1f);

        ImGui.columns(1);
        ImGui.popID();

        return valArray[0];



    }

    public static int dragInt(String label, int values){
        ImGui.pushID(label);

        ImGui.columns(2);
        ImGui.setColumnWidth(0, deafaultColumnWidth);
        ImGui.text(label);
        ImGui.nextColumn();

        int[] valArray = {values};
        ImGui.dragInt("##dragInt", valArray, 0.1f);

        ImGui.columns(1);
        ImGui.popID();

        return valArray[0];



    }

    public static boolean colorPicker(String label, Vector4f color){
        boolean res = false;
        ImGui.pushID(label);

        ImGui.columns(2);
        ImGui.setColumnWidth(0, deafaultColumnWidth);
        ImGui.text(label);
        ImGui.nextColumn();

        float[] imColor = {color.x, color.y, color.z, color.w};
        if(ImGui.colorEdit4(label, imColor)){
            color.set(imColor[0], imColor[1], imColor[2], imColor[3]);
            res = true;
        }

        ImGui.columns(1);
        ImGui.popID();
        return res;

    }
}
