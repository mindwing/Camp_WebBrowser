package kr.mindwing.webbrowser;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textUrl;
    private WebView webView;
    private Button backButton; //, forwardButton;
    private View.OnKeyListener listener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() != KeyEvent.ACTION_DOWN) {
                return false;
            }

            boolean processed = false;

            switch (keyCode) {
                case KeyEvent.KEYCODE_ENTER:
                    webView.loadUrl(textUrl.getText().toString());

                    processed = true;
                    break;

                default:
                    break;
            }

            return processed;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    noti("맨 뒷페이지 입니다.");
                }
            }
        });

//        사용되지 않음.
//        forwardButton = (Button) findViewById(R.id.forward);

        textUrl = (EditText) findViewById(R.id.text_url);
        textUrl.setOnKeyListener(listener);
        textUrl.setText("http://google.com");

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public void goForward(View view) {
        if (webView.canGoForward()) {
            webView.goForward();
        } else {
            noti("맨 앞페이지 입니다.");
        }
    }

    private void noti(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        Snackbar.make(webView, str, Snackbar.LENGTH_SHORT).show();
    }
}
