# MGV — My German Vocabulary

A personal Android app for German learners who maintain their own word lists. Import vocabulary from CSV files, store it locally, drill yourself with flashcards, search your dictionary, and consult built-in grammar reference charts — all offline.

**Package:** `com.fdanielgarcia.mygermanvocabulary`  
**License:** [GPL-3.0](LICENSE)

## Features

### Import vocabulary (CSV)

Load semicolon-separated CSV files from device storage. Each word type uses German column headers. Imported data is stored in a local SQLite database (`Vocabulary.db`).

Supported categories:

| Category | CSV columns |
|----------|-------------|
| Masculine nouns | `der`, `Bedeutung` |
| Feminine nouns | `die`, `Bedeutung` |
| Neuter nouns | `das`, `Bedeutung` |
| Verbs | `Infinitiv`, `Präsens`, `Präteritum`, `Perfekt`, `Bedeutung` |
| Adjectives | `Adjektiv`, `Bedeutung` |
| Adverbs | `Adverb`, `Bedeutung` |
| Conjunctions | `Konjunktion`, `Bedeutung` |
| Prepositions | `Präposition`, `Bedeutung` |
| Pronouns | `Pronomen`, `Bedeutung` |

Files are read as **ISO-8859-1** with **semicolon** (`;`) delimiters.

### Vocabulary tests (flashcards)

Practice by category: all nouns, masculine/feminine/neuter nouns, verbs, adjectives, adverbs, conjunctions, prepositions, or pronouns.

- A random entry is shown; tap or swipe to reveal the meaning.
- Nouns hide the article at first, then reveal gender with color coding (`der` / `die` / `das`).
- Verbs show infinitive forms; conjugations and meaning are revealed on demand.
- Categories without loaded data are disabled until a CSV is imported.

After revealing the meaning, an **Example** button appears. Tap it to generate a contextual German example sentence for the current word using Gemini Nano on-device (ML Kit GenAI). The result is shown in a dialog. If the device does not support Gemini Nano, a message is shown instead. Sentence length (in words) is configurable in Settings.

### Search

Search across all loaded vocabulary (German text and meanings). Minimum character count is configurable in Settings (default: 4).

### Grammar reference

Static reference charts for:

- Personal pronouns
- Prepositions
- Comparatives and superlatives
- Adjective declension

### Settings

- UI language: system default, German, English, or Spanish
- Minimum characters required before search runs
- Min. / max. example sentence length in words (default: 5 / 20)

## Screenshots

_Screenshots can be added here._

## Requirements

- Android 11+ (API 30)
- Portrait orientation only
- **Example sentence feature** requires a device that supports Gemini Nano (ML Kit AICore). The feature degrades gracefully on unsupported devices.

## Building

```bash
./gradlew assembleDebug    # Debug APK
./gradlew assembleRelease  # Release APK
./gradlew test             # Unit tests
```

Open the project in Android Studio and run the `app` configuration for on-device debugging.

Release APKs are named `MGV-{versionName}-{versionCode}_{date}.apk`.

## Project structure

```
app/src/main/java/com/fdanielgarcia/mygermanvocabulary/
├── domain/          # Data models (Substantive, Verb, Adjective, …)
├── data/            # SQLite database and list types
├── use_cases/       # Business logic (file import, search, locale, …)
└── presentation/    # Activities, Fragments, adapters
```

Navigation is handled with the Android Navigation Component (`nav_graph.xml`). UI uses ViewBinding (not Jetpack Compose).

## Tech stack

- Kotlin
- AndroidX (AppCompat, Material, Navigation, Preference)
- SQLite via `SQLiteOpenHelper`
- Apache Commons CSV for file import
- ML Kit GenAI (`com.google.mlkit:genai-prompt`) for on-device example sentence generation (Gemini Nano)

## For contributors

- [AGENTS.md](AGENTS.md) — conventions and guidance for AI coding tools
- [KNOWN_ISSUES.md](KNOWN_ISSUES.md) — pre-existing bugs and technical debt to revisit
- [MANUAL_TEST_CHECKLIST.md](MANUAL_TEST_CHECKLIST.md) — device/emulator test plan before releases
