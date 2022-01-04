import java.util.HashMap;

public class Test4 {

    public static final Test4 INSTANCE = new Test4();

    public static void get() {

    }

    public static Test4 getInstance() {
        System.out.println("getInstance");
        return INSTANCE;
    }


}
