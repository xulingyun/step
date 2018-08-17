package step.xv.com.step;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import step.xv.com.step_library.Info;
import step.xv.com.step_library.StepsViewIndicator;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    StepsViewIndicator hsvi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hsvi = findViewById(R.id.hsvi);
        ArrayList<Info> list = new ArrayList<>();
        list.add(new Info("能优习惯", Info.STEP_COMPLETED));
        list.add(new Info("化策",Info.STEP_COMPLETED));
        list.add(new Info("编码",Info.STEP_COMPLETEING));
        list.add(new Info("完成",Info.STEP_UNDO));
        list.add(new Info("完成1",Info.STEP_UNDO));
        hsvi.setInfoArrayList(list);

    }

    public void ff(View view) {
        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);
        BitmapDrawable bb = new BitmapDrawable(getResources(),b);
        hsvi.setCompleteingIcon(bb);
    }
}
