package andara.heatcliff.yuclean.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import andara.heatcliff.yuclean.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {
    public WebView webViweEdit;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tarik_dana, container, false);

        webViweEdit = (WebView) rootView.findViewById(R.id.webViewTarik);
        webViweEdit.setWebViewClient(new EditFragment.MyWebViewClient());

        String url = "http://yuclean.andara-tech.com/editprofile";
        webViweEdit.getSettings().setJavaScriptEnabled(true);
        webViweEdit.loadUrl(url);

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
