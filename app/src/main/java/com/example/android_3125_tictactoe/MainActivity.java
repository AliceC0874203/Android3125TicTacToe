package com.example.android_3125_tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.android_3125_tictactoe.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ItemAdapter adapter;
    boolean isGameFinished = false;
    boolean isGameRunning = false;
    int[][] winPositions =
            {{0, 1, 2}, {3, 4, 5},
                    {6, 7, 8}, {0, 3, 6},
                    {1, 4, 7}, {2, 5, 8},
                    {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        resetGame();

        adapter = new ItemAdapter(this, R.layout.item_gridview);
        binding.gridView.setAdapter(adapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!isGameRunning || isGameFinished){
                    Snackbar.make(binding.getRoot(),"Press Start to play.",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                TictactoeObj model = TictactoeObj.list.get(position);
                if(model.getPlayer() != 0){
                    Snackbar.make(binding.getRoot(),"Choose next.",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                model.setPlayer( adapter.playerOneTurn ? 1 : 2 );
                TictactoeObj.list.set(position,model);
                adapter.playerOneTurn = !adapter.playerOneTurn;
                adapter.notifyDataSetChanged();
                if(checkWinner()){
                    isGameFinished = true;
                    return;
                }else{
                    updatePlayerTurn();
                }
            }
        });

        binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                binding.start.setVisibility(View.INVISIBLE);
                isGameRunning = true;
                isGameFinished = false;
                adapter.playerOneTurn = true;
                adapter.notifyDataSetChanged();
                updatePlayerTurn();
            }
        });
    }

    private void resetGame() {
        TictactoeObj.list.clear();
        for (int i = 0; i < 10; i++) {
            TictactoeObj.list.add(new TictactoeObj(i, 0));
        }
    }

    private void updatePlayerTurn() {
        binding.turnTxt.setText("Player " + (adapter.playerOneTurn ? 1 : 2) + " Turn.");
    }

    private boolean checkWinner() {
        ArrayList<TictactoeObj> list = TictactoeObj.list;
        for (int[] winPosition : winPositions) {
            if (
                    list.get(winPosition[0]).getPlayer() == list.get(winPosition[1]).getPlayer()
                            &&
                            list.get(winPosition[1]).getPlayer() == list.get(winPosition[2]).getPlayer()
                            &&
                            list.get(winPosition[0]).getPlayer() != 0
            ) {
                isGameRunning = false;
                isGameFinished = true;
                int winPlayer = list.get(winPosition[0]).getPlayer();
                String win = "Player " + winPlayer + " won the game";
                binding.turnTxt.setText(win);
                binding.start.setVisibility(View.VISIBLE);
                Snackbar.make(binding.getRoot(), win, Snackbar.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }
}