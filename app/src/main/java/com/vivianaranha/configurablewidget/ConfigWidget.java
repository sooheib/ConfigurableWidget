package com.vivianaranha.configurablewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link ConfigWidgetConfigureActivity ConfigWidgetConfigureActivity}
 */
public class ConfigWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int i = 0;i<appWidgetIds.length; i++){
            int appWidgetId = appWidgetIds[i];
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vivianaranha.com"));
            PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.config_widget);
            views.setOnClickPendingIntent(R.id.imageButton, pending);

            appWidgetManager.updateAppWidget(appWidgetId, views);


        }
    }


}

