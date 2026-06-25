# AGENTS.md — My German Vocabulary (MGV)

Instructions for AI coding agents working in this repository.

## Overview

MGV is an offline Android app for personal German vocabulary study. Users import CSV word lists, data is stored in SQLite, and the app provides flashcard drills, search, and static grammar reference screens. This is not a full language course — it is a vocabulary manager and self-test tool.

## Stack

- **Language:** Kotlin (JVM 17)
- **UI:** XML layouts, ViewBinding — **no Jetpack Compose**
- **Navigation:** Android Navigation Component (`app/src/main/res/navigation/nav_graph.xml`)
- **Persistence:** `SQLiteOpenHelper` in `data/VocabularyDB.kt`
- **CSV import:** Apache Commons CSV (`use_cases/FileManagement.kt`)
- **SDK:** minSdk 30, compileSdk/targetSdk 35
- **AGP:** 8.13.2 | **Kotlin:** 2.3.0 | **Gradle:** 9.5.0

## Build & test

```bash
./gradlew assembleDebug
./gradlew assembleRelease
./gradlew test
./gradlew clean
```

Open in Android Studio and run the `app` module for device/emulator debugging.

## Architecture

Follow the existing layered layout:

| Layer | Package | Responsibility |
|-------|---------|----------------|
| Domain | `domain/` | Plain models: `Substantive`, `Verb`, `Adjective`, `Gender`, … |
| Data | `data/` | `VocabularyDB`, typed lists (`SubstantiveList`, `VerbList`, …) |
| Use cases | `use_cases/` | Business logic — no Android UI |
| Presentation | `presentation/` | Activities, Fragments, adapters |

- Application singleton: `MGVApplication` holds the shared `VocabularyDB` instance.
- `MainActivity` hosts the nav graph; grammar references are separate Activities.
- Locale handling: `LocaleManagement`, `PreferredLocale`, `BaseActivity`.

## Key files

- `data/VocabularyDB.kt` — schema, CRUD, search queries (9 tables by word type/gender)
- `use_cases/FileManagement.kt` — CSV import; column names are German
- `use_cases/ListManagement.kt` — load lists for tests and search
- `use_cases/VocabularyManagement.kt` — display helpers (strip plural hints, verb hints)
- `presentation/DefaultFragment.kt` — home: Load Files, Vocabulary Test, Search
- `res/values/strings.xml` (+ `values-de/`, `values-es/`) — all user-facing strings

## CSV import contract

Do not change column names or delimiters without updating `FileManagement.kt` and documenting in README.

- Encoding: **ISO-8859-1**
- Delimiter: **semicolon** (`;`)
- Format: RFC 4180 with header row
- Headers per type: see README.md table (`der`/`die`/`das`, `Infinitiv`, `Bedeutung`, …)
- Import replaces existing data for that category (`emptyTable` before insert)

## Database changes

When modifying `VocabularyDB` schema:

1. Increment `DATABASE_VERSION` in `VocabularyDB.kt`
2. Update `onCreate` / `onUpgrade` accordingly (current pattern drops and recreates all tables)

## UI conventions

- Portrait only; do not add landscape unless explicitly requested.
- New screens: prefer Fragments in the nav graph; use Activities only for standalone reference views (existing pattern).
- Use ViewBinding — binding classes are generated from layout XML.
- Register new destinations in `nav_graph.xml` and add string labels in all locale files.
- Gender colors: `R.color.der`, `R.color.die`, `R.color.das` via `Gender` enum.

## Localization

Supported UI locales: German (`values-de/`), English (`values/`), Spanish (`values-es/`).  
Add new user-facing strings to **all three** when changing UI text.

## Don't

- Do not introduce Jetpack Compose without an explicit request.
- Do not add network dependencies — the app is fully offline.
- Do not change CSV column headers casually; they must match user file formats.
- Do not commit secrets, keystores, or signing credentials.
- Do not refactor unrelated code when fixing a targeted issue.
- Avoid expanding scope beyond what was asked (no drive-by cleanups).

## Definition of done

- `./gradlew assembleDebug` succeeds
- New UI strings added to `values/`, `values-de/`, and `values-es/` if applicable
- Navigation wired in `nav_graph.xml` for new fragments
- DB schema version bumped if tables changed
- Changes match existing naming and package conventions

## Related docs

- [README.md](README.md) — product overview and CSV format reference for users
