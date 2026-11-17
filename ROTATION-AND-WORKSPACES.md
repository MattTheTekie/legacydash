# Rotation and fullscreen workspaces

Source update; compilation, screen layout and touch transformation on real hardware are unverified. All prior games, gallery, uploads, saved-session code and API 17 configuration are retained. Version remains 0.5.0-alpha.

Drawing and colouring use the same Studio canvas instance when toggling fullscreen. Fullscreen removes the dashboard/sidebar and reserves a compact toolbar for Exit Full, Tools, Turn 90, Screen, Undo and Save. Tools dialog exposes palette, brush/eraser, width, opacity, undo, confirmed clear, confirmed next colouring page and PNG save. Same four colouring outlines and existing four-stroke undo remain. No new fill, layers, redo or zoom tools.

Turn 90 is non-destructive view rotation, including outlines and touch coordinates through Android view transforms; repeated turns do not resample the paint bitmap. Save PNG rotates the exported image to the current view orientation. The working bitmap remains 900 by 560; fullscreen fits its aspect ratio rather than stretching to fill all space.

Screen rotation is a separate control with automatic sensor, portrait, landscape, reverse portrait and reverse landscape. Selection persists. Manifest handles orientation/screen-size changes in the activity so ordinary rotations do not recreate it and discard the current in-memory canvas. This is not process-death recovery or automatic drawing backup; save PNG before leaving the studio or closing the app. Vendor firmware may restrict some orientations or sensors.

Other dashboard pages have Full and Rotate controls in their headers. Full hides sidebar/header and shows an Exit Fullscreen overlay; it expands the page but does not redesign each module's fixed-size content. Gallery overlay has a Rotate screen button. Screen-rotation settings are also available from Settings and Gallery Settings. Android 4.2 system bars may reappear after touch; immersive-sticky is only used on API 19+. Back exits workspace/page fullscreen before navigating home.

Existing release workflow does not replace the original release; use latest successful Actions artifact. Previous debug signing, data preservation and GPL dependency notices still apply.
