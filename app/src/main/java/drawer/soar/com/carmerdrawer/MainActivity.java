package drawer.soar.com.carmerdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import drawer.soar.com.view.DrawLayout;
import drawer.soar.com.view.ItemOnClick;
import drawer.soar.com.view.ListAttr;
import drawer.soar.com.view.MenuDrawable;
import drawer.soar.com.view.ModeListView;

/**
 * ----------------------------------------------------
 * ※ Author :
 * ※ Date : 2018/4/28
 * ※ Time : 11:53
 * ※ Project : CarmerDrawer
 * ※ Package : drawer.soar.com.carmerdrawer
 * ----------------------------------------------------
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MenuDrawable mMenuDrawable = findViewById(R.id.drawer_camera);
        ModeListView modeList = findViewById(R.id.mode_listview);
        LinearLayout modelistparent = findViewById(R.id.modelistparent);

        List<ListAttr> data = new ArrayList<>();
        ListAttr attr1 = new ListAttr(1 , "test1放大" , R.mipmap.mode_hdrs_normal ,R.mipmap.mode_hdrs_pressed ,R.mipmap.mode_hdrs_selected ,true , true);
        ListAttr attr2 = new ListAttr(2 , "test2法大师傅的" , R.mipmap.mode_face_normal ,R.mipmap.mode_face_pressed ,R.mipmap.mode_face_selected ,true , false);
        ListAttr attr3 = new ListAttr(3 , "test3放大" , R.mipmap.mode_manuals_normal,R.mipmap.mode_manuals_pressed ,R.mipmap.mode_manuals_selected  ,true , false);
        ListAttr attr4 = new ListAttr(4 , "test4光辉打" , R.mipmap.mode_panoramas_normal ,R.mipmap.mode_panoramas_pressed ,R.mipmap.mode_panoramas_selected  ,true , false);
        ListAttr attr5 = new ListAttr(5 , "test5规划的" , R.mipmap.mode_photo_normal ,R.mipmap.mode_photo_pressed ,R.mipmap.mode_photo_selected  ,true , false);
        ListAttr attr6 = new ListAttr(6 , "test6该放手还是" , R.mipmap.mode_scan_normal ,R.mipmap.mode_scan_pressed ,R.mipmap.mode_scan_selected  ,true , false);
        ListAttr attr7 = new ListAttr(7 , "test7用坏了" , R.mipmap.mode_time_normal ,R.mipmap.mode_time_pressed ,R.mipmap.mode_time_selected  ,false , false);

        data.add(attr1);
        data.add(attr2);
        data.add(attr3);
        data.add(attr4);
        data.add(attr5);
        data.add(attr6);
        data.add(attr7);

        modeList.setData(data);

        DrawLayout mDrawLayout = findViewById(R.id.drawer_linear);
        mDrawLayout.setModeListView(modeList, modelistparent);
        mDrawLayout.setDrawLayoutCall(new ItemOnClick() {
            @Override
            public void onItemClick(int position, String text) {
                Toast.makeText(MainActivity.this , position+"----->"+text , Toast.LENGTH_SHORT).show();
            }
        });
        mDrawLayout.setMenuDrawable(mMenuDrawable);
        mMenuDrawable.setDrawerLayout(this, mDrawLayout, modeList);
    }
}
