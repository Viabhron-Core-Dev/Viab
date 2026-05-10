# Nexus Hybrid Launcher: Full Native Production Blueprint

## 1. Project Status Summary
*   **Target Device**: Low-end Android (3GB RAM).
*   **Architecture**: Native Kotlin + Gradle + SQLite (No heavy dependencies).
*   **Current Progress**: **~40%** (CI/CD debugging in progress, core services bootstrapped).

---

## 2. Implemented Components (Current Progress)

### A. Infrastructure & CI/CD
*   [x] **Build System**: Gradle 8.2 + Kotlin DSL setup (Transitioned to system Gradle for CI stability).
*   [x] **GitHub Actions**: Automated `.apk` build workflow (Optimized to use runner-provided Gradle).
*   [x] **Manifest**: Permission mapping (System Overlay, Accessibility, Record Audio).

### B. Persistence & Monitoring (The "Always-On" Layer)
*   [x] **NexusCoreService**: Foreground service with NetSpeed logic (`TrafficStats`).
*   [x] **NexusAccessibilityService**: Global actions (Sleep, Screenshot, Notifications).
*   [x] **SQLite Database**: Schema for workspace layout and sidebar state.
*   [x] **CallRecordingService**: AMR-compliant voice capture engine.

### C. Launcher UI Skeleton
*   [x] **Workspace**: Paged layout container with "Wiggle" logic.
*   [x] **App Drawer**: `AppLoader` scanning for system activities + Search logic.
*   [x] **Adapters**: Unified `LauncherAdapter` supporting drag/drop visuals.
*   [x] **Sidebar**: `HandleManager` for multiple edge triggers.

---

## 3. The Roadmap to 100+ Files (Full Depth)

To compete with Launchair/Nova, the following sub-packages must be built:

### A. Widget Hosting (Complex Layer)
*   [ ] `logic/WidgetHostManager.kt`: Handling `AppWidgetHost` callbacks.
*   [ ] `ui/WidgetContainer.kt`: Dynamic resizing of widgets on the grid.
*   [ ] `data/WidgetInfo.kt`: Persistence of widget positioning.

### B. Gesture & Input Engine
*   [ ] `logic/GestureDetector.kt`: Custom listeners for Swipe Up, Pinch, and Two-finger rotations.
*   [ ] `logic/HapticManager.kt`: Optimized vibration feedback profiles for high-end feel.

### C. The Pro Sidebar (Actions & Sliders)
*   [ ] `ui/sliders/VolumeSlider.kt`: Custom View for Media/Ringer control.
*   [ ] `ui/sliders/BrightnessSlider.kt`: System brightness override logic.
*   [ ] `logic/ShortcutManager.kt`: Support for deep-linking into app actions (e.g., Open New Tab in Chrome).

### D. Deep Performance Tuning
*   [ ] `logic/MemoryGuard.kt`: Background task that kills non-essential Nexus threads when system RAM is <200MB.
*   [ ] `logic/Caching/IconCache.kt`: Disk-based LRU cache to prevent icon reload lag.

---

## 4. File Structure Visualization

```text
app/src/main/kotlin/com/nexus/launcher/
├── data/           # NexusItem, WidgetInfo, FolderInfo (10+ files)
├── db/             # NexusDatabase, ItemDao, SettingsProvider (5+ files)
├── drawer/         # Search, AlphabeticalScroller, Tabs (15+ files)
├── logic/          # AppLoader, ActionHandler, IconCache, Gestures (30+ files)
├── overlay/        # Handles, SidebarController, TrashZone (15+ files)
├── receivers/      # Call, Package, Boot, Power (8+ files)
├── services/       # Core, Accessibility, Recorder, Monitor (10+ files)
└── ui/             # Workspace, Hotseat, DragLayer, Folders (20+ files)
```

---

## 5. Next Critical Steps
1.  **Icon Pack Integration**: Implement a parser for standard icon packs from the Play Store.
2.  **Handoff Logic**: Finalize the "Wiggle" transition where a long-pressed Sidebar item can be dropped onto the Homescreen.
3.  **WhatsApp Hook**: Research `NotificationListenerService` to detect active WhatsApp VOIP calls for the call recorder.
