# Bordocampo

App per iPhone per seguire le partite di calcio della propria squadra: cronometro, conto alla rovescia, gol, ammonizioni ed espulsioni, risultato finale con marcatori e minuti.

È una web app (HTML + JavaScript, nessuna dipendenza): si apre in Safari e si installa sulla schermata Home, dove funziona come un'app anche senza connessione. I dati restano salvati sul telefono.

## Installazione su iPhone

1. Pubblica la cartella con GitHub Pages (Settings → Pages → branch, cartella root): l'app sarà su `https://<utente>.github.io/INFORMATICA/partita/`.
2. Apri l'indirizzo con **Safari**.
3. Tocca **Condividi** → **Aggiungi alla schermata Home**.

## Come si usa

- **Rosa**: nella scheda **La mia squadra** inserisci il nome della squadra e i giocatori (anche più nomi insieme separati da virgola). Nella scheda **Avversari** crei una rosa per ogni squadra avversaria: viene proposta in automatico quando giochi contro una squadra con lo stesso nome.
- **Salvare e caricare le rose**: in Rosa, nel riquadro **Salva e carica**, salvi la rosa aperta in un file col nome della squadra (es. «Rosa Aurora.json») oppure tutte le rose insieme, e con **Carica rose da file** le ricarichi più tardi, anche su un altro dispositivo. Su iPhone il file si salva in File, su Android tramite la condivisione (Drive, File…), sul PC nella cartella Download. Si può caricare anche un file di testo con un nome per riga.
- **Nuova partita**: avversario (scelto con un tocco tra le squadre salvate in Rosa → Avversari, oppure scritto se è nuovo), casa/trasferta, numero di tempi e minuti per tempo.
- **Avvia / Pausa / Riprendi**: il cronometro mostra il minuto di gioco (es. 2° tempo parte da 45:00), sotto c'è quanto manca alla fine del tempo. Allo scadere parte il recupero (+mm:ss) e suona un fischio. I pulsanti ±10″ e ±1′ correggono il cronometro.
- **Gol**: tocca il pallone sotto il nome della squadra. Il minuto viene registrato subito; poi, se vuoi, scegli il marcatore dalla lista o scrivi un nome nuovo, e il tipo (su azione, rigore, autogol). Puoi anche chiudere senza dettagli.
- **Cartellini**: tocca il cartellino giallo o rosso sotto la squadra. Il secondo giallo allo stesso giocatore viene segnato come espulsione.
- **Formazioni**: nella schermata della partita, nel riquadro **Formazioni**, scegli la squadra (la tua o l'avversaria) e tocca **Prepara la formazione**. Scegli quanti giocano (5, 7, 8, 9 o 11) e il modulo, poi tocca un ruolo sul campo e il giocatore. Chi metti in campo sparisce dall'elenco; se lo togli dal campo torna in panchina. Sotto il campo scegli chi è in **panchina** (a disposizione) e chi non è convocato. Per l'avversario puoi scrivere tutti i nomi insieme, separati da virgola: rosa e formazione dell'avversario vengono ricordate e riproposte la volta che lo incontri di nuovo. Le formazioni si aggiornano con cambi, gol e cartellini e compaiono anche nella diretta.
- **Scegliere i giocatori negli eventi**: in gol, cartellini e cambi compaiono sempre i nomi della rosa della squadra, divisi in gruppi (in campo, panchina, altri della rosa); con la formazione pronta puoi anche toccare il giocatore sul campo. La ricerca filtra i nomi; se cerchi un nome che non c'è compare il pulsante «Aggiungi … alla rosa». Se la rosa della squadra è vuota, il foglio offre di inserirla subito.
- **Cambi**: tocca il pulsante con le due frecce sotto la squadra. Il minuto viene registrato subito; poi scegli chi entra e chi esce dalla lista o scrivi un nome nuovo. I cambi compaiono nella cronaca, nel risultato finale e nella diretta.
- **Fine tempo / Fine partita**: a fine partita vedi il risultato con parziali, marcatori e minuti, ammoniti ed espulsi. Puoi copiarlo o condividerlo (WhatsApp, ecc.).
- **Correggere**: ogni evento si corregge toccandolo nella cronaca, anche a partita finita: giocatore, tipo, minuto (si può scrivere, es. 38 o 45+2), squadra (pulsante «Assegna a …») oppure Elimina. Nel riepilogo di una partita finita, **Correggi il risultato** aggiunge gol, ammonizioni, espulsioni e cambi dimenticati.
- **Diretta per i genitori**: nella schermata della partita tocca **Crea link della diretta** e manda il link nel gruppo della squadra. Chi lo apre vede risultato, minuto, marcatori e cartellini aggiornati in tempo reale, senza installare niente (pagina `diretta.html`).

## Come funziona la diretta

L'app pubblica lo stato della partita su [ntfy.sh](https://ntfy.sh), un servizio gratuito di messaggi che non richiede account, in un canale con un nome casuale lungo e difficile da indovinare. La pagina `diretta.html` legge quel canale in tempo reale. ntfy.sh conserva i messaggi per 12 ore, poi il link smette di funzionare.

Chi conosce il link può vedere la partita, compresi i nomi dei giocatori: condividilo solo nel gruppo della squadra. Se il campo non ha segnale, l'app riprova da sola e la partita resta comunque salvata sul telefono.

## Android

L'app Android è in [`../android-app`](../android-app).
