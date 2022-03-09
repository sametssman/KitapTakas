package adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sametsisman.kitaptakas.databinding.RecyclerviewRowBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;

import model.Ilan;

public class KitapAdapter extends RecyclerView.Adapter<KitapAdapter.KitapHolder> {
    private ArrayList<Ilan> ilanArrayList;

    public KitapAdapter(ArrayList<Ilan> ilanArrayList) {
        this.ilanArrayList = ilanArrayList;
    }

    @NonNull
    @Override
    public KitapHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewRowBinding binding = RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new KitapHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull KitapHolder holder, int position) {
        holder.binding.textViewkitap.setText(ilanArrayList.get(position).kitapIsmi);
        holder.binding.textViewYazar.setText(ilanArrayList.get(position).yazarIsmi);
        holder.binding.textViewoptional.setText(ilanArrayList.get(position).optional);
        Picasso.get().load(ilanArrayList.get(position).image).resize(0,400).into(holder.binding.imageViewRow);
    }

    @Override
    public int getItemCount() {
        return ilanArrayList.size();
    }

    public class KitapHolder extends RecyclerView.ViewHolder{
        RecyclerviewRowBinding binding;
        public KitapHolder(@NonNull RecyclerviewRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
