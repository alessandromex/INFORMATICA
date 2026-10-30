# Bordocampo per Android

L'app Android è la stessa web app di [`../partita`](../partita) impacchettata con [Capacitor](https://capacitorjs.com): funziona senza connessione, tiene lo schermo acceso durante la partita, usa il pannello di condivisione di Android e il tasto indietro chiude i fogli aperti o torna al menu.

## Installare sul telefono

Scarica l'ultima versione da qui, direttamente dal telefono Android:

**https://github.com/alessandromex/INFORMATICA/releases/latest/download/Bordocampo.apk**

Apri il file scaricato. La prima volta Android chiede di consentire l'installazione da questa fonte: consentila e tocca **Installa**. Gli aggiornamenti si installano sopra la versione precedente senza perdere partite e rosa.

## Come viene creato l'APK

A ogni modifica di `partita/` o `android-app/` su `main`, la GitHub Action [`.github/workflows/android.yml`](../.github/workflows/android.yml) compila l'APK e lo pubblica in una nuova release. Si può anche avviare a mano da **Actions → App Android → Run workflow**.

Per compilarlo sul computer servono Node 22, JDK 21 e l'Android SDK (platform 36):

```bash
cd android-app
npm ci
npm run apk        # APK in android/app/build/outputs/apk/release/
```

`npm run sync` copia `../partita` in `www/` e aggiorna il progetto Android; `python3 scripts/genera-icone.py` rigenera icone e schermata di avvio.

## Firma

La chiave di firma non è nel repository. La GitHub Action la prende da due segreti del repository (**Settings → Secrets and variables → Actions**):

- `ANDROID_KEYSTORE_BASE64`: il file della chiave (alias `bordocampo`) codificato in base64;
- `ANDROID_KEYSTORE_PASSWORD`: la sua password.

Con i segreti impostati ogni APK ha la stessa firma e si installa sopra il precedente senza perdere i dati. Senza segreti l'APK viene firmato con una chiave temporanea: funziona, ma per installare una versione nuova bisogna prima disinstallare la vecchia, e così si perdono le partite salvate.

Per creare una chiave:

```bash
keytool -genkeypair -keystore bordocampo.keystore -alias bordocampo -keyalg RSA -keysize 2048 -validity 10000
base64 -w0 bordocampo.keystore    # valore di ANDROID_KEYSTORE_BASE64
```

Conserva il file della chiave in un posto sicuro: se va perso, l'app firmata con una chiave nuova non può aggiornare quella già installata.
