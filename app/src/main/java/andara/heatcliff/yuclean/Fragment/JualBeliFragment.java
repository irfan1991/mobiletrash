package andara.heatcliff.yuclean.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import andara.heatcliff.yuclean.DetectConnection;
import andara.heatcliff.yuclean.R;

public class JualBeliFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static WebView webViewJualBeli;
    private View mContentView;
    private SwipeRefreshLayout refreshLayout;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_jual_beli, container, false);
        if (!DetectConnection.checkInternetConnection(getActivity().getApplicationContext())) {
            Toast.makeText(getActivity().getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        } else {


            webViewJualBeli = (WebView) rootView.findViewById(R.id.webViewJualBeli);
            webViewJualBeli.setWebViewClient(new MyWebViewClient());

            String url = "https://trashpedia.net/cata";
            webViewJualBeli.getSettings().setJavaScriptEnabled(true);
            webViewJualBeli.loadUrl(url);

            refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
            refreshLayout.setOnRefreshListener(this);
        }
            return rootView;

    }

    public void onRefresh(){
        webViewJualBeli.reload();
        refreshLayout.setRefreshing(false);
    }

    public JualBeliFragment() {
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!DetectConnection.checkInternetConnection(getActivity().getApplicationContext())) {
                Toast.makeText(getActivity().getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            view.loadData("Maaf Internet Anda Tidak Statil,Load Data Gagal","text/html","utf-8");
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            view.loadData("Maaf Internet Anda Tidak Statil,Load Data Gagal","text/html","utf-8");
            super.onReceivedHttpError(view, request, errorResponse);
        }
    }

    public static boolean canGoBack(){
        return webViewJualBeli.canGoBack();
    }

    public static void goBack(){
        webViewJualBeli.goBack();
    }




    }

