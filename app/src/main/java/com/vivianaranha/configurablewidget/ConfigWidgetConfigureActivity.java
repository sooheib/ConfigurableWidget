package com.vivianaranha.configurablewidget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

/**
 * The configuration screen for the {@link ConfigWidget ConfigWidget} AppWidget.
 */
public class ConfigWidgetConfigureActivity extends Activity {

    int widgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.config_widget_configure);

        setResult(RESULT_CANCELED);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        }

        final AppWidgetManager widgetManager = AppWidgetManager.getInstance(this);

        final RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.config_widget);

        final EditText et = (EditText) findViewById(R.id.editText);
        Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(et.getText().toString()));
                PendingIntent pending = PendingIntent.getActivity(ConfigWidgetConfigureActivity.this, 0, intent, 0);

                views.setOnClickPendingIntent(R.id.imageButton, pending);


                widgetManager.updateAppWidget(widgetId, views);

                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                setResult(RESULT_OK, resultValue);
                finish();

            }
        });
    }
}

