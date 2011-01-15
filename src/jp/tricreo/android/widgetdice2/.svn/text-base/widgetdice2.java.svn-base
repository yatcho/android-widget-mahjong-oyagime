package jp.tricreo.android.widgetdice2;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

//ホームウィジェット
public class widgetdice2 extends AppWidgetProvider {
    //更新時に呼ばれる
    @Override
    public void onUpdate(Context context,
        AppWidgetManager appWidgetManager,int[] appWidgetIds) {
        //ホームウィジェットを処理するサービスの実行
        Intent intent=new Intent(context,WidgetControl2.class);
        context.startService(intent);
    }
}