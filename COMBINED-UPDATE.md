# Combined app update / 0.10.0-alpha

One final app version includes the already committed UI/icons, Immich album selection, keyless imperial Planetary data, PDF/ebook library, editable art, games and galleries, plus the previously reviewed DLNA receiver and these additional seasonal/QR features. The reviewed receiver commit and consolidation commit are underlying source changes on main. Repository inspection found no other branch or open PR to merge/delete. No other existing feature is intentionally removed.

Source only: no successful compilation, device/controller test, security audit or visual acceptance test is claimed. Build and release publication remain conditional on CI success. API 17 support is declared, not verified on hardware.

## Themes and birthdays
Spring, summer, fall, winter, Christmas, Halloween, St. Patrick's Day and birthday palettes. Automatic local-date mode or manual selection. Season hemisphere can follow saved weather latitude or be chosen; without either, Northern Hemisphere is the stated default. Auto display windows: Christmas Dec 1-26, Halloween Oct 15-31, St. Patrick's Mar 10-17; an exact local birthday date takes priority. These are presentation rules, not live event data.

Themes recolour Home cards, app shell, navigation, headings/action backgrounds. They do not claim every embedded provider page, game graphic or native widget is fully themed. Optional leaves, rain, snow and snowmen-on-snow decorative Home banners; motion off by default, bounded to roughly fifteen frames/second when shown. No decoration drawn over reading, drawings, video or casting. Up to fifty local month/day birthday entries, no birth year required, removable and not uploaded. Uninstall/clear-data loses local choices and birthdays.

## Santa
Countdown to next December 25 from device local date. Journey view is a clearly labelled fictional 90-second preview animation with manual scrub, not real geography, measured positions or live NORAD data. Official external link: https://www.noradsanta.org/ . NORAD confirms the seasonal Santa mission is on December 24: https://www.norad.mil/NORAD-Tracks-Santa/ . No invented live tracker, scraped position API, or implied official affiliation.

## QR books and images
The receiver page now accepts images plus EPUB, PDF and UTF-8 TXT from a same-LAN browser and imports books into the existing bookshelf with metadata/covers when available. Uses the existing private library, native PDF renderer and EPUB/TXT parser. Image previews remain in Uploaded images, separate from Immich. No book files are uploaded to any cloud by this feature.

HTTP is unencrypted; trusted LAN only. Random session URL plus header token, no CORS allowance, fifteen-minute monotonic expiry, explicit Stop and page/pause shutdown. Anyone with the session URL/token on the reachable LAN can upload. Bounds: images ten MiB/twenty MP, books fifty MiB (TXT parser eight MB), about 250 MB/100-book library, fifty images/100 MiB image gallery. Server-generated filenames; supplied original name used as display metadata only. Validates book/container/image structure, not malware safety. Legacy PDF security warning still applies. EPUB DRM/encrypted resources and complex layout limitations remain.

QR HTML uses safe DOM status messages rather than embedded newline escapes and sends original filename as an encoded header. Active upload sockets close on Stop. App-kill can still leave a temporary partial file; this is not a hardened server. No video QR upload, no automatic Immich import, no cloud relay.

DLNA remains opt-in/foreground and unauthenticated on the LAN, per DLNA-RECEIVER.md. Controller compatibility and codecs are unverified; no Chromecast/AirPlay/Miracast claims. All previous signing, data-preservation and legacy/GPL dependency notices remain applicable. Use the release built from the final 0.10.0-alpha commit, not an earlier intermediate build.
