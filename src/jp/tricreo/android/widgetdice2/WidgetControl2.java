package jp.tricreo.android.widgetdice2;
import java.util.*;
import android.app.*;
import android.appwidget.AppWidgetManager;
import android.content.*;
import android.os.IBinder;
import android.widget.RemoteViews;

//ホームウィジェットを制御するサービス
public class WidgetControl2 extends Service {
    private static final String ACTION_BTNCLICK =
        "jp.tricreo.WidgetControl2.ACTION_BTNCLICK";

    //サービス開始時に呼ばれる
    @Override
    public void onStart(Intent intent,int startId) {
        super.onStart(intent, startId);

        //リモートビューの取得
        AppWidgetManager manager=AppWidgetManager.getInstance(this);
        RemoteViews view=new RemoteViews(getPackageName(),R.layout.dicelayout);
        //ボタンが押された時
        if (ACTION_BTNCLICK.equals(intent.getAction())) {
        	btnClicked(view);
        }

        //button1,button2とボタンクリックイベントの関連付け
        Intent newintent=new Intent();
        newintent.setAction(ACTION_BTNCLICK);
        PendingIntent pendingintentbutton1=PendingIntent.getService(this,0,newintent,0);
        view.setOnClickPendingIntent(R.id.button1,pendingintentbutton1);

        //ホームウィジェットの更新
        ComponentName widget=new ComponentName(this,widgetdice2.class);
        manager.updateAppWidget(widget,view);

    }

    //バインダーを返す
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //ボタンクリック時に呼ばれる
    public void btnClicked(RemoteViews view){
        String[] place = new String[]{"右２","対３","左４","自５","右６","対７","左８","自９","右１０","対１１","左１２"};
    	int[] ids={
            R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,
            R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    	int idx=rand(6);
    	view.setImageViewResource(R.id.button1,ids[idx]);
    	int idx2=rand(6);
    	view.setImageViewResource(R.id.button2,ids[idx2]);

    	view.setTextViewText(R.id.place_name1, place[idx+idx2]);

    }

    //乱数の取得
    private static Random rand=new Random();
    public static int rand(int num) {
        return (rand.nextInt()>>>1)%num;
    }
}