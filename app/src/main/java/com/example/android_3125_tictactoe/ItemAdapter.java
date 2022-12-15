package com.example.android_3125_tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_3125_tictactoe.databinding.ItemGridviewBinding;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<TictactoeObj> {

    public boolean playerOneTurn = true;

    public ItemAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        return TictactoeObj.list.size() - 1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemGridviewBinding binding = ItemGridviewBinding.inflate(
                LayoutInflater.from(getContext()),parent,false
        );
        TictactoeObj model = TictactoeObj.list.get(position);
        switch (model.getPlayer()){
            case 1:
                binding.image.setImageResource(R.drawable.x);
                break;
            case 2:
                binding.image.setImageResource(R.drawable.o);
                break;
            default:
                binding.image.setImageDrawable(null);
                break;
        }
        return binding.getRoot();
    }
}
