package com.topitup.retailer.ui.dashboard;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.topitup.retailer.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.Holder> {
    private final List<ServiceItem> items;
    private final NavController navController;

    public ServiceAdapter(List<ServiceItem> items, NavController navController) {
        this.items = items;
        this.navController = navController;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ServiceItem item = items.get(position);
        holder.title.setText(item.title);
        holder.subtitle.setText(item.subtitle);
        holder.icon.setImageResource(item.iconResource);
        holder.iconContainer.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(holder.itemView.getContext(), item.iconBackgroundColor)));
        holder.card.setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.getContext(), item.cardBackgroundColor));
        holder.title.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), item.titleColor));
        holder.subtitle.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), item.subtitleColor));
        holder.card.setOnClickListener(view -> navController.navigate(item.destination));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        final MaterialCardView card;
        final FrameLayout iconContainer;
        final ImageView icon;
        final TextView title;
        final TextView subtitle;

        Holder(View view) {
            super(view);
            card = (MaterialCardView) view.findViewById(R.id.service_card);
            iconContainer = view.findViewById(R.id.service_icon_container);
            icon = view.findViewById(R.id.service_icon);
            title = view.findViewById(R.id.service_title);
            subtitle = view.findViewById(R.id.service_subtitle);
        }
    }
}
