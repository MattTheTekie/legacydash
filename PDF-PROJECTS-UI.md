# 0.7.0-alpha / PDFs, editable art and UI refresh

Source implementation only. No successful build, emulator/device test, security audit or visual acceptance test is claimed. API 17 target and prior features retained.

## PDFs
Native Pdfium rendering, not an external browser or uploaded cloud document. PDF import appears under Import book / PDF. Imported PDFs join the existing bookshelf with first-page covers, metadata, page progress and Continue Reading. Reader includes previous/next, jump to page, pinch zoom/pan, fit-page reset, saved page bookmarks and last-page persistence. Page images render at up to 1200 by 1600 using RGB_565; pinch zoom enlarges that bitmap rather than rerendering at arbitrary resolution. No PDF text selection/search, editing, annotations, form entry or password UI.

Compatibility/security trade-off: com.github.barteksc:pdfium-android:1.9.0 supports API 14+ but bundles an old PDF engine. This is not a modern hardened PDF reader; use trusted files only. Native renderer availability/ABI compatibility and packaging must be verified on the frame. Newer Android PDF engines were not silently substituted because they raise minimum API. Dependency is Apache-2.0; https://github.com/barteksc/PdfiumAndroid and its bundled third-party notices apply.

## Editable projects
Drawing and colouring saves now differ from PNG exports. Projects keep the transparent paint bitmap plus page identity, brush width/opacity/colour, eraser state, view rotation and title. Gallery thumbnails, reopen, rename and confirmed delete included. Autosave snapshots occur about 1.2 seconds after stroke release, on leaving the studio and on app pause. Save explicitly snapshots too. Writes are serialized; individual paint/thumbnail/metadata files use AtomicFile, not a single cross-file transaction. Sudden termination can still leave metadata/preview from a neighbouring save. Undo history is not persisted. Projects live in private app storage and are removed on uninstall/clear data; originals should be exported/backed up. Basic limit 100 projects / about 100 MB.

Tools menu separates editable project saving from PNG export. Existing four colouring outlines and four-stroke undo retained; no additional fill/layers/redo features are claimed. Fullscreen studio remains the default.

## UI
Rebuilt dashboard/app shell with compact drawn-icon navigation, gradient clock panel, grouped collection/create/explore cards, consistent typography/spacing, icon action rows and styled dialog controls. Home includes direct Art Projects access. Core theme replaces default button/edit-field backgrounds. This is a focused home/navigation/control redesign; old calendar widgets and game art are not all replaced with new components. Remains native/API-17-compatible rather than claiming current Material-library compatibility.

Existing release workflow still uses its original tag. Use latest successful Actions APK artifact. Existing debug signing, data preservation and GPL dependency notices remain applicable.
