# Rigenera icone e schermate di avvio Android (pallone su fondo verde scuro, come l'icona iPhone).
# Uso: python3 scripts/genera-icone.py   (richiede Pillow)
import math
from pathlib import Path
from PIL import Image, ImageDraw

RES = Path(__file__).resolve().parent.parent / "android/app/src/main/res"
BG, LINE, INK, WHITE = (13, 25, 18), (36, 64, 47), (16, 21, 18), (255, 255, 255)


def ball(d, cx, cy, r, w):
    d.ellipse([cx - r, cy - r, cx + r, cy + r], fill=WHITE, outline=INK, width=w)
    pr = r * 0.42
    pts = [(cx + pr * math.sin(math.radians(a)), cy - pr * math.cos(math.radians(a))) for a in range(0, 360, 72)]
    d.polygon(pts, fill=INK)
    for a in range(0, 360, 72):
        s, c = math.sin(math.radians(a)), math.cos(math.radians(a))
        d.line([(cx + pr * s, cy - pr * c), (cx + r * s, cy - r * c)], fill=INK, width=w)


def draw(w, h, ratio, bg=BG, field=True, round_mask=False):
    k = 4  # disegno in grande e riduco, per bordi morbidi
    im = Image.new("RGBA", (w * k, h * k), bg)
    d = ImageDraw.Draw(im)
    cx, cy, r = w * k / 2, h * k / 2, min(w, h) * k * ratio
    if field:
        d.line([(0, cy), (w * k, cy)], fill=LINE, width=max(4, int(min(w, h) * k / 60)))
    ball(d, cx, cy, r, max(3, int(r / 14)))
    im = im.resize((w, h), Image.LANCZOS)
    if round_mask:
        m = Image.new("L", (w * k, h * k), 0)
        ImageDraw.Draw(m).ellipse([0, 0, w * k, h * k], fill=255)
        im.putalpha(m.resize((w, h), Image.LANCZOS))
    return im


for f in RES.glob("mipmap-*/ic_launcher.png"):
    n = Image.open(f).size[0]
    draw(n, n, 0.30).convert("RGB").save(f)
for f in RES.glob("mipmap-*/ic_launcher_round.png"):
    n = Image.open(f).size[0]
    draw(n, n, 0.30, round_mask=True).save(f)
for f in RES.glob("mipmap-*/ic_launcher_foreground.png"):
    n = Image.open(f).size[0]
    draw(n, n, 0.20, bg=(0, 0, 0, 0), field=False).save(f)  # dentro la zona sicura di 66/108
for f in RES.glob("drawable*/splash.png"):
    w, h = Image.open(f).size
    draw(w, h, 0.12).convert("RGB").save(f)
(RES / "values/ic_launcher_background.xml").write_text(
    '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n    <color name="ic_launcher_background">#0D1912</color>\n</resources>\n'
)
print("Icone e schermate di avvio aggiornate")
