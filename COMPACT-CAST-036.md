# 0.36.0-alpha / compact Home casting card

The prior casting card stacked a long heading, receiver status, two paragraphs, a full-width 144dp QR and two links. Even with top-aligned children, that tall card determined the shared row's height, leaving blank space below the shorter Planetary data card and pushing the Today/photo overview down.

Home now uses a short Cast from phone heading, receiver label limited to two displayed lines (full accessibility description retained), a one-line BubbleUPnP/DLNA label and compact QR/details/setup actions. A 104dp QR sits beside the text only when the card's measured inner row has at least 270dp; otherwise it is hidden and Show QR remains available. No fixed-height spacer or large full-width QR is retained on Home. A short unverified HTTP APK notice remains visible. The pair keeps wrap-content heights and the same weather live-update container.

Full sender instructions, current receiver status, a larger 240dp QR and full unverified-download warning are available in a scrollable dialog reached from Show QR, the small QR or the warning. Receiver setup still targets the saved name field. Opening the APK still requires confirmation and uses the same exact user-supplied URL; this change does not verify, download or install the APK. Small inline QR scanning is not guaranteed, so the enlarged code and direct link remain alternatives.

The photo card size, image quality/cache, greetings, themes, receiver naming and floating pet are unchanged. This reduces the casting contribution to Home height; it does not promise that every Home section fits simultaneously on every screen/font size or remove normal scrolling.

No Android build, visual/device test or QR scan was run. Verify CI, the reported tablet gap/partially visible photo, wide/narrow/font-scaled layouts, inline-QR breakpoint, expanded QR/dialog scrolling, setup deep link, stale job handling and unchanged weather/photo updates.
