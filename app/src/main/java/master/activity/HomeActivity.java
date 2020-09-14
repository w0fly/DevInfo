package master.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.master.deviceinfo.R;
import com.master.deviceinfo.fragments.*;
import com.master.deviceinfo.models.DeviceInfoModel;
import master.adapters.MenuAdapter;
import master.fragments.*;
import master.utils.*;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();


    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;

    @BindView(R.id.txtTile)
    TextView txtTile;

    @BindView(R.id.rlMenu)
    RelativeLayout rlMenu;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BottomSheetBehavior sheetBehavior;

    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        initUI();

    }

    private void initUI() {


        MenuAdapter adapter = new MenuAdapter(HomeActivity.this, MenuList.getMenuList(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String value) {
                // TODO Auto-generated method stub

                selectedButton(value);
                toggleBottomSheet();

            }
        });


        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        selectedButton(getResources().getString(R.string.device));

    }



    /**
     * manually opening / closing bottom sheet on button click
     */
    @OnClick(R.id.rlMenu)
    public void rlMenu() {
        toggleBottomSheet();
    }


    public void toggleBottomSheet() {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        }
    }


    public void selectedButton(String selectedValue) {
        txtTile.setText(selectedValue);
        if (getResources().getString(R.string.device).equals(selectedValue)) {
            replicasFragments(new HomesFragment());
            rlMenu.setBackgroundResource(R.color.colorPrimary);
        }else if (getResources().getString(R.string.mobile_info).equals(selectedValue)) {
            replicasFragments(new DeviceInfoFragment());
            rlMenu.setBackgroundResource(R.color.colorPrimary);
        } else if (getResources().getString(R.string.os).equals(selectedValue)) {
            replicasFragments(new OSFragment());
            rlMenu.setBackgroundResource(R.color.dark_blue);
        } else if (getResources().getString(R.string.sensor).equals(selectedValue)) {
            replicasFragments(new SensorCategoryFragment());
            rlMenu.setBackgroundResource(R.color.dark_purple);
        } else if (getResources().getString(R.string.processor_label).equals(selectedValue)) {
            replicasFragments(new CPUFragment());
            rlMenu.setBackgroundResource(R.color.dark_violet);
        } else if (getResources().getString(R.string.battery).equals(selectedValue)) {
            replicasFragments(new BatteryFragment());
            rlMenu.setBackgroundResource(R.color.dark_green);
        } else if (getResources().getString(R.string.network).equals(selectedValue)) {
            replicasFragments(new NetworkFragment());
            rlMenu.setBackgroundResource(R.color.dark_sky_blue);
        } else if (getResources().getString(R.string.sim).equals(selectedValue)) {
            replicasFragments(new SimFragment());
            rlMenu.setBackgroundResource(R.color.dark_parrot_green);
        } else if (getResources().getString(R.string.camera).equals(selectedValue)) {
            replicasFragments(new CameraFragment());
            rlMenu.setBackgroundResource(R.color.dark_green_blue);
        } else if (getResources().getString(R.string.storage).equals(selectedValue)) {
            replicasFragments(new StorageFragment());
            rlMenu.setBackgroundResource(R.color.dark_red);
        } else if (getResources().getString(R.string.bluetooth).equals(selectedValue)) {
            replicasFragments(new BlueToothFragment());
            rlMenu.setBackgroundResource(R.color.dark_blue_one);
        } else if (getResources().getString(R.string.display).equals(selectedValue)) {
            replicasFragments(new DisplayFragment());
            rlMenu.setBackgroundResource(R.color.dark_violet_one);
        } else if (getResources().getString(R.string.features).equals(selectedValue)) {
            replicasFragments(new PhoneFeaturesFragment());

            rlMenu.setBackgroundResource(R.color.dark_brown);
        } else if (getResources().getString(R.string.user_apps).equals(selectedValue)) {
            replicasFragments(new UserAppsFragment());
            rlMenu.setBackgroundResource(R.color.dark_parrot_green_blue);
        } else if (getResources().getString(R.string.system_apps).equals(selectedValue)) {
            replicasFragments(new SystemAppsFragment());
            rlMenu.setBackgroundResource(R.color.dark_parrot_green_blue);
        } else if (getResources().getString(R.string.about_us).equals(selectedValue)) {
            replicasFragments(new AboutUsFragment());
            rlMenu.setBackgroundResource(R.color.colorPrimaryDark);

        } else if (getResources().getString(R.string.share).equals(selectedValue)) {
            Methods.sharing("https://play.google.com/store/apps/details?id=com.master.deviceinfo");
        }


        /*mInterstitial Ad*/

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());



        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });



    }


    public void replicasFragments(Fragment fragment) {


        FragmentTransaction transaction;
        transaction = ((FragmentActivity) this).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commit();
    }








}
