package local.skyfamily;
import android.app.AlertDialog;import android.content.Intent;
public class PersonalActivity extends CalendarPhotosActivity {
 @Override void normalWindow(){LegacyDashApp.requestMode(this);}
 @Override void page(String title){super.page(title);ShortcutPins.install(this);}
 void colouringLibrary(boolean pick){Intent intent=new Intent(this,ColouringBookActivity.class);intent.putExtra("import",pick);startActivity(intent);}
 @Override void drawing(boolean colouring){if(!colouring){super.drawing(false);return;}new AlertDialog.Builder(this).setTitle("Colouring").setItems(new String[]{"Built-in colouring pages","Import colouring book / PDF, EPUB, CBZ, image","Imported colouring books"},(dialog,index)->{if(index==0)super.drawing(true);else colouringLibrary(index==1);}).setNegativeButton("Cancel",null).show();}
 @Override void frameCreate(){super.frameCreate();button("Import colouring book",()->colouringLibrary(true));button("Imported colouring books",()->colouringLibrary(false));}
 @Override void uploadHub(){super.uploadHub();button("Import colouring book",()->colouringLibrary(true));button("Imported colouring books",()->colouringLibrary(false));}
}
