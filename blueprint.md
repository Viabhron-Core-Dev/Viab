# Nexus Hybrid Launcher: Technical Blueprint & Roadmap

## 🎯 Project Vision
A production-grade, low-footprint Android launcher (3GB RAM target) featuring a hybrid workspace, a persistent action-sidebar, and integrated system utilities (call recording, net-speed monitoring).

---

## 🏗️ Development Phases

### Phase 1: Infrastructure & Core Engine [IN PROGRESS]
*   **Goal**: Establish a stable build environment, CI/CD, and the "Always-On" system layer.
*   [x] **Build System**: Gradle 8.2 + Kotlin DSL. (Note: Using system Gradle in CI for stability).
*   [x] **CI/CD**: GitHub Actions for automated `.apk` generation.
*   [x] **Manifest & Permissions**: Deep-level permissions (Accessibility, Overlays, Storage).
*   [x] **Service Layer**: 
    *   `NexusCoreService`: Traffic monitoring & memory oversight.
    *   `NexusAccessibilityService`: Handles global UI actions.
    *   `CallRecordingService`: Voice capture logic.

### Phase 2: Workspace & Layout Logic [PENDING]
*   **Goal**: The main UI interactions: Drag-and-drop, grid calculations, and paged workspace.
*   [x] **Basic Workspace**: Paged container.
*   [ ] **Wiggle Mode**: Logic for long-press induced edit states.
*   [ ] **Widget Hosting**: Implementing `AppWidgetHost`.
*   [ ] **Icon Pack Parser**: Compatibility with standard icon packs.

### Phase 3: The Pro Sidebar (Nexus-Edge) [PENDING]
*   **Goal**: The unique selling point—a customizable edge panel.
*   [x] **Sidebar Foundation**: Handle detection and fragment overlay.
*   [ ] **Quick Actions**: WiFi/Bluetooth toggles, Brightness/Volume sliders.
*   [ ] **Pinned Apps**: Independent of workspace layout.

### Phase 4: Performance & Hardening [PENDING]
*   **Goal**: Optimization for low-end devices and security.
*   [ ] **Icon Cache**: Disk-based LRU caching.
*   [ ] **Memory Guard**: Intelligent process management.
*   [ ] **Encryption**: Secure storage for sensitive logs.

---

## 🪵 Project History & Troubleshooting Log
*   **2026-05-10 (AM)**: 
    *   *Issue*: Gradle Wrapper (`gradlew`) failing due to binary corruption in environment.
    *   *Fix*: Switched CI to system-installed `gradle`.
*   **2026-05-10 (PM)**:
    *   *Issue*: Duplicate `attr/dragThreshold` resource conflict and `AndroidManifest` namespace deprecation failures.
    *   *Fix*: Removed `package` from Manifest. Updated `android-sidebar` to `5.1.0`. Bumped JVM target to `17`.
    *   *Outcome*: Cleaned up dependency graph. CI rebuild triggered.

---

## 🚦 Phase 1 Stabilization: CI/CD Status
*   **Status**: Finalizing.
*   **Problem**: `mergeDebugResources` failed due to third-party library attribute collision and Manifest-level package declaration.
*   **Resolution**: 
    1.  Dependency sync: Updated all core libraries to latest compatible versions (Material 1.12, Sidebar 5.1).
    2.  Purged legacy Manifest `package` field in favor of `build.gradle` namespace.
*   **Verification**: Monitoring GitHub Actions for successful artifact generation.

---

## ✅ Turn-by-Turn Checklist (Context Guard)
- [x] **Infrastructure**: Build stabilized? (Yes, moved to system gradle).
- [ ] **Core Engine**: `NexusCoreService` running? (Skeleton implemented).
- [ ] **UI Layer**: Workspace grid functional? (Basic implementation exists).
- [ ] **Monitoring**: NetSpeed/RAM stats working? (Logic in logic/ but needs UI bridge).

---

## 📍 Current Focus: [Phase 1 Finalization & Phase 2 Kickoff]
1.  Verify SQLite schema stability for Workspace items.
2.  Kickoff **Wiggle Mode** implementation—handling touch interception for dragging.
3.  Implement **IconCache** to prevent jank during app drawer scrolls.

---

## 📂 File Structure Overview
```text
app/src/main/kotlin/com/nexus/launcher/
├── data/           # POJOs for SQLite (item type, positioning)
├── db/             # Room/SQLite Database + Migrations
├── drawer/         # App Drawer UI & Search logic
├── logic/          # Core mechanics (Icon Loader, Drag Controller)
├── overlay/        # Sidebar UI and Trigger Zones
├── receivers/      # System event listeners (Boot, Power, Calls)
├── services/       # Persistent background operations
└── ui/             # View Groups and Custom Components
```
