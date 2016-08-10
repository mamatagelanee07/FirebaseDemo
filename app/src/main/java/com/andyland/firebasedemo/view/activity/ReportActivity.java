import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.andyland.firebasedemo.R;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReportActivity extends AppCompatActivity {

    private String mUser_Code;
    PersianDatePicker dtPicker;
    TimePicker tmPicker;
    TextView txtFromDate, txtToDate, txtFromTime, txtToTime;
    public static Button btnSearch, btnMap;
    static List<Marketing_Points> userPointList;

    static List<Orginal_Points> orginal;

    public static RecyclerView Points_Recycler;
    private LinearLayoutManager pointsLayoutManager;
    public static PointsList_Adapter pointAdapter;
    static int check = 0;
    //boolean falgTest= true;
    boolean falgTest2 = true;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        activity = ReportActivity.this;


        txtFromDate = (TextView) findViewById(R.id.txtFromDate);
        txtToDate = (TextView) findViewById(R.id.txtToDate);
        txtFromTime = (TextView) findViewById(R.id.txtFromTime);
        txtToTime = (TextView) findViewById(R.id.txtToTime);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        Points_Recycler = (RecyclerView) findViewById(R.id.Points_Recycler);
        btnMap = (Button) findViewById(R.id.btnMap);
        Points_Recycler.setHasFixedSize(true);

        pointsLayoutManager = new LinearLayoutManager(this);
// use a linear layout manager
        Points_Recycler.setLayoutManager(pointsLayoutManager);

        txtFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ReportActivity.this);
                dialog.setContentView(R.layout.custom_datepicker);
                dialog.setTitle("Title...");
                dtPicker = (PersianDatePicker) dialog.findViewById(R.id.dtPicker);
                Button btnInsertDate = (Button) dialog.findViewById(R.id.btnInsertDate);
                btnInsertDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtFromDate.setText(dtPicker.getDisplayPersianDate().getPersianShortDate());
                        dialog.dismiss();
                    }
                });
                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancelDate);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        txtToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ReportActivity.this);
                dialog.setContentView(R.layout.custom_datepicker);
                dialog.setTitle("Title...");
                dtPicker = (PersianDatePicker) dialog.findViewById(R.id.dtPicker);
                Button btnInsertDate = (Button) dialog.findViewById(R.id.btnInsertDate);
                btnInsertDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtToDate.setText(dtPicker.getDisplayPersianDate().getPersianShortDate());
                        dialog.dismiss();
                    }
                });
                Button btnCancelDate = (Button) dialog.findViewById(R.id.btnCancelDate);
                btnCancelDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        txtFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ReportActivity.this);
                dialog.setContentView(R.layout.custom_timepicker);
                dialog.setTitle("Title...");
                tmPicker = (TimePicker) dialog.findViewById(R.id.tmPicker);
                tmPicker.setIs24HourView(true);
                Button btnInsertTime = (Button) dialog.findViewById(R.id.btnInsertTime);
                btnInsertTime.setOnClickListener(new View.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {
                        int hour = 0;
                        int min = 0;
                        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                            hour = tmPicker.getHour();
                            min = tmPicker.getMinute();
                            txtFromTime.setText(hour + ":" + min + ":" + "00");
                        } else {
                            hour = tmPicker.getCurrentHour();
                            min = tmPicker.getCurrentMinute();
                            txtFromTime.setText(hour + ":" + min + ":" + "00");
                        }
                        dialog.dismiss();
                    }
                });
                Button btnCancelTime = (Button) dialog.findViewById(R.id.btnCancelTime);
                btnCancelTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        txtToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ReportActivity.this);
                dialog.setContentView(R.layout.custom_timepicker);
                dialog.setTitle("Title...");
                tmPicker = (TimePicker) dialog.findViewById(R.id.tmPicker);
                tmPicker.setIs24HourView(true);
                Button btnInsertTime = (Button) dialog.findViewById(R.id.btnInsertTime);
                btnInsertTime.setOnClickListener(new View.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {
                        int hour = 0;
                        int min = 0;

                        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                            hour = tmPicker.getHour();
                            min = tmPicker.getMinute();
                            txtToTime.setText(hour + ":" + min + ":" + "00");
                        } else {
                            hour = tmPicker.getCurrentHour();
                            min = tmPicker.getCurrentMinute();
                            txtToTime.setText(hour + ":" + min + ":" + "00");
                        }
                        dialog.dismiss();
                    }
                });
                Button btnCancelTime = (Button) dialog.findViewById(R.id.btnCancelTime);
                btnCancelTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, MapsActivity.class);
                ReportActivity.this.startActivity(intent);
            }
        });

        mUser_Code = getIntent().getStringExtra("User_Code");
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearch.setEnabled(false);
                String uri;
                String FDate = txtFromDate.getText().toString();
                String TDate = txtToDate.getText().toString();
                String FTime = txtFromTime.getText().toString();
                String TTime = txtToTime.getText().toString();
                RequestPackage packag;
                if (FDate.length() > 0 & TDate.length() > 0 & FTime.length() > 0 & TTime.length() > 0) {
                    packag = new RequestData().getRequest(Const.URL_Points_List, FDate, TDate, FTime, TTime);
                    uri = GenerateFullUrl.FullUrl(packag, mUser_Code);
                    if (uri.length() > 0) {
                        makeJsonArryReq(uri);
                    }
                } else if (FDate.length() > 0 & TDate.length() > 0) {
                    packag = new RequestData().getRequest(Const.URL_Points_List, FDate, TDate);
                    uri = GenerateFullUrl.FullUrl(packag, mUser_Code);
                    if (uri.length() > 0) {
                        makeJsonArryReq(uri);
                    }
                } else {
                    btnSearch.setEnabled(true);
                    Toast.makeText(ReportActivity.this, getString(R.string.errorReport), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void makeJsonArryReq(String uri) {
        userPointList = new ArrayList<>();
        orginal = new ArrayList<>();

        final JsonArrayRequest req = new JsonArrayRequest(uri,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        orginal = MarketingPoints_JSONParser.FeedOriginal(response.toString(), mUser_Code, ReportActivity.this);
                        userPointList = MarketingPoints_JSONParser.parseFeed(response.toString(), mUser_Code, ReportActivity.this);
                        FillList();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Custom Log Error", "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);

    }

    public void FillList() {
        ArrayList<StepModel> Step = new ArrayList<>();
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());

        List<Address> addresses;
        StepModel stp = null;
        if (check < userPointList.size()) {
            btnMap.setEnabled(true);
            try {
                addresses = geocoder.getFromLocation(userPointList.get(check).getLat(), userPointList.get(check).getLng(), 1);
                stp = new StepModel();
                stp.setsCity(addresses.get(0).getAdminArea());
                stp.setsStreet(addresses.get(0).getThoroughfare());
                stp.setSlat(userPointList.get(check).getLat());
                stp.setSlng(userPointList.get(check).getLng());
                stp.setSdate(userPointList.get(check).getDate());
                stp.setStime(userPointList.get(check).getTime());
                stp.setsCounts(userPointList.get(check).getCounts());
                stp.setSprofilePhoto(userPointList.get(check).getProfilePhoto());
                stp.setsUserCode(userPointList.get(check).getUserCode());
                Step.add(stp);

            } catch (IOException e) {
                e.printStackTrace();
            }
            pointAdapter = new PointsList_Adapter(Step, activity);
            pointAdapter.reloadList(Step);
            pointAdapter.notifyDataSetChanged();
            check++;
        } else {
            return;
        }

    }

    public static List<Marketing_Points> mapPoint() {
        return userPointList;
    }

    public static List<Orginal_Points> FullPoint() {
        return orginal;
    }


}