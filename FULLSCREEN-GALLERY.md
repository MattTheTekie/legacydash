# 0.5.0-alpha / saved Immich and fullscreen frame mode

Source implementation only. No compile success, security audit or hardware test is claimed. Existing games and upload receiver retained; API 17 remains the minimum.

## Saved sign-in
Remembers Immich session token, not account password. Android API 18+ uses an AndroidKeyStore RSA key to wrap random AES/HMAC keys. Session payload uses AES-CBC encryption with encrypt-then-HMAC authentication. On API 17 the user chooses a separate unlock passphrase (minimum ten characters), using PBKDF2-HMAC-SHA1 with random salt and 120,000 iterations to derive AES/HMAC keys. No plaintext token fallback. Keystore support and device credential configuration can cause persistence to fail; errors are shown rather than silently downgrading.

API 18+ attempts automatic restoration at app startup and when entering Immich. API 17 requires passphrase unlock after process restart. Expired/revoked server sessions still require fresh login; no refresh-token assumption. Forget saved login clears this app's local encrypted blob/session, not the server-side session. Uninstalling, clearing app data, or losing the device key removes the saved login. Existing debug-key/update caveats still apply.

## Fullscreen gallery
Native player replaces the dashboard view completely; no sidebar. Hides system bars as far as Android version permits and keeps screen awake. Android 4.2 navigation bars may reappear on interaction; immersive-sticky is used only on API 19+.

Remote Immich library or last selected album is collected across API pagination, up to 5,000 items per start. Local QR-uploaded photos are a separate selectable source. Existing library-item taps now open the fullscreen player. Start Gallery begins automatic rotation; swipe previous/next works while playing or paused. Tap shows/hides overlays. Buttons: Previous, Play/Pause, Next, Mute/Unmute, Exit; video seek bar. Configurable photo duration, shuffle and muted video default. Videos advance on completion. Backgrounding pauses playback.

Videos download incrementally to private temporary disk files before playback, maximum 256 MiB per item; not true network streaming or instant-start video. Tokens stay in HTTP headers, not URLs; authenticated transfers and login POSTs do not follow redirects. Frames still need compatible codecs/TLS. Images use server previews, not original-resolution pan/zoom. Failed media may be skipped after 3.5 seconds during autoplay. Not a validated offline cache; abrupt process termination may leave gallery-video cache files.

## Idle mode
Optional inactivity launch from this app's Home screen only, configurable 30-3600 seconds, disabled by default. Does not interrupt games/notes/drawing, does not operate over other apps, and is not an Android DreamService. A locked API 17 saved session must be unlocked before unattended remote idle playback. Uploads can be used without Immich.

Current release automation still creates only its original release tag. Use the latest successful Actions artifact for this version. Remembered session survives app restart only if app data and signing identity remain intact.
