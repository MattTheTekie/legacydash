# 0.21.0-alpha / cast rotation

The standalone cast player previously never requested an orientation and had no rotation control. It now defaults to SCREEN_ORIENTATION_FULL_SENSOR for cast-specific automatic device rotation. This is intentionally independent from the dashboard orientation preference and can use the device sensor even when normal user auto-rotate is locked, subject to platform/device policy. Hardware without a usable sensor still needs a manual choice.

Tap to reveal controls, then use Rotate for Automatic/follow sensor, Automatic/match media, Portrait, Landscape, Reverse portrait or Reverse landscape. Match media uses current decoded photo/video dimensions, accounting for any manual picture turn; it uses sensorLandscape/sensorPortrait so both directions within that orientation remain possible. Square, audio-only or not-yet-sized media falls back to full sensor.

Turn 90 degrees rotates the actual cast photo/video view, independently of the screen orientation. Rotate also includes Reset picture turn. The cast-specific mode and quarter turns persist under cast.rotation.mode/turns in the existing sky preferences; saved activity state preserves the current choices and controls deadline. No note/artwork/media data is modified.

Video fitting transforms the TextureView's default stretched coordinates into rotated, centered, uniformly scaled media bounds. Photo fitting uses an ImageView matrix with the same rotation and uniform fit. Odd quarter turns swap the fitted dimensions. Root layout, video size polling, texture resize and configuration changes update fitting without revealing controls or resetting the user-interaction deadline. Existing manifest orientation/screenSize handling and service-owned playback remain; there is no explicit player reload or seek on rotation. Android may still replace the underlying surface during a device rotation.

The toolbar scrolls horizontally on narrow displays; Rotate and Turn 90 degrees may require a horizontal swipe. The rotation dialog suspends auto-hide while open. Album transitions do not auto-show the toolbar. This does not add EXIF parsing, codec support, receiver-side transcoding, OS rotation-policy exemptions or guaranteed behavior on every device/multi-window mode.

Source-only: no Android build/device tests were run. Verify CI, sensor rotations including reverse modes, a fixed/sensorless frame, manual picture turns for both photos and videos, portrait/landscape media albums, aspect fit, audio-only casts, restoration and unchanged hidden-controls behavior.
