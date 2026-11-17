# 0.8.0-alpha / icon controls and visual album selection

Source update only. No compilation, device, visual or Immich-server compatibility test is claimed. API 17 and all existing modules retained.

## Icons
Drawn control icons with short labels replace more text-only controls: header fullscreen/rotation, studio toolbar, action rows, fullscreen gallery controls, and native PDF toolbar. Labels/content descriptions remain for clarity; dynamic play/pause and mute labels update their icons. Home adds an album/play/uploaded-photo quick strip. Existing navigation/home icons retained; this is not a claim that every legacy widget is redesigned.

## Album picker
Native searchable album-cover grid, 24 cards per page, names and server-reported item counts, tap selection, Apply/Browse and Play actions. Explicit Whole library option; attempting Apply/Play with no chosen album asks the user to choose or explicitly use Whole library. Up to twelve selected albums. Selection is stored locally per server URL, migrated from the previous single-album preference when present, and cleared on local sign-out. No album edits or server mutations. Account permissions determine visible shared albums.

Remote browser and fullscreen/Home-idle gallery use the saved selection. Each selected album is searched separately and results are combined/deduplicated by asset ID, avoiding reliance on multi-ID intersection/union semantics. Whole-library mode searches without an album filter. Supports next-page or cursor response fields where returned; limited to 5,000 image/video records and 100 metadata requests per refresh. Server API versions can still vary. Selection metadata cached in memory for five minutes; Refresh fetches again. If bounded, browser displays a notice.

Media browser now shows photo/video thumbnail cards, 24 per page; taps enter fullscreen at the item, using the collected set rather than only the visible page. Thumbnail memory cache bounded to about six MB. Gallery uses existing preview images and temporary video downloads, not a new streaming/transcoding implementation. Local QR-photo gallery remains a separate source. Album choice does not change that local source.

Existing encrypted-login/Android-4.2 unlock behaviour preserved. If an expired session is forgotten, album selection is cleared with it. Server-URL scoping is not multi-account profile management. Existing source/release/signing, PDF security and dependency-license notes apply; use latest successful Actions artifact.
