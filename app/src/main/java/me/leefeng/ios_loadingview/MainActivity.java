package me.leefeng.ios_loadingview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LoadingView load = (LoadingView) findViewById(R.id.load);
        Switch main_switch = (Switch) findViewById(R.id.main_switch);
        main_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    load.startLoad();
//                }else{
//                    load.stopLoad();
//                }
            }
        });
    }
}
