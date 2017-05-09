package andara.heatcliff.yuclean;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.kbeanie.imagechooser.api.ChooserType;
import com.kbeanie.imagechooser.api.ChosenImage;
import com.kbeanie.imagechooser.api.FileUtils;
import com.kbeanie.imagechooser.api.ImageChooserListener;
import com.kbeanie.imagechooser.api.ImageChooserManager;
import com.kbeanie.imagechooser.api.utils.ImageChooserBuilder;

import android.view.MenuItem;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;

import andara.heatcliff.yuclean.Login.LoginActivity;

public class RegistrasiWebViewActivity extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener {

    static  WebView webView;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;
    ProgressDialog mProgress;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_web_view);

        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "PERMASALAHAN KONEKSI, SILAHKAN COBA LAGI", Toast.LENGTH_SHORT).show();
        } else {
            webView = (WebView) findViewById(R.id.webView);
            webView.setWebViewClient(new MyWebViewClient());

            String url = "https://trashpedia.net/daftar";
            WebSettings mWebSettings = webView.getSettings();
            mWebSettings.setJavaScriptEnabled(true);
            mWebSettings.setSupportZoom(false);
            mWebSettings.setAllowFileAccess(true);
            mWebSettings.setAllowFileAccess(true);
            mWebSettings.setAllowContentAccess(true);
            // the init state of progress dialog

            webView.loadUrl(url);
            webView.setWebChromeClient(new WebChromeClient()
            {


                // For 3.0+ Devices (Start)
                // onActivityResult attached before constructor
                protected void openFileChooser(ValueCallback uploadMsg, String acceptType)
                {
                    mUploadMessage = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
                }


                // For Lollipop 5.0+ Devices
                public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)
                {
                    if (uploadMessage != null) {
                        uploadMessage.onReceiveValue(null);
                        uploadMessage = null;
                    }

                    uploadMessage = filePathCallback;

                    Intent intent = fileChooserParams.createIntent();
                    try
                    {
                        startActivityForResult(intent, REQUEST_SELECT_FILE);
                    } catch (ActivityNotFoundException e)
                    {
                        uploadMessage = null;
                        Toast.makeText(getApplicationContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                        return false;
                    }
                    return true;
                }

                //For Android 4.1 only
                protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
                {
                    mUploadMessage = uploadMsg;
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
                }

                protected void openFileChooser(ValueCallback<Uri> uploadMsg)
                {
                    mUploadMessage = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
                }



            });

            refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
            refreshLayout.setOnRefreshListener(this);
        }

    }


    public void onRefresh(){
        webView.reload();
        refreshLayout.setRefreshing(false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (requestCode == REQUEST_SELECT_FILE)
            {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        }
        else if (requestCode == FILECHOOSER_RESULTCODE)
        {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
        else
            Toast.makeText(this.getApplicationContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }


        private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!DetectConnection.checkInternetConnection(getApplicationContext())) {
                Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
            } else {
                view.loadUrl(url);
            }
            return true;
        }


        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
            //Your code to do
            view.loadData("Maaf Internet Anda Tidak Statil,Load Data Gagal","text/html","utf-8");
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            view.loadData("Maaf Internet Anda Tidak Statil,Load Data Gagal","text/html","utf-8");
            super.onReceivedHttpError(view, request, errorResponse);
        }



    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menureg, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_oke:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Toast.makeText(RegistrasiWebViewActivity.this, "Registrasi Sukses", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrasiWebViewActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
