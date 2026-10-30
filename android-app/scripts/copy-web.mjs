// Copia la web app (../partita) in www/, la cartella che Capacitor impacchetta nell'APK.
// Il service worker e il manifest servono solo alla versione web.
import { cpSync, rmSync, mkdirSync } from "node:fs";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";

const root = join(dirname(fileURLToPath(import.meta.url)), "..");
const src = join(root, "..", "partita");
const out = join(root, "www");
const skip = new Set(["sw.js", "manifest.webmanifest", "README.md"]);

rmSync(out, { recursive: true, force: true });
mkdirSync(out);
cpSync(src, out, { recursive: true, filter: (p) => !skip.has(p.slice(src.length + 1)) });
console.log("Web app copiata in www/");
