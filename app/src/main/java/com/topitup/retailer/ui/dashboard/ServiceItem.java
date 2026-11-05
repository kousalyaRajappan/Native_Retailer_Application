package com.topitup.retailer.ui.dashboard;

import com.topitup.retailer.R;

public class ServiceItem {
    public final String title;
    public final String subtitle;
    public final int iconResource;
    public final int iconBackgroundColor;
    public final int cardBackgroundColor;
    public final int titleColor;
    public final int subtitleColor;
    public final int destination;

    public ServiceItem(String title, String subtitle, int iconResource, int iconBackgroundColor,
                       int destination) {
        this(title, subtitle, iconResource, iconBackgroundColor, R.color.card,
                R.color.on_surface, R.color.dashboard_secondary, destination);
    }

    public ServiceItem(String title, String subtitle, int iconResource, int iconBackgroundColor,
                       int cardBackgroundColor, int titleColor, int subtitleColor, int destination) {
        this.title = title;
        this.subtitle = subtitle;
        this.iconResource = iconResource;
        this.iconBackgroundColor = iconBackgroundColor;
        this.cardBackgroundColor = cardBackgroundColor;
        this.titleColor = titleColor;
        this.subtitleColor = subtitleColor;
        this.destination = destination;
    }
}
