package com.shanan.bplaces;

import android.app.Application;
import android.content.Context;

import com.shanan.bplaces.di.component.AppComponent;
import com.shanan.bplaces.di.component.DaggerAppComponent;
import com.shanan.bplaces.di.modules.AppModule;

/**
 * Created by Shanan on 23/09/2017.
 */

public class App extends Application {

    AppComponent appComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
