import com.google.gson.JsonObject;

public class vector3 {

    float x;
    float y;
    float z;

    public vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "vector3{" +
                "x:" + x +
                ", y:" + y +
                ", z:" + z +
                '}';
    }

    public JsonObject toJSON() {
        JsonObject json = new JsonObject();

        json.addProperty("x", x);
        json.addProperty("y", y);
        json.addProperty("z", z);

        return json;
    }
}
