package co.inamoto.assignments.rockpaperscissors.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import co.inamoto.assignments.rockpaperscissors.R;
import co.inamoto.assignments.rockpaperscissors.RockScissorsPaperApplication;
import co.inamoto.assignments.rockpaperscissors.presenter.GamePresenter;

public class GameActivity extends AppCompatActivity {


    @Inject
    GamePresenter presenter;

    @BindView(R.id.rock_button)
    ImageButton rockButton;

    @BindView(R.id.paper_button)
    ImageButton paperButton;

    @BindView(R.id.scissors_button)
    ImageButton scissorsButton;

    @BindView(R.id.instructions)
    TextView instructions;

    @BindView(R.id.result)
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RockScissorsPaperApplication.getApplication().component().inject(this);
        setContentView(R.layout.game);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(this);
    }

    @OnClick(R.id.rock_button)
    public void rockClicked() {
        presenter.rockClicked();
    }

    @OnClick(R.id.paper_button)
    public void paperClicked() {
        presenter.paperClicked();
    }

    @OnClick(R.id.scissors_button)
    public void scissorsClicked() {
        presenter.scissorsClicked();
    }

    public void setRockImage(int resourceId) {
        rockButton.setImageResource(resourceId);
    }

    public void setPaperImage(int resourceId) {
        paperButton.setImageResource(resourceId);
    }

    public void setScissorsImage(int resourceId) {
        scissorsButton.setImageResource(resourceId);
    }

    public void setResultString(String text) {
        result.setText(text);
    }

    public void setInstructionsString(String text) {
        instructions.setText(text);
    }

}
