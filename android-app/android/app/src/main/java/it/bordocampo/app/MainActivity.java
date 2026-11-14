package it.bordocampo.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import androidx.activity.OnBackPressedCallback;
import com.getcapacitor.BridgeActivity;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class MainActivity extends BridgeActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Schermo sempre acceso: a bordo campo il cronometro deve restare visibile.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Tasto indietro: la web app chiude il foglio aperto o torna al menu (window.bcBack);
        // solo dal menu principale si esce dall'app.
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                bridge.getWebView().evaluateJavascript("window.bcBack ? window.bcBack() : 0", value -> {
                    if (!"1".equals(value)) {
                        setEnabled(false);
                        getOnBackPressedDispatcher().onBackPressed();
                        setEnabled(true);
                    }
                });
            }
        });

        openFile(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        openFile(intent);
    }

    // File MisterBook arrivato da WhatsApp (apri con / condividi): lo legge e lo passa alla web app
    // (window.bcStaffFile), che mostra il riepilogo. Riprova finché la pagina non è pronta.
    @SuppressWarnings("deprecation")
    private void openFile(Intent intent) {
        if (intent == null) return;
        Uri uri = null;
        if (Intent.ACTION_VIEW.equals(intent.getAction())) uri = intent.getData();
        else if (Intent.ACTION_SEND.equals(intent.getAction())) uri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (uri == null) return;
        String text;
        try (InputStream in = getContentResolver().openInputStream(uri)) {
            if (in == null) return;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int n, total = 0;
            while ((n = in.read(buf)) > 0) {
                total += n;
                if (total > 2_000_000) return; // un file MisterBook è di pochi KB
                out.write(buf, 0, n);
            }
            text = new String(out.toByteArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return;
        }
        final String js = "window.bcStaffFile ? window.bcStaffFile(" + JSONObject.quote(text) + ") : 0";
        deliver(js, 0);
    }

    private void deliver(String js, int attempt) {
        if (attempt > 40 || bridge == null || bridge.getWebView() == null) return;
        bridge.getWebView().evaluateJavascript(js, value -> {
            if (!"1".equals(value)) handler.postDelayed(() -> deliver(js, attempt + 1), 500);
        });
    }
}
