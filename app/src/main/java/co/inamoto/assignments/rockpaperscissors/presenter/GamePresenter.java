package co.inamoto.assignments.rockpaperscissors.presenter;

import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import co.inamoto.assignments.rockpaperscissors.R;
import co.inamoto.assignments.rockpaperscissors.view.GameActivity;

/**
 * Created by willturnage on 6/7/17.
 */

public class GamePresenter {

    private GameActivity activity;
    private DatabaseReference databaseReference;
    private DatabaseReference gameReference;

    private String instanceId;
    private String gameId;
    private String playerId;
    private String myMove;


    public GamePresenter(DatabaseReference ref, String iid) {
        databaseReference = ref;
        instanceId = iid;
        myMove = "";
        playerId = "";
    }

    public void onResume(GameActivity ga) {
        activity = ga;
        gameId = activity.getIntent().getStringExtra("gameId");

        gameReference = databaseReference.child("games").child(gameId);
        gameReference.addValueEventListener(gameListener);

    }

    public void rockClicked() {
        if (updateMyMove("rock")) {
            activity.setRockImage(R.drawable.rock);
        }
    }

    public void paperClicked() {
        if (updateMyMove("paper")) {
            activity.setPaperImage(R.drawable.paper);
        }
    }

    public void scissorsClicked() {
        if (updateMyMove("scissors")) {
            activity.setScissorsImage(R.drawable.scissors);
        }
    }


    private boolean updateMyMove(String move) {

        if ((gameReference != null) && (!playerId.contentEquals(""))) {
            if (myMove.contentEquals("")) {
                myMove = move;
                gameReference.child(playerId + "move").setValue(move);
                return true;
            }
        }
        return false;

    }


    // update the game status on the screen
    private void updateGame(String player1Move, String player2Move) {


        if (player1Move == null || player2Move == null) {

            // game isn't completed yet
            if (playerId.contentEquals("player1")) {

                if (player1Move == null) {
                    activity.setInstructionsString("Choose your move.");
                } else if (player1Move.contentEquals("")) {
                    activity.setInstructionsString("Choose your move.");
                } else {
                    activity.setInstructionsString("Waiting for other player...");
                }

            } else {

                if (player2Move == null) {
                    activity.setInstructionsString("Choose your move.");
                } else if (player2Move.contentEquals("")) {
                    activity.setInstructionsString("Choose your move.");
                } else {
                    activity.setInstructionsString("Waiting for other player...");
                }

            }

            activity.setResultString("");

        } else if (player1Move.contentEquals("") || player2Move.contentEquals("")) {

            // game isn't completed yet
            if (playerId.contentEquals("player1")) {

                if (player1Move == null) {
                    activity.setInstructionsString("Choose your move.");
                } else if (player1Move.contentEquals("")) {
                    activity.setInstructionsString("Choose your move.");
                } else {
                    activity.setInstructionsString("Waiting for other player...");
                }

            } else {

                if (player2Move == null) {
                    activity.setInstructionsString("Choose your move.");
                } else if (player2Move.contentEquals("")) {
                    activity.setInstructionsString("Choose your move.");
                } else {
                    activity.setInstructionsString("Waiting for other player...");
                }

            }

            activity.setResultString("");

        } else {

            // game is completed, show the results and set a timer to reset the game

            if (player1Move.contentEquals(player2Move)) {

                activity.setInstructionsString("They played " + player1Move + ".");
                activity.setResultString("TIE GAME");

            } else {

                if (playerId.contentEquals("player1")) {

                    activity.setInstructionsString("They played " + player2Move + ".");

                    if (player1Move.contentEquals("rock")) {

                        if (player2Move.contentEquals("paper")) {
                            activity.setResultString("YOU LOSE");
                        } else {
                            activity.setResultString("YOU WIN");
                        }

                    } else if (player1Move.contentEquals("paper")) {

                        if (player2Move.contentEquals("scissors")) {
                            activity.setResultString("YOU LOSE");
                        } else {
                            activity.setResultString("YOU WIN");
                        }

                    } else if (player1Move.contentEquals("scissors")) {

                        if (player2Move.contentEquals("rock")) {
                            activity.setResultString("YOU LOSE");
                        } else {
                            activity.setResultString("YOU WIN");
                        }

                    }


                } else {

                    activity.setInstructionsString("They played " + player1Move + ".");

                    if (player2Move.contentEquals("rock")) {

                        if (player1Move.contentEquals("paper")) {
                            activity.setResultString("YOU LOSE");
                        } else {
                            activity.setResultString("YOU WIN");
                        }

                    } else if (player2Move.contentEquals("paper")) {

                        if (player1Move.contentEquals("scissors")) {
                            activity.setResultString("YOU LOSE");
                        } else {
                            activity.setResultString("YOU WIN");
                        }

                    } else if (player2Move.contentEquals("scissors")) {

                        if (player1Move.contentEquals("rock")) {
                            activity.setResultString("YOU LOSE");
                        } else {
                            activity.setResultString("YOU WIN");
                        }

                    }


                }

            }


            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resetGame();
                }
            }, 3000);


        }

    }

    // reset the game
    private void resetGame() {

        myMove = "";
        activity.setInstructionsString("");
        activity.setResultString("");

        activity.setRockImage(R.drawable.rock_gray);
        activity.setPaperImage(R.drawable.paper_gray);
        activity.setScissorsImage(R.drawable.scissors_gray);

        gameReference.child(playerId + "move").setValue(myMove);

    }


    private ValueEventListener gameListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.getValue() != null) {

                if (instanceId.contentEquals((String) dataSnapshot.child("player1").getValue())) {
                    playerId = "player1";
                }

                if (instanceId.contentEquals((String) dataSnapshot.child("player2").getValue())) {
                    playerId = "player2";
                }

                String player1Move = null;
                String player2Move = null;

                if (dataSnapshot.hasChild("player1move")) {
                    player1Move = (String) dataSnapshot.child("player1move").getValue();
                }
                if (dataSnapshot.hasChild("player2move")) {
                    player2Move = (String) dataSnapshot.child("player2move").getValue();
                }

                updateGame(player1Move, player2Move);
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

    };

}
