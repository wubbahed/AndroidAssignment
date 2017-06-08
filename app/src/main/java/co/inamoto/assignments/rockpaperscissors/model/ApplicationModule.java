package co.inamoto.assignments.rockpaperscissors.model;

import android.app.AlarmManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Singleton;

import co.inamoto.assignments.rockpaperscissors.presenter.GamePresenter;
import co.inamoto.assignments.rockpaperscissors.presenter.LobbyPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by willturnage on 6/7/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(final @NonNull Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    FirebaseDatabase provideFirebaseDatabase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        return firebaseDatabase;
    }

    @Provides
    @Singleton
    FirebaseInstanceId provideFirebaseInstanceId(Context context) {
        FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();
        return firebaseInstanceId;
    }

    @Provides
    @Singleton
    GamePresenter provideGamePresenter(
            FirebaseDatabase firebaseDatabase,
            FirebaseInstanceId instanceId
    ) {
        return new GamePresenter(firebaseDatabase.getReference(), instanceId.getId());
    }

    @Provides
    @Singleton
    LobbyPresenter provideLobbyPresenter(
            FirebaseDatabase firebaseDatabase,
            FirebaseInstanceId instanceId
    ) {
        return new LobbyPresenter(firebaseDatabase.getReference(), instanceId.getId());
    }

}
