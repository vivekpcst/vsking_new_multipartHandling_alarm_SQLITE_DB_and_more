package dzo.com.barcodescanner;

import android.app.SearchManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Random;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FirstSlideShow.OnFragmentItemCLickListener,
        BeautifulGirlsDetail.OnDbSavedListener, ShowGirls.OnItemSweepDeleteListener {
    TextView slideshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.nav_slideshow);

        item.setIcon(buildCounterDrawable(99, R.drawable.ic_menu_slideshow));
        slideshow=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_slideshow));
        initializeCountDrawer();
        navigationView.setNavigationItemSelectedListener(this);
        displayFragment(R.id.nav_camera);
    }
    private void initializeCountDrawer(){
        //Gravity property aligns the text
//        gallery.setGravity(Gravity.CENTER_VERTICAL);
//        gallery.setTypeface(null, Typeface.BOLD);
//        gallery.setTextColor(getResources().getColor(R.color.colorAccent));
//        gallery.setText("99+");
        slideshow.setGravity(Gravity.CENTER_VERTICAL);
        slideshow.setTypeface(null, Typeface.BOLD);
        slideshow.setTextColor(getResources().getColor(R.color.colorAccent));
//count is added
        slideshow.setText("7");
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;


    }
    private Drawable buildCounterDrawable(int count,int image){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.counter_menu_item, null);
        view.setBackgroundResource(image);
        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.BadgeRelativeLayout);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(R.id.BadgeCount);
            textView.setText("" + count);
        }
        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return new BitmapDrawable(getResources(), bitmap);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        /*
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(this,BarCodeScanner.class));

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        displayFragment(item.getItemId());
        return true;
    }

   private void displayFragment(int fragment_id){
       Fragment fragment=null;
       switch(fragment_id){
           case R.id.nav_camera:
           fragment=new BarCodeScanner();
           break;
           case R.id.nav_gallery:
               fragment=new Gallery();
               break;
           case R.id.nav_slideshow:
               fragment=new SlideFragment();
               break;
           case R.id.nav_manage:
               fragment=new BeautifulGirlsDetail();
               break;
           case R.id.nav_share:
               fragment=new MyTextToSpeech();
               break;
       }
       if (fragment!=null){
           FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
           fragmentTransaction.replace(R.id.main_content,fragment);
           fragmentTransaction.commit();
       }
       DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
       drawerLayout.closeDrawer(GravityCompat.START);
   }



    @Override
    public void onFragmentClick(String color) {
        Random rnd = new Random();
        int colors = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        SecondSlideShow slideShow=new SecondSlideShow();
        Bundle bd=new Bundle();
        bd.putInt("color",colors);
        slideShow.setArguments(bd);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction().replace(R.id.secondSlideContent,slideShow,null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onDbSaved(String id, String name, String age, String occupation) {
        MyDatabaseHelper dbHelper=new MyDatabaseHelper(this);
        dbHelper.insertNewRow(id,name,age,occupation);

    }

    @Override
    public void onItemDelete(String id) {
        MyDatabaseHelper dbHelper=new MyDatabaseHelper(this);
        dbHelper.deleteRow(id);
    }

    @Override
    public void onDeleteSwip(String BeautifulGirlsDetail) {
        displayFragment(R.id.nav_manage);
    }
}
