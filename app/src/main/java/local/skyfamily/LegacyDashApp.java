package local.skyfamily;
import android.app.*;import android.os.*;import android.graphics.*;import android.view.*;import java.util.*;
public final class LegacyDashApp extends Application implements Application.ActivityLifecycleCallbacks {
 static volatile long foregroundEpoch=0;int started=0;final Map<Activity,Fullscreen> screens=new IdentityHashMap<>();
 public void onCreate(){super.onCreate();registerActivityLifecycleCallbacks(this);}
 public void onActivityCreated(Activity activity,Bundle state){screens.put(activity,new Fullscreen(activity));}
 public void onActivityStarted(Activity activity){if(started++==0)foregroundEpoch++;}
 public void onActivityResumed(Activity activity){Fullscreen screen=screens.get(activity);if(screen!=null)screen.resume();}
 public void onActivityPaused(Activity activity){Fullscreen screen=screens.get(activity);if(screen!=null)screen.pause();}
 public void onActivityStopped(Activity activity){started=Math.max(0,started-1);}
 public void onActivitySaveInstanceState(Activity activity,Bundle state){}
 public void onActivityDestroyed(Activity activity){Fullscreen screen=screens.remove(activity);if(screen!=null)screen.close();}
 static final class Fullscreen implements ViewTreeObserver.OnGlobalLayoutListener {
 final Activity activity;final View decor;final Handler main=new Handler();boolean resumed=false,closed=false,keyboard=false;final Runnable applyMode=this::apply;
 Fullscreen(Activity owner){activity=owner;decor=owner.getWindow().getDecorView();decor.getViewTreeObserver().addOnGlobalLayoutListener(this);decor.setOnSystemUiVisibilityChangeListener(this::visibilityChanged);}
 void resume(){resumed=true;schedule(0);}
 void pause(){resumed=false;main.removeCallbacks(applyMode);}
 void schedule(long delay){main.removeCallbacks(applyMode);if(resumed&&!closed)main.postDelayed(applyMode,delay);}
 boolean keyboardVisible(){Rect visible=new Rect();decor.getWindowVisibleDisplayFrame(visible);if(visible.bottom<=0)return false;Point display=new Point();activity.getWindowManager().getDefaultDisplay().getSize(display);if(display.y<=0)return false;int threshold=Math.max(Math.round(100*activity.getResources().getDisplayMetrics().density),display.y/5);return display.y-visible.bottom>threshold;}
 public void onGlobalLayout(){if(!resumed||closed)return;boolean shown=keyboardVisible();boolean fullscreen=(activity.getWindow().getAttributes().flags&WindowManager.LayoutParams.FLAG_FULLSCREEN)!=0;if(shown!=keyboard||!shown&&!fullscreen){keyboard=shown;schedule(0);}}
 void visibilityChanged(int flags){if(resumed&&!closed)schedule(1000);}
 void apply(){if(!resumed||closed||activity.isFinishing())return;keyboard=keyboardVisible();Window window=activity.getWindow();int desired=0;if(keyboard){if((window.getAttributes().flags&WindowManager.LayoutParams.FLAG_FULLSCREEN)!=0)window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);}else{window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);if((window.getAttributes().flags&WindowManager.LayoutParams.FLAG_FULLSCREEN)==0)window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);desired=View.SYSTEM_UI_FLAG_FULLSCREEN;if(Build.VERSION.SDK_INT>=19)desired|=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;}int mask=View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;if(Build.VERSION.SDK_INT>=19)mask|=View.SYSTEM_UI_FLAG_IMMERSIVE|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;int current=decor.getSystemUiVisibility(),next=(current&~mask)|desired;if(current!=next)decor.setSystemUiVisibility(next);}
 void close(){pause();closed=true;decor.setOnSystemUiVisibilityChangeListener(null);ViewTreeObserver observer=decor.getViewTreeObserver();if(observer.isAlive())observer.removeOnGlobalLayoutListener(this);}
 }
}
