package master.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import com.master.deviceinfo.R;
import com.master.deviceinfo.models.DeviceInfoModel;
import master.utils.AppConst;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


public class SplashActivity extends AppCompatActivity {


    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mHandler.postDelayed(mPendingLauncherRunnable, 2000);


    }


    private final Runnable mPendingLauncherRunnable = new Runnable() {
        @Override
        public void run() {

            getAppsList();

            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();

        }
    };


    public void getAppsList() {

        AppConst.systemAppsList.clear();
        AppConst.userAppsList.clear();
        int flags = PackageManager.GET_META_DATA | PackageManager.GET_SHARED_LIBRARY_FILES;
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> applications = pm.getInstalledApplications(flags);

        for (ApplicationInfo appInfo : applications) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                // System application
                Drawable icon = pm.getApplicationIcon(appInfo);
                AppConst.systemAppsList.add(new DeviceInfoModel(1, icon, pm.getApplicationLabel(appInfo).toString(), appInfo.packageName));

            } else {
                Drawable icon = pm.getApplicationIcon(appInfo);
                AppConst.userAppsList.add(new DeviceInfoModel(2, icon, pm.getApplicationLabel(appInfo).toString(), appInfo.packageName));

            }

        }


    }


}



