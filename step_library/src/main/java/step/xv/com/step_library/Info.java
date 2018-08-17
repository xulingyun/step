package step.xv.com.step_library;

/**
 * Created by Cantaloupe--郓 on 2018/8/14.
 * 描述：
 */
public class Info {

    public static final int STEP_UNDO = -1;//未完成  undo step
    public static final int STEP_COMPLETEING = 0;//正在进行 current step
    public static final int STEP_COMPLETED = 1;//已完成 completed step

    String name;
    int state;

    public Info() {
    }

    public Info(String $Name, int $State) {
        name = $Name;
        state = $State;
    }

    public String getName() {
        return name;
    }

    public void setName(String $Name) {
        name = $Name;
    }

    public int getState() {
        return state;
    }

    public void setState(int $State) {
        state = $State;
    }
}
