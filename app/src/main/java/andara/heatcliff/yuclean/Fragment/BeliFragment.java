package andara.heatcliff.yuclean.Fragment;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import andara.heatcliff.yuclean.Adapter.AdapterBeli;
import andara.heatcliff.yuclean.ItemObject.ItemObjectBeli;
import andara.heatcliff.yuclean.R;

/**
 * Created by irfan on 10/17/2016.
 */
public class BeliFragment extends Fragment {

    public WebView webViewJualBeli;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_beli, container, false);

        webViewJualBeli = (WebView) rootView.findViewById(R.id.webViewJualBeli);
        webViewJualBeli.setWebViewClient(new BeliFragment.MyWebViewClient());

        String url = "https://trashpedia.net/carti";
        webViewJualBeli.getSettings().setJavaScriptEnabled(true);
        webViewJualBeli.loadUrl(url);

        return rootView;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.i("WEB_VIEW_TEST", "error code:" + errorCode);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }


    private void startWebView(String url)
    {        webViewJualBeli.setWebViewClient(new WebViewClient()

    {       ProgressDialog progressDialog;

        public boolean shouldOverrideUrlLoading(WebView view, String url) {                              view.loadUrl(url);                return true;            }

        public void onLoadResource (WebView view, String url) {                if (progressDialog == null) {
            progressDialog =  new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.show();                }            }
        public void onPageFinished(WebView view, String url) {
            try{                if (progressDialog.isShowing()) {
                progressDialog.dismiss();                    progressDialog = null;
            }                }catch(Exception exception){
                exception.printStackTrace();                }            }

    });

        webViewJualBeli.getSettings().setJavaScriptEnabled(true);

        webViewJualBeli.loadUrl(url);    }
}