package co.inamoto.assignments.rockpaperscissors.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import co.inamoto.assignments.rockpaperscissors.R;
import co.inamoto.assignments.rockpaperscissors.RockScissorsPaperApplication;
import co.inamoto.assignments.rockpaperscissors.presenter.LobbyPresenter;

/**
 * Created by willturnage on 6/7/17.
 */

public class LobbyActivity extends AppCompatActivity {

    @Inject
    LobbyPresenter presenter;

    @BindView(R.id.devices_in_lobby)
    public ListView deviceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RockScissorsPaperApplication.getApplication().component().inject(this);
        setContentView(R.layout.lobby);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(this);
    }

    @OnItemClick(R.id.devices_in_lobby)
    public void deviceClicked(ListView parent, int position) {
        Log.i("deviceClicked", parent + " " + position);
        presenter.chooseDevice(position);
    }


}
