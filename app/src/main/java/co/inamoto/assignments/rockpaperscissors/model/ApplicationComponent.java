package co.inamoto.assignments.rockpaperscissors.model;

import android.content.Context;

import javax.inject.Singleton;

import co.inamoto.assignments.rockpaperscissors.presenter.GamePresenter;
import co.inamoto.assignments.rockpaperscissors.presenter.LobbyPresenter;
import co.inamoto.assignments.rockpaperscissors.view.GameActivity;
import co.inamoto.assignments.rockpaperscissors.view.LobbyActivity;
import dagger.Component;

/**
 * Created by willturnage on 6/7/17.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class
})

public interface ApplicationComponent {

    void inject(LobbyActivity __);
    void inject(GameActivity __);

    void inject(LobbyPresenter __);
    void inject(GamePresenter __);

}
