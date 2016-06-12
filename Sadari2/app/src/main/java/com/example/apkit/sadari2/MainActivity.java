package com.example.apkit.sadari2;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    int jHari=0;
    int valBulan=0;
    int valTgl=0;
    DBHelper myDB = new DBHelper(this);
    SessionManager sessionManager = new SessionManager();
    private int[] tabIcons = {
            R.drawable.iconsadari,
            R.drawable.iconlatihan,
            R.drawable.iconcatatan,
            R.drawable.iconpayudara,
            R.drawable.iconkonsultasi,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.icon);
        actionBar.setTitle("SADARI");
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        try {
            Intent i = getIntent();
            if (i.getStringExtra("goto").equals("catatan")) {
                viewPager.setCurrentItem(2);
            }
        }
        catch (Exception e){

        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new SadariFragment(),  "SADARI");
        adapter.addFrag(new LatihanFragment(), "Latihan");
        adapter.addFrag(new CatatanFragment(),  "Catatan");
        adapter.addFrag(new PayudaraFragment(), "Payudara");
        adapter.addFrag(new KonsultasiFragment(),  "Konsultasi");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle (int position) {
            return null;
            //public CharSequence getPageTitle(int position) {
            //return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.action_alarm:
                scheduleNotification(getNotification("Apakah Anda Telah Melakukan Sadari?"), 5000);
                return true;
        }
        return true;
    }


    public void dialogAddHaid(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setIcon(getResources().getDrawable(R.drawable.icon));
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_haid, null);
        alertDialog.setView(view);
        final Spinner txtTglHaid = (Spinner) view.findViewById(R.id.dialog_sp_tgl);
        final Spinner txtBulanHaid = (Spinner) view.findViewById(R.id.dialog_sp_bulan);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bulan,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtBulanHaid.setAdapter(adapter);
        final List<String> listTanggal;
        listTanggal = new ArrayList<String>();
        for(int i=1; i<=31; i++){
            listTanggal.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapterTanggal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listTanggal);
        adapterTanggal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtTglHaid.setAdapter(adapterTanggal);

        SQLiteDatabase db = myDB.getReadableDatabase();
        String query = "SELECT * FROM " + myDB.TB_HAID + " ORDER BY " + myDB.HAID_ID + " ASC";
        Cursor res = db.rawQuery(query, null);
        try {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                if (res.getString(res.getColumnIndex(myDB.HAID_USER_ID)).equals(sessionManager.getPreferences(MainActivity.this, "ID"))) {
                    //valBulan = adapter.getPosition(res.getString(res.getColumnIndex(myDB.HAID_BULAN)));
                    valBulan = Integer.parseInt(res.getString(res.getColumnIndex(myDB.HAID_BULAN))) - 1;
                    valTgl = Integer.parseInt(res.getString(res.getColumnIndex(myDB.HAID_TANGGAL))) - 1;
                }
                res.moveToNext();
            }
        }
        catch (Exception e){
            valBulan = 0;
            valTgl = 0;
        }
        //Toast.makeText(getApplicationContext(), String.valueOf(valBulan), Toast.LENGTH_LONG).show();
        txtTglHaid.setSelection(valTgl);
        txtBulanHaid.setSelection(valBulan);
        alertDialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valTgl = Integer.parseInt(txtTglHaid.getSelectedItem().toString());
                valBulan = txtBulanHaid.getSelectedItemPosition() + 1;
                switch (valBulan) {
                    case 1:
                        jHari = 31;
                        break;
                    case 2:
                        jHari = 29;
                        break;
                    case 3:
                        jHari = 31;
                        break;
                    case 4:
                        jHari = 30;
                        break;
                    case 5:
                        jHari = 31;
                        break;
                    case 6:
                        jHari = 30;
                        break;
                    case 7:
                        jHari = 31;
                        break;
                    case 8:
                        jHari = 31;
                        break;
                    case 9:
                        jHari = 30;
                        break;
                    case 10:
                        jHari = 31;
                        break;
                    case 11:
                        jHari = 30;
                        break;
                    case 12:
                        jHari = 31;
                        break;
                }
                //Toast.makeText(getApplicationContext(),"Kesalahan!! Bulan ke-" + tgl + " hanya memiliki " + jHari + " hari",Toast.LENGTH_LONG).show();
                if (valTgl > jHari) {
                    Toast.makeText(getApplicationContext(), "Kesalahan!! Bulan ke-" + valBulan + " hanya memiliki " + jHari + " hari", Toast.LENGTH_LONG).show();
                } else {
                    myDB.insertHaid(Integer.parseInt(sessionManager.getPreferences(getApplicationContext(), "ID")), valTgl, valBulan);
                }
            }
        });
        alertDialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification getNotification(String content) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Alarm Sadari");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.icon);
        builder.setContentIntent(pintent);

        return builder.build();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Keluar")
                .setMessage("Apakah anda yakin akan keluar?")
                .setIcon(R.drawable.icon)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("no", null).show();
    }
}

