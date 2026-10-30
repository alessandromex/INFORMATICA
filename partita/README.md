# Bordocampo

App per iPhone per seguire le partite di calcio della propria squadra: cronometro, conto alla rovescia, gol, ammonizioni ed espulsioni, risultato finale con marcatori e minuti.

È una web app (HTML + JavaScript, nessuna dipendenza): si apre in Safari e si installa sulla schermata Home, dove funziona come un'app anche senza connessione. I dati restano salvati sul telefono.

## Installazione su iPhone

1. Pubblica la cartella con GitHub Pages (Settings → Pages → branch, cartella root): l'app sarà su `https://<utente>.github.io/INFORMATICA/partita/`.
2. Apri l'indirizzo con **Safari**.
3. Tocca **Condividi** → **Aggiungi alla schermata Home**.

## Come si usa

- **Rosa**: inserisci il nome della squadra e i giocatori (anche più nomi insieme separati da virgola).
- **Nuova partita**: avversario, casa/trasferta, numero di tempi e minuti per tempo.
- **Avvia / Pausa / Riprendi**: il cronometro mostra il minuto di gioco (es. 2° tempo parte da 45:00), sotto c'è quanto manca alla fine del tempo. Allo scadere parte il recupero (+mm:ss) e suona un fischio. I pulsanti ±10″ e ±1′ correggono il cronometro.
- **Gol**: tocca il pallone sotto il nome della squadra. Il minuto viene registrato subito; poi, se vuoi, scegli il marcatore dalla lista o scrivi un nome nuovo, e il tipo (su azione, rigore, autogol). Puoi anche chiudere senza dettagli.
- **Cartellini**: tocca il cartellino giallo o rosso sotto la squadra. Il secondo giallo allo stesso giocatore viene segnato come espulsione.
- **Fine tempo / Fine partita**: a fine partita vedi il risultato con parziali, marcatori e minuti, ammoniti ed espulsi. Puoi copiarlo o condividerlo (WhatsApp, ecc.).
- Ogni evento si corregge toccandolo nella cronaca, anche a partita finita.
- **Diretta per i genitori**: nella schermata della partita tocca **Crea link della diretta** e manda il link nel gruppo della squadra. Chi lo apre vede risultato, minuto, marcatori e cartellini aggiornati in tempo reale, senza installare niente (pagina `diretta.html`).

## Come funziona la diretta

L'app pubblica lo stato della partita su [ntfy.sh](https://ntfy.sh), un servizio gratuito di messaggi che non richiede account, in un canale con un nome casuale lungo e difficile da indovinare. La pagina `diretta.html` legge quel canale in tempo reale. ntfy.sh conserva i messaggi per 12 ore, poi il link smette di funzionare.

Chi conosce il link può vedere la partita, compresi i nomi dei giocatori: condividilo solo nel gruppo della squadra. Se il campo non ha segnale, l'app riprova da sola e la partita resta comunque salvata sul telefono.

## Android

L'app Android è in [`../android-app`](../android-app).
