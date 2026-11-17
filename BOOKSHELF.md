# 0.6.0-alpha / bookshelf and fullscreen creative defaults

Source implementation only. Not compiled, emulator-tested or device-tested here. API 17 target retained.

Books & school now opens a dedicated bookshelf. EPUB and UTF-8 TXT imports are copied into private app storage so later reading does not depend on temporary file grants. Search title/author, recent-read ordering, Continue Reading, two/three-column cover cards, saved text progress bars, chapter/page indicators and twenty-book shelf pages. EPUB manifest cover-image or legacy cover metadata is used when available; coverless books and TXT show generated title tiles. Long-press removal deletes only the app's copy and local progress.

Native fullscreen text reader: EPUB spine order and TXT sections, chapter selector, previous/next, swipe/tap page turns, font sizes 14-36, saved chapter/character offset, and reading progress. Pages are calculated for current font and screen size; displayed page count is per chapter, not print-edition page numbering. Overall percentage measures text position, not time spent reading. TXT sections are chunks up to 60,000 characters. EPUB chapters are labelled by ordinal, not parsed NCX/nav titles. Font and orientation changes repaginate.

This is a text-first reader: EPUB CSS/layout, inline illustrations, tables, math, hyperlinks, audio, DRM and fixed-layout fidelity are not implemented. Encrypted-resource EPUBs are rejected. PDFs and other school documents still open in an installed external reader through a separate button; no PDF bookshelf thumbnails or automatic PDF progress are claimed.

Import bounds: 50 MB per file, about 250 MB/100 books in library, EPUB maximum 3,000 entries/100 MB declared expanded size, resource limits and four million characters of EPUB text. Files must come from trusted sources. Limits are basic resource controls, not a security audit.

Drawing and colouring now enter the existing fullscreen workspace automatically. Exit Full returns to the dashboard workspace without replacing its in-memory paint canvas. Existing rotation, Tools, Undo and Save remain. Saving artwork before leaving is still necessary; no process-death recovery is added.

All prior games, Immich/gallery and QR-image upload code retained. QR upload remains images-only, not ebook import. Release automation still uses its original tag; obtain the latest successful Actions artifact. App-private books are removed on uninstall/clear data; back up originals. Previous debug signing and GPL dependency notices apply.
