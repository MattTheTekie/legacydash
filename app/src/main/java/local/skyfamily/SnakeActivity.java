package local.skyfamily;
import android.graphics.*;import android.os.Handler;import android.view.*;import android.widget.*;import java.util.*;
public class SnakeActivity extends ArcadeActivity {
 SnakeBoard snake;
 @Override void page(String title){if(snake!=null){snake.stop();snake=null;}super.page(title);}
 @Override void puzzle(){super.puzzle();button("Snake",()->playSnake());}
 void playSnake(){page("Snake");text("Eat the amber food to grow. Avoid walls and yourself. Swipe, use arrows, or use the on-screen controls.");TextView stats=new TextView(this);stats.setTextColor(accent);stats.setTextSize(20);body.addView(stats);snake=new SnakeBoard(stats);body.addView(snake,new LinearLayout.LayoutParams(-1,dp(430)));button("Pause / resume",()->{if(snake!=null){snake.paused=!snake.paused;snake.status();snake.invalidate();}});button("Restart",()->playSnake());button("Up",()->{if(snake!=null)snake.turn(0);});button("Left",()->{if(snake!=null)snake.turn(3);});button("Right",()->{if(snake!=null)snake.turn(1);});button("Down",()->{if(snake!=null)snake.turn(2);});snake.start();}
 @Override public boolean onKeyDown(int key,android.view.KeyEvent event){if(snake!=null){int d=key==19?0:key==22?1:key==20?2:key==21?3:-1;if(d>=0){snake.turn(d);return true;}if(key==62){snake.paused=!snake.paused;snake.status();snake.invalidate();return true;}}return super.onKeyDown(key,event);}
 @Override protected void onPause(){if(snake!=null){snake.paused=true;snake.stop();snake.status();}super.onPause();}
 @Override protected void onResume(){super.onResume();if(snake!=null)snake.start();}
 @Override protected void onDestroy(){if(snake!=null)snake.stop();super.onDestroy();}
 class SnakeBoard extends View {
 final int size=20;ArrayDeque<Integer> segments=new ArrayDeque<>();Random random=new Random();Handler timer=new Handler();Paint pen=new Paint(3);TextView stats;int direction=1,pending=1,food,score=0;boolean queued=false,paused=false,over=false,running=false,won=false;float downX,downY;int[] dx={0,1,0,-1},dy={-1,0,1,0};
 SnakeBoard(TextView s){super(SnakeActivity.this);stats=s;segments.add(210);segments.add(209);segments.add(208);placeFood();status();setContentDescription("Snake board. Direction buttons are below.");}
 void placeFood(){ArrayList<Integer> free=new ArrayList<>();for(int i=0;i<size*size;i++)if(!segments.contains(i))free.add(i);if(free.isEmpty()){won=true;end();return;}food=free.get(random.nextInt(free.size()));}
 void status(){stats.setText("Score "+score+" / Best "+prefs.getInt("snake.best",0)+(over?(won?" / Board complete!":" / Game over"):paused?" / Paused":""));}
 void end(){over=true;int best=prefs.getInt("snake.best",0);if(score>best)prefs.edit().putInt("snake.best",score).apply();status();}
 void turn(int d){if(paused||over||queued||d==direction||(direction+2)%4==d)return;pending=d;queued=true;}
 void tick(){direction=pending;queued=false;int head=segments.peekFirst(),x=head%size+dx[direction],y=head/size+dy[direction];if(x<0||y<0||x>=size||y>=size){end();return;}int next=y*size+x;boolean eat=next==food;boolean occupied=segments.contains(next);if(occupied&&(eat||next!=segments.peekLast())){end();return;}if(!eat)segments.removeLast();segments.addFirst(next);if(eat){score++;int best=prefs.getInt("snake.best",0);if(score>best)prefs.edit().putInt("snake.best",score).apply();placeFood();}status();}
 final Runnable pulse=new Runnable(){public void run(){if(!running)return;if(!paused&&!over)tick();invalidate();timer.postDelayed(this,Math.max(70,210-score*5));}};
 void start(){if(running)return;running=true;timer.postDelayed(pulse,250);}
 void stop(){running=false;timer.removeCallbacksAndMessages(null);}
 protected void onDraw(Canvas c){c.drawColor(bg);float cell=Math.min(getWidth(),getHeight())/(float)size,ox=(getWidth()-size*cell)/2,oy=(getHeight()-size*cell)/2;for(int y=0;y<size;y++)for(int x=0;x<size;x++){pen.setColor((x+y)%2==0?surface:0xff18263a);c.drawRect(ox+x*cell,oy+y*cell,ox+(x+1)*cell,oy+(y+1)*cell,pen);}if(!won){pen.setColor(0xffffb44f);c.drawCircle(ox+(food%size+.5f)*cell,oy+(food/size+.5f)*cell,cell*.32f,pen);}int index=0;for(int at:segments){pen.setColor(index++==0?0xffc1fff0:accent);c.drawRoundRect(new RectF(ox+(at%size)*cell+1,oy+(at/size)*cell+1,ox+(at%size+1)*cell-1,oy+(at/size+1)*cell-1),cell*.2f,cell*.2f,pen);}if(paused||over){pen.setColor(0xdd0b1120);c.drawRect(ox,oy+cell*8,ox+cell*size,oy+cell*12,pen);pen.setColor(fg);pen.setTextAlign(Paint.Align.CENTER);pen.setTypeface(Typeface.DEFAULT_BOLD);pen.setTextSize(dp(24));c.drawText(over?(won?"Board complete!":"Game over"): "Paused",getWidth()/2f,oy+cell*10.5f,pen);}}
 public boolean onTouchEvent(MotionEvent e){if(e.getAction()==0){downX=e.getX();downY=e.getY();getParent().requestDisallowInterceptTouchEvent(true);}if(e.getAction()==1){float x=e.getX()-downX,y=e.getY()-downY;if(Math.abs(x)+Math.abs(y)>dp(16))turn(Math.abs(x)>Math.abs(y)?x>0?1:3:y>0?2:0);getParent().requestDisallowInterceptTouchEvent(false);performClick();}if(e.getAction()==3)getParent().requestDisallowInterceptTouchEvent(false);return true;}
 public boolean performClick(){super.performClick();return true;}
 protected void onDetachedFromWindow(){stop();super.onDetachedFromWindow();}
 }
}
