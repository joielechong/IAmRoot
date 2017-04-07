package com.rilixtech.iamroot.sample;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import uk.co.barbuzz.beerprogressview.BeerProgressView;

public class MainActivity extends AppCompatActivity
    implements CheckRootTask.OnCheckRootFinishedListener {

  private AlertDialog mInfoDialog;
  private BeerProgressView mBeerView;
  private MainActivity mActivity;
  private TextViewFont mIsRootedText;
  private ArrayList<ImageView> mCheckRootImageViewList;
  private TextView mIsRootedTextDisclaimer;
  private FloatingActionButton mFloatingActionButton;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
  }

  private void initView() {
    mActivity = this;
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mBeerView = (BeerProgressView) findViewById(R.id.loadingRootCheckBeerView);
    mIsRootedText = (TextViewFont) findViewById(R.id.content_main_is_rooted_text);
    mIsRootedTextDisclaimer = (TextView) findViewById(R.id.content_mainisRootedTextDisclaimer);

    ImageView rootCheck1ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_1);
    ImageView rootCheck2ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_2);
    ImageView rootCheck3ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_3);
    ImageView rootCheck4ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_4);
    ImageView rootCheck5ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_5);
    ImageView rootCheck6ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_6);
    ImageView rootCheck7ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_7);
    ImageView rootCheck8ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_8);
    ImageView rootCheck9ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_9);
    ImageView rootCheck10ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_10);
    ImageView rootCheck11ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_11);
    mCheckRootImageViewList = new ArrayList<>();
    mCheckRootImageViewList.add(rootCheck1ImageView);
    mCheckRootImageViewList.add(rootCheck2ImageView);
    mCheckRootImageViewList.add(rootCheck3ImageView);
    mCheckRootImageViewList.add(rootCheck4ImageView);
    mCheckRootImageViewList.add(rootCheck5ImageView);
    mCheckRootImageViewList.add(rootCheck6ImageView);
    mCheckRootImageViewList.add(rootCheck7ImageView);
    mCheckRootImageViewList.add(rootCheck8ImageView);
    mCheckRootImageViewList.add(rootCheck9ImageView);
    mCheckRootImageViewList.add(rootCheck10ImageView);
    mCheckRootImageViewList.add(rootCheck11ImageView);

    mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        mFloatingActionButton.setVisibility(View.GONE);
        resetRootCheckImages();
        CheckRootTask checkRootTask =
            new CheckRootTask(mActivity, mActivity, mBeerView, mCheckRootImageViewList);
        checkRootTask.execute(true);
      }
    });
  }

  private void resetRootCheckImages() {
    mIsRootedText.setVisibility(View.GONE);
    for (ImageView imageView : mCheckRootImageViewList) {
      imageView.setImageDrawable(null);
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_github) {
      Intent i = new Intent(Intent.ACTION_VIEW);
      i.setData(Uri.parse(getString(R.string.github_link)));
      startActivity(i);
      return true;
    } else if (id == R.id.action_info) {
      showInfoDialog();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void showInfoDialog() {
    if (mInfoDialog != null && mInfoDialog.isShowing()) {
      //do nothing if already showing
    } else {
      mInfoDialog = new AlertDialog.Builder(this).setTitle(R.string.app_name)
          .setMessage(R.string.info_details)
          .setCancelable(true)
          .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
            }
          })
          .setNegativeButton(R.string.more_info, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
              startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.github_link))));
            }
          })
          .create();
      mInfoDialog.show();
    }
  }

  @Override public void onCheckRootFinished(boolean isRooted) {
    mFloatingActionButton.setVisibility(View.VISIBLE);
    mIsRootedText.setText(isRooted ? "ROOTED*" : "NOT ROOTED");
    mIsRootedTextDisclaimer.setVisibility(isRooted ? View.VISIBLE : View.GONE);
    mIsRootedText.setTextColor(isRooted ? ContextCompat.getColor(this, R.color.fail)
        : ContextCompat.getColor(this, R.color.pass));
    mIsRootedText.setVisibility(View.VISIBLE);
  }
}
