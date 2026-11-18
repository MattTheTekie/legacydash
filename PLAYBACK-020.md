# 0.20.0-alpha / quiet media changes

Both playback implementations explicitly revealed UI on each item: FramePlayer.show() called controls(), and CastPlayerActivity's generation-change branch called revealControls(). Those automatic calls are removed. Immich/uploaded-photo autoplay galleries start with caption/controls hidden; manual single-item opens retain initially visible controls. Automatic advances and swipe changes preserve current visibility and the existing hide timer rather than resetting it. Touch, control actions, pause and genuine gallery load errors can still reveal UI.

The cast viewer starts hidden and keeps user-revealed controls on a user-interaction deadline, independent of media generations. Item changes, preparation, first-frame callbacks and resume do not reveal or extend the toolbar. The first reveal gesture is consumed through UP/CANCEL to avoid activating a newly shown button. Genuine errors/stopped-service notices remain visible.

Cast Stop/completion received from the sender no longer destroys the viewer: album controllers can stop/replace media without flashing the dashboard and re-opening a fresh player. It remains fullscreen between items and at the end until Back or the local Stop button is used. Back keeps background receiving/playback behavior; local Stop stops media and exits. A completed item or sender Stop can therefore leave the last frame visible rather than auto-returning Home.

The previous photo is retained during the next photo load and while a following video waits for a frame. Routine loading text is suppressed once content has already been presented; genuine errors are not hidden. This does not promise gapless video decoding or eliminate network delays.

No Android build or device test was run here. Verify CI, rapid photo albums, photo/video transitions, short-interval slideshows, sender Stop/Play between items, local/Immich galleries, touch/seek timers, errors, Back/Stop and background/resume.
