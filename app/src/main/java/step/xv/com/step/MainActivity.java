package step.xv.com.step;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import step.xv.com.step_library.Info;
import step.xv.com.step_library.StepsViewIndicator;

public class MainActivity extends AppCompatActivity {

    StepsViewIndicator hsvi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hsvi = findViewById(R.id.hsvi);
        ArrayList<Info> list = new ArrayList<>();
        list.add(new Info("能优化策略十分重要。因为我认为预防永远比治愈有意义重要得多。我们不应该等到一个问题已经发生了，并且到了一定程度才想起来需要重构代码或者进行性能优化，通过早早的学习性能优化的思维和工具能避免很多问题，纠正一些不良的编码习惯", Info.STEP_COMPLETED));
        list.add(new Info("能优化策略十分重要。因为我认为预防永远比治愈有意义重要得多。我们不应该等到一个问题已经发生了，并且到了一定程度才想起来需要重构代码或者进行性能优化，通过早早的学习性能优化的思维和工具能避免很多问题，纠正一些不良的编码习惯",Info.STEP_COMPLETED));
        list.add(new Info("编码能力提高具有很大的意义",Info.STEP_COMPLETEING));
        list.add(new Info("完成",Info.STEP_UNDO));
        list.add(new Info("完成1",Info.STEP_UNDO));
        list.add(new Info("完成2",Info.STEP_UNDO));
        list.add(new Info("完成3",Info.STEP_UNDO));
        list.add(new Info("完成4",Info.STEP_UNDO));
        list.add(new Info("完成5",Info.STEP_UNDO));
        list.add(new Info("完成6",Info.STEP_UNDO));
        hsvi.setInfoArrayList(list);
    }
}
