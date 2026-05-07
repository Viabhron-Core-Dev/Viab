# Android Hybrid Launcher Blueprint: Project "Nexus"

## 1. Project Vision
A ultra-customizable Android environment combining **Lawnchair's** desktop aesthetics with the persistent, multi-layered utility of **Everywhere Launcher** and **NetSpeed Indicator**.

---

## 2. Core Architecture

### A. Desktop Engine (Nova/Lawnchair Style)
*   **Homescreen**: 
    *   **Grid System**: Flexible nxm grid with sub-grid positioning.
    *   **Clock Widget**: A specialized `RemoteViews` implementation synced to `java.util.Calendar` or `Instant` with custom font support.
    *   **Dock**: A separate `paged` or `static` dock container with blur background support.
    *   **Gestures**: Intercepts `onSingleTap`, `onDoubleTap`, and `onFling` on the workspace background (e.g., Double tap to sleep via Accessibility API).
*   **App Drawer**:
    *   **Folders**: Tabbed or grouped app drawer entries.
    *   **Search Bar**: Integrated `EditText` with fuzzy search; option to auto-hide when scrolling.
    *   **Sorting**: Alphabetical, Install Date, or Frequency of Use.

### B. Handle & Gesture Manager (Everywhere Launcher Logic)
*   **The Handle Service**: 
    *   Uses `WindowManager` overlays.
    *   **Configurations**: Thickness (dp), Position (Y-axis), Transparency (Alpha), and Color.
    *   **Gesture Mapping**: 
        *   `Single Tap`: Toggle Volume/Ringer.
        *   `Double Tap`: Open specific App (e.g., NetSpeed).
        *   `Swipe Up/Down`: Lock Screen / Expand Notifications.
        *   `Long Press`: Enter Edit Mode.

### C. The Pro Sidebar (Slide-out Overlay)
*   **Elements (Multi-Page)**:
    1.  **Apps/Shortcuts**: Direct intent launches.
    2.  **Widgets**: Scaled widget hosts within the sidebar.
    3.  **Folders**: 
        *   Styles: First-icon preview, Grid-of-4, or custom Slidestyle.
    4.  **Android Actions**: 
        *   **Volume Control**: Horizontal sliders for Ringer, Media, and Alarm.
        *   **Brightness**: Step-based or slider-based brightness override.
        *   **Orientation**: Toggle for Auto-rotate lock.
        *   **Screenshot**: Triggers `MediaProjection` or `Accessibility` screenshot action.
*   **UX Controls (Based on User Images)**:
    *   **Stick Mode**: Snap to Bottom/Right or Center/Right.
    *   **Length Mode**: "Wrap Content" (sidebar height matches items) vs "Full Screen".
    *   **Auto-Close**: Configurable timeout (default 5000ms).
    *   **Icon Styles**: Circle, Square, Squircle with custom tinting (Image Color).

---

## 3. High-Performance Feature Set

### A. Call Recording (The "CallU" Bridge)
*   **Trigger**: Background `PhoneStateListener` detects incoming/outgoing calls.
*   **Logic**: 
    1.  Starts a background `AudioRecord` pipe.
    2.  Attempts `VOICE_CALL` source.
    3.  **Fallback**: If restricted by Android 10+, uses **Accessibility Service** audio intake.
*   **Storage**: Lightweight `.amr` encoding to minimize I/O and storage pressure.

### B. "Wiggle" Edit Mode (Identity UX)
*   **Trigger**: Long-press (500ms) on any element (Homescreen or Sidebar).
*   **Visuals**: 
    *   **Scale**: 0.95x.
    *   **Animation**: 20Hz rotation oscillation (-1deg to 1deg).
*   **Behavior**: 
    *   In **Launcher**: Items snap to grid; dropping on another item creates a **Folder**.
    *   In **Sidebar**: Items swap positions using `ItemTouchHelper`. Dragging to top/bottom "Trash Zone" removes it.
    *   **Folders**: Supports icons previews (First-in-folder, Grid, or Slider).

### C. System Performance (3GB RAM Optimization)
*   **Persistence**: Uses a **Foreground Service** with a permanent notification to prevent the system from killing the app process.
*   **Accessibility Shield**: Provides a "Quick Reset" toggle in the sidebar that jumps to Android Accessibility settings if the service becomes unresponsive.
*   **Optimization**: 
    *   **Icon Downsampling**: Renders all icons at a fixed 48dp to save VRAM.
    *   **Zero-Blur Mode**: Detects low system resources and switches to flat backgrounds to reduce GPU overdraw.

---

## 4. Permission Requirements (Mandatory)
*   **System Alert Window**: For Sidebar/Handles/NetSpeed.
*   **Accessibility Service**: For Screen Lock, Screenshot, and Call Audio Fallback.
*   **Record Audio & Phone State**: For Call Recording.
*   **Modify System Settings**: For Brightness/Timeout control.
*   **Query All Packages**: To populate the app drawer.

---

## 5. Development Strategy (Phase-by-Phase)

1.  **Skeleton**: Launcher3 base with dock/widget support.
2.  **Handles**: Background overlay service for the high-customizable edge triggers.
3.  **Actions**: Implementation of Volume, Brightness, and System sliders.
4.  **Edit Mode**: Finalize the "Wiggle" drag logic.
5.  **Persistence**: Fine-tune the foreground service to ensure 99% uptime on low-RAM devices.

---

## 6. Comparison Chart: Key Features vs Reference Repos

| Requirement | implementation Source | Logic Refinement |
| :--- | :--- | :--- |
| **App Drawer Folders** | [Lawnchair](https://github.com/LawnchairLauncher/Lawnchair) | Use their `FolderIcon` and `FolderInfo` classes. |
| **Multi-Action Handles** | [Everywhere Launcher](https://github.com/michael-rapp/AndroidSidebar) | Extend with `ActionHandler` for volume/toggles. |
| **System Sliders** | Custom Development | Use `AudioManager` and `Settings.System`. |
| **Sleep/Screenshot** | [SideActions](https://github.com/MuntashirAkon/AppManager) | Accessibility Service events. |
| **Call Recording** | Custom Engine | `AudioRecord` with Accessibility fallback. |
| **NetSpeed Strip** | [Internet-Speed](https://github.com/deepak140596/Internet-Speed) | Status bar overlay via `WindowManager`. |
