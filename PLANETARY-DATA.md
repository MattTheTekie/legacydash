# Planetary data / weather

Adds an icon tile on Home under Explore, opening native weather UI. Existing modules are preserved; this change is independent of the still-pending icons/album-selection commit. No version or manifest replacement is needed.

Uses public Open-Meteo geocoding and forecast HTTPS APIs with no embedded key or sign-up for personal/non-commercial use. Provider terms/rate limits still apply; this is not a claim of unlimited free commercial service.

User types a town and state/region or country (full names recommended), searches explicitly, and chooses one of up to ten returned matches. Saves selected place/coordinates locally. No GPS permission, automatic location tracking or default guessed city. Queries and chosen coordinates are sent to the provider to obtain results.

Imperial display: Fahrenheit temperature/apparent temperature/highs/lows; wind/gusts in mph; precipitation totals/amounts in inches; sea-level pressure converted from hPa to inHg. Humidity/cloud/probability are percentages; UV is dimensionless. Forecast times use location timezone from the response.

Current model conditions, weather-code icons, next twelve available hourly entries, seven-day highs/lows/precipitation/wind, today's sunrise/sunset and maximum UV. Current conditions are model-based, not asserted station measurements. Rain-chance labels mean precipitation probability as supplied by provider. Does not provide official severe-weather alerts or emergency warnings.

Forecast cache lasts fifteen minutes. Failed refresh can show older saved forecast with explicit stale/offline warning and local fetch timestamp; no silent live-data claim. Missing values display unavailable/dashes. Uses old-Android-compatible native views and existing networking; real-device TLS/API compatibility and layout remain untested. Original source and data at https://open-meteo.com/ and https://open-meteo.com/en/docs/geocoding-api .
