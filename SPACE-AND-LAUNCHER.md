# Space and launcher update

Source implementation, not build- or device-tested. Version 0.2.0-alpha; existing release workflow still uses its original v0.1.0-alpha tag, so take new builds from Actions artifacts until release automation is revised.

Launcher: installed app icons, searchable adaptive app grid, system pictograms on dashboard cards and a bundled XML app icon. Icons are lightweight system drawables, not a commissioned icon set.

Space: ISS 25544 and Tiangong/Tianhe 48274 use CelesTrak TLEs and predict4java SGP4/SDP4 predictions. TLEs cached for two hours; cached fallback may be older. Map updates every ten seconds while open, with past 40 / next 90 minute ground tracks, coordinates, altitude and raw TLE epoch. Map is a fixed world overview, not an interactive zoom/pan map. Device clock must be correct. Not live measured telemetry or navigation-grade output. Moving away or pausing stops updates; reopen Space after resume to restart.

Map: four visible OpenStreetMap zoom-1 tiles, identified User-Agent, seven-day local cache and on-screen attribution. OSM data ODbL; https://www.openstreetmap.org/copyright ; tile policy https://operations.osmfoundation.org/policies/tiles/ .

Crew: Open Notify public HTTP endpoint, source roster grouped by craft, total count and local cache timestamp. Fifteen-minute cache, stale fallback; roster may be incomplete/outdated. No fabricated nationality, mission duration or launch counts.

APOD: new science.nasa.gov WordPress APOD JSON endpoint, latest published date, image where supplied, explanation and original page/credits. Six-hour cache; no NASA key needed for this endpoint. Images over ten MB may fail. Video entries open the original page.

Cameras: existing SkylineWebcams provider destinations open in-app with WebChromeClient fullscreen video handling. This does not extract private media URLs, bypass ads/access rules, or guarantee old-WebView playback. Not a native camera-feed directory yet.

## Third-party dependency and source availability
predict4java com.github.davidmoten:predict4java:1.3.1 is GPL version 2.0, not MIT. Source: https://github.com/davidmoten/predict4java/tree/1.3.1 and source artifact https://repo.maven.apache.org/maven2/com/github/davidmoten/predict4java/1.3.1/predict4java-1.3.1-sources.jar . License: https://www.gnu.org/licenses/old-licenses/gpl-2.0.html . Combined binary distribution must comply with GPL-2.0, including applicable license notices and complete corresponding-source obligations; this notice alone is not a complete distribution compliance package. Do not represent the combined APK as MIT-only. Review/include complete licenses and corresponding dependency source before redistributing outside the current development account. This change does not assert ownership of or relicense third-party assets or data.
