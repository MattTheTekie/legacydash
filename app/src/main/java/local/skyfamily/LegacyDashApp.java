package local.skyfamily;
import android.app.*;import android.os.*;import android.view.*;import android.widget.EditText;import java.util.*;
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
 static void requestMode(Activity activity){if(!(activity.getApplication() instanceof LegacyDashApp))return;Fullscreen screen=((LegacyDashApp)activity.getApplication()).screens.get(activity);if(screen!=null)screen.schedule(0);}
 static final class Fullscreen implements ViewTreeObserver.OnGlobalFocusChangeListener {
 final Activity activity;final View decor;final Handler main=new Handler();boolean resumed=false,closed=false;final Runnable applyMode=this::apply;
 Fullscreen(Activity owner){activity=owner;decor=owner.getWindow().getDecorView();decor.getViewTreeObserver().addOnGlobalFocusChangeListener(this);decor.setOnSystemUiVisibilityChangeListener(this::visibilityChanged);}
 void resume(){resumed=true;schedule(0);}
 void pause(){resumed=false;main.removeCallbacks(applyMode);}
 void schedule(long delay){main.removeCallbacks(applyMode);if(resumed&&!closed)main.postDelayed(applyMode,delay);}
 public void onGlobalFocusChanged(View oldFocus,View newFocus){schedule(0);}
 void visibilityChanged(int flags){if(resumed&&!closed)schedule(1500);}
 void apply(){if(!resumed||closed||activity.isFinishing())return;boolean editing=activity.getCurrentFocus() instanceof EditText;Window window=activity.getWindow();int desired=0,flags=window.getAttributes().flags;if(editing){if((flags&WindowManager.LayoutParams.FLAG_FULLSCREEN)!=0)window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);}else{if((flags&WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)!=0)window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);if((flags&WindowManager.LayoutParams.FLAG_FULLSCREEN)==0)window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);desired=View.SYSTEM_UI_FLAG_FULLSCREEN;if(Build.VERSION.SDK_INT>=19)desired|=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;}int mask=View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;if(Build.VERSION.SDK_INT>=19)mask|=View.SYSTEM_UI_FLAG_IMMERSIVE|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;int current=decor.getSystemUiVisibility(),next=(current&~mask)|desired;if(current!=next)decor.setSystemUiVisibility(next);}
 void close(){pause();closed=true;decor.setOnSystemUiVisibilityChangeListener(null);ViewTreeObserver observer=decor.getViewTreeObserver();if(observer.isAlive())observer.removeOnGlobalFocusChangeListener(this);}
 }
}
