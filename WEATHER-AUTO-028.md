# 0.28.0-alpha / automatic forecast cache

Native Open-Meteo forecasts automatically refresh when the saved location's cache reaches 15 minutes old while the dashboard activity is resumed. Fresh caches schedule the remaining lifetime instead of fetching on every screen change. Missing/invalid/other-location caches fetch on the next check. Resume checks for stale data; selecting a new location wakes the scheduler. Downloads run through the app's existing bounded, timed HTTP worker with no credentials or GPS added. Current, hourly and seven-day data and the existing imperial units/cache schema are retained.

This is a foreground lifecycle timer, not an exact background job or an always-running service. It stops scheduling when the app is paused/closed and resumes checking on return. An already-running request may finish and cache its result after pause, but does not update hidden views. OS suspension, network latency and failures mean exactly-on-the-second updates are not guaranteed. The independent WeatherStar website/kiosk is unchanged.

Home updates only the weather card's contents, without rebuilding Home or resetting the photo preview, notes, games or gallery. If the native read-only weather detail screen is currently open, it redraws from the cache and restores its scroll position. The location-search screen is not replaced while the user types.

Only one automatic request runs at a time. Automatic failures preserve the previous cache and timestamp; retry attempts are limited to once per 15 minutes per current location rather than looping while offline. Stale caches remain labelled. Manual Refresh remains available; a manual and automatic request may overlap, but automatic completion does not overwrite a newer manually saved cache. Results are discarded when the saved coordinates have changed.

No new Android permissions, service, boot receiver, work library or API key are added. Location selection still sends coordinates to the existing Open-Meteo endpoint under its existing provider terms.

No Android build or device test was run. Verify CI, first setup, 15-minute boundary, Home left idle, weather detail scroll, stale-on-resume, missing cache, location changes during requests, offline retry timing, manual refresh overlap and unchanged casting/photo/calendar behavior.
