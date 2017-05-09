package andara.heatcliff.yuclean.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import andara.heatcliff.yuclean.R;


public class TarikDanaFragment extends Fragment {
    public WebView webViweTarikDana;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tarik_dana, container, false);

        webViweTarikDana = (WebView) rootView.findViewById(R.id.webViewTarik);
        webViweTarikDana.setWebViewClient(new TarikDanaFragment.MyWebViewClient());

        String url = "https://trashpedia.net/tarikdana";
        webViweTarikDana.getSettings().setJavaScriptEnabled(true);
        webViweTarikDana.loadUrl(url);

        return rootView;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
