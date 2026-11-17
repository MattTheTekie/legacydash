# 0.4.0-alpha / arcade and local upload

Source implementation only. Not compiled, emulator-tested or frame-tested here. API 17 configuration retained.

Existing word-family maze, mini crossword and daily five-letter guess remain in Word games. Added separate Drawing Maze, Maze Chase (Pac-Man-style), Falling Blocks (Tetris-style), and Pong. Original simple visuals and generated layouts, no official commercial game assets.

Drawing Maze: trace a continuous route through a generated maze, wall-constrained movement, backtracking, directional buttons and completion feedback. Maze Chase: directional/swipe controls, dots, three chasers, temporary corner power dots and win/loss. Falling Blocks: seven-piece shuffled bag, preview, rotation with basic horizontal kicks, row clearing, score, speed increase, soft/hard drops; not official Tetris rotation/scoring rules. Pong: drag left paddle, simple computer opponent, first to seven. Pause, restart and keyboard arrows supported. Sessions reset on leaving; score records saved locally.

QR image upload: native ZXing-generated code points to a temporary HTTP receiver on a selected private IPv4 interface and ephemeral port. Same reachable LAN required; client isolation/firewalls may block it. No Internet relay and no router forwarding. Random 128-bit session path plus upload header token, no CORS allowance, fifteen-minute expiry, receiver stops on page navigation/app pause. HTTP is unencrypted: link/image traffic can be observed on an untrusted LAN. Anyone with the link/token can upload; no account authentication or malware scanning.

Uploads are raw-body HTTP POSTs from the phone page, not multipart. Receiver limits headers, request time and file length; validates image dimensions without decoding full pixels; allows up to ten MiB and twenty megapixels per image, fifty files/one hundred MiB total. Private app storage; random server-generated filenames, no original filename paths, no network listing/download endpoint. One request handled at a time. Interrupted .part files deleted during handled requests, but app termination can leave a partial file counting toward quota.

Uploaded images gallery is local to the app, accessible from Home. Includes image viewing and confirmed deletion. Uploads are NOT automatically sent to Immich and are NOT yet connected to an idle screensaver or local slideshow. HEIC/AVIF may fail on older Android. No video upload.

ZXing core 3.3.3 (Apache-2.0) used for QR generation: https://github.com/zxing/zxing/tree/zxing-3.3.3 . Existing predict4java GPL-2.0 and distribution notes still apply. Release workflow still uses its original tag; obtain latest successful Actions artifact. Debug signing persistence remains unfinished.
