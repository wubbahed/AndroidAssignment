package co.inamoto.assignments.rockpaperscissors;

import android.app.Application;
import android.support.annotation.NonNull;

import co.inamoto.assignments.rockpaperscissors.model.ApplicationComponent;
import co.inamoto.assignments.rockpaperscissors.model.ApplicationModule;
import co.inamoto.assignments.rockpaperscissors.model.DaggerApplicationComponent;

/**
 * Created by willturnage on 6/7/17.
 */

public class RockScissorsPaperApplication extends Application {

    private static RockScissorsPaperApplication application;
    private ApplicationComponent applicationComponent;

    public static RockScissorsPaperApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();

    }

    @NonNull
    public ApplicationComponent component() {
        return applicationComponent;
    }

}
