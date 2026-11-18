# Sky Family

Early native Android 4.2/API ? dashboard prototype for Skylight SKY002. Untested on hardware. Not a finished product.

## Included
Installed-app launcher; local calendar entries and dated notes; finger drawing, one colouring outline and PNG saving; small repeating daily word puzzle; original text-only feeding/training pet; configurable web panels for Immich, ISS, news and camera pages. Books and local media open in separately installed readers/players.

## Important limitations
This checked-in initial version is smaller than the earlier chat ZIP: it does NOT include its local slideshow viewer. No Immich API client, mixed-media slideshow, idle screensaver, native ISS telemetry, RSS feed reader, bundled live cameras, calendar sync, built-in ebook reader, worksheet annotation, undo/fill, parental lock, or licensed Digimon assets. Modern websites may fail in the old WebView. Setting an ISS URL does not integrate the referenced repository.

## APK
GitHub Actions builds a debug APK on each main push, uploads a run artifact and creates the v0.1.0-alpha prerelease once, only after a successful build. Later main builds remain run artifacts and do not replace that release. The debug signing key is ephemeral on hosted runners, so future APK updates may require uninstalling the previous build (deleting its local data). Production signing and migration are not implemented.

Download the APK from Releases after a successful run. Install with `adb install path/to/app.apk`. Do not change the default launcher until tested. No root or firmware flashing required by this project. Device installation access is not yet verified.

## Local build
Requires JDK 17, Gradle 8.9 and Android SDK platform 35. Run `gradle :app:assembleDebug`. No Gradle wrapper included.

## Security and data
Do not use sensitive credentials on this old device without review. Use only trusted web pages; JavaScript is enabled. No native JS bridge; web file/content access disabled; TLS errors not bypassed. HTTP is allowed for trusted LAN use but unencrypted. No API keys in URL fields. Notes/calendar/pet use private preferences; no backup is provided. App removal deletes private data. PNG drawings are in app-specific external storage.

## ISS reference
https://github.com/compscirunner/portal-iss-tracker
No upstream code or assets bundled in this initial version.
