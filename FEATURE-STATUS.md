# Native feature update

Untested source update. Not a claim of build success or device compatibility.

Implemented in source: native Immich URL/email/password login, memory-only bearer session, album selection, paged asset browsing, preview photos, previous/next, photo swipes and optional 10-second slideshow. Video uses authenticated download into private temporary cache, capped at 64 MB; no direct streaming yet. Auto rotation is limited to the current 40-item page. Unsupported codecs and old TLS remain device limitations. HTTP credentials require explicit opt-in. OAuth/passkeys, persistent secure sessions, native logout/revoke, full-resolution zoom, offline library cache and idle screensaver are not implemented. No album mutation or uploads.

Native RSS reader includes NASA and BBC World source URLs, custom RSS, title/summary/source/date/creator metadata, image headers where a feed provides a compatible image, and article opening. Feed images and article links are untrusted remote content; use trusted feeds only. Atom and image extraction from HTML descriptions are not implemented.

Drawing: colour palette, brush width, opacity, eraser, four-stroke undo, PNG save. Colouring: house, flower, fish, circle pattern. Outlines remain above the paint so erasing cannot remove them. No flood-fill or redo. Pet: original static pixel creature with existing local feed/train mechanics, not actual Digimon sprites or an animated evolution sprite set.

Space/cameras have built-in official/provider page destinations, so no user-hosted website is required. They are NOT native extracted live feeds; playback can fail in old WebView. No stream access controls are bypassed. Native ISS telemetry and reliable direct camera playback remain unfinished.

Home subtitles are inherited from the earlier dashboard and may still say web panel even though Immich and News now route to native screens. Existing README describes the earlier baseline; this file describes the new entry point. The workflow still only creates v0.1.0-alpha once; subsequent APKs are workflow artifacts. Ephemeral debug signing means update installs may fail across runs. Do not uninstall without backing up local data.
