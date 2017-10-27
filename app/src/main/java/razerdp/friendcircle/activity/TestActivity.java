package razerdp.friendcircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import razerdp.friendcircle.R;
import razerdp.github.com.baseuilib.baseadapter.BaseRecyclerViewAdapter;
import razerdp.github.com.baseuilib.baseadapter.BaseRecyclerViewHolder;
import razerdp.github.com.baseuilib.widget.common.ClickShowMoreLayout;

public class TestActivity extends AppCompatActivity {

    private RecyclerView rvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        final InnerAdapter adapter = new InnerAdapter(this, getTestDatas());

        rvContent.setAdapter(adapter);

        Button refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.updateData(getTestDatas());
            }
        });
    }

    private List<String> getTestDatas() {
        List<String> testData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 != 0) {
                testData.add("Almost heaven, West Virginia，\n" +
                        "西弗吉尼亚，近乎天堂般美妙\n" +
                        "Blue Ridge Mountains, Shenandoah River.\n" +
                        "蓝岭山脉，珊娜多荷河流\n" +
                        "Life is old there, older than the trees,\n" +
                        "那里的生命，比树木更古老\n" +
                        "Younger than the mountains, blowing like a breeze.\n" +
                        "比青山更有活力，像风一样自由\n" +
                        "Country road, take me home\n" +
                        "故乡的路，带我回家吧\n" +
                        "To the place I belong,\n" +
                        "回到我的家乡");
            } else {
                testData.add("短文字\n短文字\n短文字\n短文字\n短文字");
            }
        }
        return testData;
    }


    private class InnerAdapter extends BaseRecyclerViewAdapter<String> {

        public InnerAdapter(@NonNull Context context, @NonNull List<String> datas) {
            super(context, datas);
        }

        @Override
        protected int getViewType(int position, @NonNull String data) {
            return 0;
        }

        @Override
        protected int getLayoutResId(int viewType) {
            return R.layout.item_test;
        }

        @Override
        protected BaseRecyclerViewHolder getViewHolder(ViewGroup parent, View rootView, int viewType) {
            return new InnerViewHolder(rootView, viewType);
        }
    }

    private class InnerViewHolder extends BaseRecyclerViewHolder<String> {

        ClickShowMoreLayout clickShowMoreLayout;


        public InnerViewHolder(View itemView, int viewType) {
            super(itemView, viewType);
            clickShowMoreLayout = (ClickShowMoreLayout) findViewById(R.id.click_show_more_test);
            clickShowMoreLayout.setOnStateKeyGenerateListener(mOnStateKeyGenerateListener);
        }

        @Override
        public void onBindData(String data, int position) {
            clickShowMoreLayout.setText(data);
        }

        private ClickShowMoreLayout.OnStateKeyGenerateListener mOnStateKeyGenerateListener = new ClickShowMoreLayout.OnStateKeyGenerateListener() {
            @Override
            public int onStateKeyGenerated(int originKey) {
                KLog.i("originKey  >>  "+originKey);
                return originKey + getLayoutPosition();
            }
        };
    }
}
