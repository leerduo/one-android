package me.biubiubiu.one.ui;

import me.biubiubiu.one.BootstrapApplication;
import me.biubiubiu.one.R;
import me.biubiubiu.one.util.HttpHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.actionbarsherlock.view.MenuItem;
import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.model.ImageTagFactory;

public class BaseActivity extends BootstrapFragmentActivity {

    protected HttpHandler mHttpHandler;
    protected ImageTagFactory imageTagFactory;
    protected ImageManager imageManager;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mHttpHandler = new HttpHandler(this);
        imageManager = BootstrapApplication.getImageLoader();
        imageTagFactory = ImageTagFactory.newInstance(this, R.drawable.transparent);
        imageTagFactory.setAnimation(R.anim.fade_in);
        imageTagFactory.setSaveThumbnail(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void setActionBarTitle(String title) {
        this.getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isFinished() {
        return this == null;
    }

    public void loadPhoto(ImageView view, String url) {
        view.setTag(imageTagFactory.build(url, this));
        imageManager.getLoader().load(view);
    }
    protected void addContainerFragment(Fragment frag) {
        getSupportFragmentManager()
            .beginTransaction().add(R.id.container, frag).commit();
    }

    public void startActivityClass(Class cls) {
        Intent intent  = new Intent(this, cls);
        startActivity(intent);
    }

    protected BootstrapApplication getMyApp() {
        return (BootstrapApplication)getApplication();
    }

    protected String getUserId() {
        return "52a468d91d24ead09274284d";
    }

}
