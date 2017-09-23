package com.shanan.bplaces.di.component;

import com.shanan.bplaces.di.modules.AppModule;
import com.shanan.bplaces.ui.base.BaseActivity;
import com.shanan.bplaces.ui.base.BaseSupportFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shanan on 23/09/2017.
 */

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(BaseSupportFragment baseFragment);
}
