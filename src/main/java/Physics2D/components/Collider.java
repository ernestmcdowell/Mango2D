package Physics2D.components;

import components.Component;
import org.joml.Vector2f;

public abstract class Collider extends Component {

    private Vector2f offset = new Vector2f();



    public Vector2f getOffset() {
        return offset;
    }
}
