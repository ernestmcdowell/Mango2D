package Physics2Dtmp.rigidbody;

import Physics2Dtmp.primitives.AABB2D;
import Physics2Dtmp.primitives.Box2D;
import Physics2Dtmp.primitives.Circle;
import org.joml.Vector2f;
import renderer.Line2D;
import util.MMath;

public class IntersectionDetector2D {

    public static boolean pointOnLine(Vector2f point, Line2D line){
        float dy = line.getEnd().y - line.getStart().y;
        float dx = line.getEnd().x - line.getStart().x;
        float m = dy / dx;

        float b = line.getEnd().y - (m * line.getEnd().x);



        return point.y == m * point.x + b;
    }

    public static boolean pointInCircle(Vector2f point, Circle circle){
        Vector2f circleCenter = circle.getCenter();
        Vector2f centerToPoint = new Vector2f(point).sub(circleCenter);

        return centerToPoint.lengthSquared() <= circle.getRadius() * circle.getRadius(); // not squaring save about 100 cpu cycles
    }

    public static boolean pointInAABB(Vector2f point, AABB2D box){
        Vector2f min = box.getMin();
        Vector2f max = box.getMax();

        return point.x <= max.x && min.x <= point.x && point.y <= max.y && min.y <= point.y;
    }

    public static boolean pointInBox2D(Vector2f point, Box2D box){
        // translate the point to local spcae
        Vector2f pointLocalBoxSpace = new Vector2f(point);
        MMath.rotate(pointLocalBoxSpace, box.getRigidbody().getRotation(),
                box.getRigidbody().getPosition());

        Vector2f min = box.getMin();
        Vector2f max = box.getMax();

        return pointLocalBoxSpace.x <= max.x && min.x <= pointLocalBoxSpace.x &&
                pointLocalBoxSpace.y <= max.y && min.y <= pointLocalBoxSpace.y;

    }


}
