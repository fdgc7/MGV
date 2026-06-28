# Manual test checklist

Run on a **device or emulator** (API 30+) before releases or after changes to vocabulary import, tests, search, or database code.

Build first:

```bash
./gradlew assembleDebug
```

Install the debug APK or run from Android Studio.

---

## General

- [ ] App launches to home screen without crash
- [ ] Toolbar menu opens (Search, grammar references, Settings, About)
- [ ] About dialog shows version number
- [ ] FAB shows stored counts per category and total
- [ ] Back navigation works from all screens
- [ ] UI strings correct in **English**, **German**, and **Spanish** (change in Settings)

---

## CSV import (Load Files)

Use semicolon-separated files, ISO-8859-1 encoding, with German header row.

For each category, import a small test file (2–3 rows) and confirm the toast shows the record count:

- [ ] Masculine nouns (`der`, `Bedeutung`)
- [ ] Feminine nouns (`die`, `Bedeutung`)
- [ ] Neuter nouns (`das`, `Bedeutung`)
- [ ] Verbs (`Infinitiv`, `Präsens`, `Präteritum`, `Perfekt`, `Bedeutung`)
- [ ] Adjectives (`Adjektiv`, `Bedeutung`)
- [ ] Adverbs (`Adverb`, `Bedeutung`)
- [ ] Conjunctions (`Konjunktion`, `Bedeutung`)
- [ ] Prepositions (`Präposition`, `Bedeutung`)
- [ ] Pronouns (`Pronomen`, `Bedeutung`)

Error handling:

- [ ] Cancel file picker → “File Not Loaded” (or equivalent)
- [ ] Wrong column headers → “Incorrect File Format”
- [ ] Re-import same category → replaces previous data (count matches new file)
- [ ] FAB counts update after each successful import

**Sample pronoun CSV:**

```text
Pronomen;Bedeutung
ich;I
du;you (informal)
er;he
```

---

## Vocabulary tests (flashcards)

Categories **without** data should have disabled buttons. After import, each enabled category should work:

- [ ] All substantives
- [ ] Masculine / feminine / neuter substantives (gender colors on reveal)
- [ ] Verbs (infinitive first, then conjugations + meaning)
- [ ] Adjectives
- [ ] Adverbs
- [ ] Conjunctions
- [ ] Prepositions
- [ ] Pronouns

Per enabled test screen:

- [ ] **Show** reveals meaning
- [ ] **Next** shows another random entry
- [ ] Tap / swipe gestures work (swipe left = next, swipe down = show)
- [ ] **Quit** returns to category list
- [ ] **Example button is invisible** before Show is tapped
- [ ] **Example button appears** after Show is tapped
- [ ] **Example button disappears** again after Next is tapped

Edge cases (see [KNOWN_ISSUES.md](KNOWN_ISSUES.md)):

- [ ] List with **exactly 1 word** — does not crash on Next
- [ ] List with **2+ words** — all entries can appear over multiple Next taps

---

## Example sentence (Gemini Nano)

Run on a **device that supports Gemini Nano** (e.g. Pixel 8 or newer with AICore):

- [ ] Tap **Show** on any flashcard → Example button appears
- [ ] Tap **Example** → button disables while generating, re-enables after
- [ ] A dialog appears with a German sentence containing the word
- [ ] Dialog can be dismissed with OK
- [ ] Tap **Next** → Example button disappears
- [ ] Sentence word count is within the configured min/max range (default 5–20)

On a **device without Gemini Nano support**:

- [ ] Tap **Example** → a toast appears with the "not available on this device" message (no crash)

Settings integration:

- [ ] Change min/max word limits in Settings → next generated sentence respects the new values
- [ ] Setting min ≥ max → rejected with error toast, value not saved
- [ ] Non-numeric input → rejected with error toast, value not saved

---

## Search

- [ ] Search with fewer than minimum characters (default 4) does nothing useful / no false positives
- [ ] Search finds imported German text
- [ ] Search finds imported meanings
- [ ] Results count toast matches list length
- [ ] Result rows show correct labels (Substantive, Verb, Pronoun, etc.)
- [ ] Pronoun results use “Pronoun | Meaning” format
- [ ] Change minimum characters in Settings → search behavior updates

---

## Grammar reference (static charts)

- [ ] Personal pronouns screen opens from menu
- [ ] Prepositions screen opens
- [ ] Comparatives and superlatives screen opens
- [ ] Adjective declension screen opens
- [ ] Images display correctly (if assets are present in the build)

---

## Settings

- [ ] Language: system / German / English / Spanish — UI updates after recreate
- [ ] Minimum search characters: valid values (0–10) accepted
- [ ] Invalid value shows error and is rejected
- [ ] Min. example words: positive integer less than max → accepted
- [ ] Max. example words: positive integer greater than min → accepted
- [ ] Min. example words ≥ max → rejected with error toast
- [ ] Max. example words ≤ min → rejected with error toast

---

## Persistence

- [ ] Force-stop app and relaunch → imported vocabulary still present
- [ ] FAB counts match data after restart
- [ ] Search still finds stored words after restart

---

## Regression smoke test (quick)

Minimum path after any small change:

1. [ ] Import one pronoun CSV
2. [ ] FAB shows pronoun count > 0
3. [ ] Pronoun test → Show → Next → Quit
4. [ ] Search finds a known pronoun
5. [ ] `./gradlew assembleDebug` succeeds

---

## Notes

| Date | Tester | Build | Result | Notes |
|------|--------|-------|--------|-------|
|      |        |       |        |       |
