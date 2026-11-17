# 0.11.0-alpha / navigation and weather diagnostics

Launcher now HubActivity, extending SeasonActivity and retaining its themes, birthdays, Santa simulation, DLNA, Immich/PDF/art/game implementations and all local data. Version code 11. No files or saved URL preferences deleted.

Games browser uses native selected All / Arcade / Words tabs and a two- or three-column launch-card grid for Snake, Maze Chase, Falling Blocks, Pong, Drawing maze, Word maze, Mini crossword and Daily five-letter guess. Game engines unchanged.

Settings no longer calls the legacy empty-URL form. Short sections link to themes/birthdays, gallery/idle playback, rotation, real Immich connection, weather location/diagnostics, DLNA, Uploads, installed apps and device settings. Optional custom RSS remains inside News.

Uploads is the shared destination for Home, Settings, old share/QR actions and bookshelf import actions. Contains phone QR receiving, on-device book picker, images, bookshelf and existing saved artwork. Phone QR still accepts images/EPUB/PDF/UTF-8 TXT only, not videos or editable projects. With one private IPv4 interface, QR opens directly; otherwise the user picks an address. Existing trusted-LAN, unencrypted-transfer and legacy-PDF warnings/limits remain. Home no longer renders separate receive/images/gallery-settings action buttons; photo-gallery viewing shortcuts are retained.

Weather requests to the two exact Open-Meteo hosts use an isolated HTTPS JSON client with an honest app User-Agent and Accept header, size/time limits, bounded JSON error details, no automatic redirects and no Immich credential forwarding. Both geocoding and forecast errors name the provider and stage. Latest request outcome/timestamp for each stage is stored privately for the Weather connection details page. Existing fifteen-minute forecast caching and labelled stale fallback retained.

403 root cause is NOT confirmed: no access to the frame's response headers/body or network. This change improves request headers and diagnostics; it cannot promise to remove a provider/IP/network block. No rate-limit bypass, alternate-domain/proxy rotation, HTTP downgrade, TLS bypass or simulated weather. Provider terms: https://open-meteo.com/en/terms . Weather detail screen explains 403 and offers explicit retry or location selection.

Source changes only. Build, device rendering, QR import, game tab interaction and on-frame weather connectivity remain to be tested. Existing build/publish workflow is unchanged and publication depends on CI success.
