# 0.13.0-alpha / shared frame presentation

FrameActivity is the active launcher and a centralized presentation facade over SchoolActivity and the existing feature inheritance chain. This is not removal/refactoring of every old Activity class. No databases, preferences or feature implementations are deleted.

One active page shell: scrollable 88 dp navigation rail (Home, Photos, School, Games, Uploads, Settings), selected navigation treatment, common header with Home/fullscreen/rotation actions, and a scrolling content area. Card columns use available display dp (one to four). Existing screen-configuration handling is retained; live rotation and keyboard layout need hardware testing. Normal pages clear legacy fullscreen window flags to permit resize for the keyboard; dedicated gallery/reader/studio modes keep their specialized behavior.

Shared button/card/form/section/icon-control factories replace the active mixed implementations. Action rows at least 64 dp, inputs at least 60 dp; compact controls target at least 48 dp subject to their parent's existing toolbar height. Consistent rounded surfaces, pressed/selected treatment, theme palette, 18 sp body/actions, 20 sp card/section titles and 14 sp secondary/control labels. Small native text labels are raised to 14 sp on layout; checkbox text/height is normalized. No provider WebView restyling or changes to canvas-rendered text.

Home has clock/date and eight destinations: Photos, School, Books, Create, Games, Weather, Uploads, Explore. Create groups drawing/colouring/projects. Explore retains Calendar, Daily Notes, creature, space, cameras, news, Santa simulation, DLNA and apps. Themes, birthdays and gallery settings stay reachable; optional Home scene and birthday greeting retained. QR transfer remains centralized in Uploads.

Common controls also apply to School ink and PDF markup toolbars, inherited gallery controls and game launch cards. Existing album/book/project thumbnail layouts, native Spinner/dialog internals, custom game/canvas art and specialized fullscreen roots are not all rebuilt. Existing Immich selected states are not overwritten by generic view traversal. Unknown/custom pages may have no selected rail item rather than a misleading selection. Home action is labelled Home, not a fake hierarchical Back control.

All previously committed features remain inherited, including saved School notes/agenda and temporary PDF markup. No network/backend, dependency, signing or data-schema changes in this pass. Weather 403 diagnosis is not changed or claimed resolved.

Source-only first consolidation pass. No frame screenshot, physical screen dimensions/density, touch test, compilation result or visual acceptance test is available. Native dialog look, narrow-screen clipping, device font scale, keyboard resize, rotation, fullscreen exit, PDF overlay alignment and custom full-screen modes require CI/device validation. This is not a claim of complete pixel-level consistency across every feature.
