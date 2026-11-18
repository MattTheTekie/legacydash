package local.skyfamily;
import android.app.*;import android.content.Context;import android.os.*;import android.view.*;import android.widget.*;import java.util.*;
public final class LegacyDashApp extends Application implements Application.ActivityLifecycleCallbacks {
 static volatile long foregroundEpoch=0;int started=0;PetState companion;final Map<Activity,Fullscreen> screens=new IdentityHashMap<>();
 static PetState sharedPet(Context context){if(context.getApplicationContext() instanceof LegacyDashApp){LegacyDashApp app=(LegacyDashApp)context.getApplicationContext();if(app.companion==null)app.companion=new PetState(app);return app.companion;}return new PetState(context.getApplicationContext());}
 public void onCreate(){super.onCreate();registerActivityLifecycleCallbacks(this);}
 public void onActivityCreated(Activity activity,Bundle state){screens.put(activity,new Fullscreen(activity));}
 public void onActivityStarted(Activity activity){if(started++==0)foregroundEpoch++;}
 public void onActivityResumed(Activity activity){Fullscreen screen=screens.get(activity);if(screen!=null)screen.resume();}
 public void onActivityPaused(Activity activity){Fullscreen screen=screens.get(activity);if(screen!=null)screen.pause();}
 public void onActivityStopped(Activity activity){started=Math.max(0,started-1);}
 public void onActivitySaveInstanceState(Activity activity,Bundle state){}
 public void onActivityDestroyed(Activity activity){Fullscreen screen=screens.remove(activity);if(screen!=null)screen.close();}
 static void requestMode(Activity activity){if(!(activity.getApplication() instanceof LegacyDashApp))return;Fullscreen screen=((LegacyDashApp)activity.getApplication()).screens.get(activity);if(screen!=null)screen.apply();}
 static final class Fullscreen implements ViewTreeObserver.OnGlobalFocusChangeListener,ViewTreeObserver.OnGlobalLayoutListener {
 final Activity activity;final View decor;final Handler main=new Handler();boolean resumed=false,closed=false,applying=false;FloatingPet pet;final Runnable applyMode=this::apply;
 Fullscreen(Activity owner){activity=owner;decor=owner.getWindow().getDecorView();decor.getViewTreeObserver().addOnGlobalFocusChangeListener(this);decor.getViewTreeObserver().addOnGlobalLayoutListener(this);decor.setOnSystemUiVisibilityChangeListener(this::visibilityChanged);}
 void resume(){resumed=true;apply();ensurePet();}
 void pause(){resumed=false;main.removeCallbacks(applyMode);if(pet!=null)pet.foreground(false);}
 void schedule(long delay){main.removeCallbacks(applyMode);if(resumed&&!closed)main.postDelayed(applyMode,delay);}
 public void onGlobalFocusChanged(View oldFocus,View newFocus){apply();}
 public void onGlobalLayout(){if(resumed&&!closed)ensurePet();}
 void ensurePet(){if(!resumed||closed)return;View view=activity.findViewById(android.R.id.content);if(!(view instanceof FrameLayout))return;FrameLayout content=(FrameLayout)view;if(pet==null)pet=new FloatingPet(activity,sharedPet(activity));if(pet.getParent()!=content){if(pet.getParent() instanceof ViewGroup)((ViewGroup)pet.getParent()).removeView(pet);FrameLayout.LayoutParams size=new FrameLayout.LayoutParams(pet.dp(104),pet.dp(96),Gravity.BOTTOM|Gravity.RIGHT);size.setMargins(pet.dp(12),pet.dp(12),pet.dp(12),pet.dp(12));content.addView(pet,size);if(Build.VERSION.SDK_INT>=21)pet.setElevation(pet.dp(24));}if(content.indexOfChild(pet)!=content.getChildCount()-1)pet.bringToFront();pet.constrain();pet.foreground(true);}
 void visibilityChanged(int flags){if(resumed&&!closed&&!applying)schedule(1500);}
 void apply(){if(!resumed||closed||applying||activity.isFinishing())return;applying=true;try{Window window=activity.getWindow();int flags=window.getAttributes().flags;if((flags&WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)!=0)window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);if((flags&WindowManager.LayoutParams.FLAG_FULLSCREEN)==0)window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);int desired=View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;int mask=View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;if(Build.VERSION.SDK_INT>=19){desired|=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;mask|=View.SYSTEM_UI_FLAG_IMMERSIVE|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;}int current=decor.getSystemUiVisibility(),next=(current&~mask)|desired;if(current!=next)decor.setSystemUiVisibility(next);}finally{applying=false;}}
 void close(){pause();closed=true;decor.setOnSystemUiVisibilityChangeListener(null);ViewTreeObserver observer=decor.getViewTreeObserver();if(observer.isAlive()){observer.removeOnGlobalFocusChangeListener(this);observer.removeOnGlobalLayoutListener(this);}if(pet!=null&&pet.getParent() instanceof ViewGroup)((ViewGroup)pet.getParent()).removeView(pet);pet=null;}
 }
}
