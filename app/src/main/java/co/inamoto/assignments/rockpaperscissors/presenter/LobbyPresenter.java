package co.inamoto.assignments.rockpaperscissors.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.inamoto.assignments.rockpaperscissors.view.GameActivity;
import co.inamoto.assignments.rockpaperscissors.view.LobbyActivity;

/**
 * Created by willturnage on 6/7/17.
 */

public class LobbyPresenter {

    private String instanceId;
    private DatabaseReference lobbyReference;
    private ArrayList<String> lobbyDevices;
    private ArrayAdapter<String> lobbyDevicesAdapter;

    private LobbyActivity activity;


    public LobbyPresenter(DatabaseReference ref, String iid) {

        instanceId = iid;
        lobbyDevices = new ArrayList<String>();

        lobbyReference = ref.child("lobby");
        lobbyReference.addValueEventListener(lobbyListener);

    }


    public void onResume(LobbyActivity la) {
        activity = la;

        lobbyReference.child(instanceId).setValue(true);
        lobbyDevicesAdapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, lobbyDevices);
        activity.deviceList.setAdapter(lobbyDevicesAdapter);

    }


    public void chooseDevice(int position) {

        // get game partner
        String partnerInstanceId = lobbyDevices.get(position);

        Log.i("chooseDevice", position + " " + partnerInstanceId);

        // setup the game
        DatabaseReference gameReference = lobbyReference.getRoot().child("games");
        String gameId = gameReference.push().getKey();
        gameReference.child(gameId).child("player1").setValue(instanceId);
        gameReference.child(gameId).child("player2").setValue(partnerInstanceId);

        // set the gameId in the lobby
        lobbyReference.child(instanceId).setValue(gameId);
        lobbyReference.child(partnerInstanceId).setValue(gameId);

    }


    public void roomChosen(String gameId) {
        Intent i = new Intent(activity, GameActivity.class);
        i.putExtra("gameId", gameId);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(i);
    }


    private ValueEventListener lobbyListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.getValue() != null) {

                lobbyDevices.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getValue() instanceof Boolean) {
                        if (!instanceId.contentEquals(child.getKey())) {
                            lobbyDevices.add(child.getKey());
                        }
                    }
                    if (child.getKey().contentEquals(instanceId)) {
                        if (child.getValue() instanceof String) {
                            roomChosen((String) child.getValue());
                        }
                    }
                }

                if (lobbyDevicesAdapter != null) {
                    lobbyDevicesAdapter.notifyDataSetChanged();
                }

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

    };


}
