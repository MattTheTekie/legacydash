# 0.24.0-alpha / Back stops the current cast

Android Back, the fullscreen player's Back button and its Stop button now share an idempotent exit path. The current MediaPlayer is reset/released immediately, preparation/image callbacks are invalidated by generation, the displayed media reference is cleared, and all current renderer transports are marked STOPPED with wantPlay=false and an empty URI/metadata. Pending controller callbacks posted before the exit are discarded using an exit epoch. The stopped generation is dismissed to prevent the dashboard immediately reopening it.

This stops current audio/video and dismisses a photo, including when media was still preparing. A photo HTTP worker already in progress may finish its bounded network operation, but its result is discarded and cannot present or restart that cast.

The foreground receiver service, discovery sockets, notification, receiver preference, network scanning, rotation choices and gallery quiet-transitions behavior remain. A sender must set a new media URI to start again after this explicit local exit. A genuinely new incoming cast can still open normally; this is not a persistent sender block. Remote Stop/completion still does not destroy the viewer between album items.

Home/app switching and configuration changes do not invoke this Back-specific stop path; dismissing an open rotation/compatibility dialog alone is not exiting the player.

No Android build/device test was run. Verify CI and Back during audio/video/photo playback and preparation, rapid Back taps, queued Play, no automatic reopening of stopped content, a subsequent fresh cast, and unchanged rotation/slideshow behavior.
