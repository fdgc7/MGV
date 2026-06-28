# Known issues and technical debt

Pre-existing issues in MGV to revisit. Not introduced by a single feature — they affect multiple word types or the app as a whole.

Check this file when fixing bugs, before releases, or when touching related code.

---

## High priority

### ~~`randomElement()` off-by-one and single-item crash~~ ✓ Fixed 2026-06-28

**Files:** `data/*List.kt` (`SubstantiveList`, `VerbList`, `PronounList`, and all other typed lists), `data/VocabularyList.kt`

**Fix:** Replaced `(0 until list.size - 1).random(rand)` with `list.indices.random(rand)` in all 8 list classes. Last item is now reachable and a single-element list no longer crashes.

---

### ~~Incomplete `Parcelable` implementation for vocabulary lists~~ ✓ Fixed 2026-06-28

**Files:** `data/VocabularyList.kt`, `data/*List.kt`, `presentation/TestVocabularyFragment.kt`, `presentation/ShowSubstantiveFragment.kt`, `presentation/ShowVerbFragment.kt`, `presentation/ShowOtherFragment.kt`

**Fix:** Removed Parcel-based list transfer entirely. `TestVocabularyFragment` now passes only the `vocabularyType` string key in the Bundle. Each show fragment receives the key and calls `ListManagement.loadList()` itself, reloading from SQLite. This also eliminates the deprecated `getParcelable<T>()` calls.

---

## Medium priority

### Category screens may overflow on small devices

**Files:** `res/layout/fragment_load_files.xml`, `res/layout/fragment_test_vocabulary.xml`

**Problem:** Long vertical button chains (10 categories + Back) with no `ScrollView`.

**Impact:** Buttons at the bottom may be clipped or hard to reach on small screens.

**Suggested fix:** Wrap content in a `ScrollView` or `NestedScrollView` while keeping constraint layout behavior.

---

### Missing grammar reference drawables

**Files:** `res/layout/activity_show_personal_pronouns.xml`, `activity_show_prepositions.xml`, `activity_show_comparatives_superlatives.xml`, `activity_show_adjective_declension.xml`

**Problem:** Layouts reference `@drawable/personal_pronouns`, `prepositions`, `comparatives_superlatives`, `adjective_declension`, but those assets are not in `res/drawable/` in the repository (only `ic_settings.xml` is present).

**Impact:** Grammar reference screens may show broken images at runtime.

**Suggested fix:** Add the image assets to `res/drawable/` or document that they must be supplied locally.

---

### `loadList()` uninitialized return on unknown type

**File:** `use_cases/ListManagement.kt`

**Problem:** `vocabularyList` is `lateinit` and only assigned in known `when` branches. An unknown `vocabularyType` string would crash on return.

**Impact:** Low today (call sites use fixed strings), but fragile if refactored.

**Suggested fix:** `else` branch throwing a clear error or returning an empty list.

---

## Low priority

### Dead string resources (grammar chart C not implemented)

**Files:** `res/values/strings.xml` (+ `values-de/`, `values-es/`)

**Strings:** `action_pronouns`, `show_pronouns_activity_label`

**Problem:** Defined but unused — no menu item or Activity (by design; only personal pronouns chart exists).

**Impact:** None at runtime; minor clutter.

**Suggested fix:** Remove strings or implement the feature if scope changes.

---

### `ShowPersonalPronounsActivity` zoom not implemented

**File:** `presentation/ShowPersonalPronounsActivity.kt`

**Problem:** `TODO: Zoom in ImageView` — reference chart cannot be zoomed.

**Impact:** UX only on the personal pronouns grammar screen.

---

### Dark theme forced off

**File:** `MGVApplication.kt`

**Problem:** `AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)` with a TODO to add theme choice.

**Impact:** App always uses light theme regardless of system setting.

---

### SQL search uses string concatenation

**File:** `data/VocabularyDB.kt`

**Problem:** `search*` methods build `LIKE` queries via string concatenation instead of bound parameters.

**Impact:** Acceptable for a local offline app with trusted user data; not ideal pattern for special characters in search input.

---

### Deprecated `getParcelable()` API usage

**Files:** `presentation/ShowSubstantiveFragment.kt`, `ShowVerbFragment.kt`, `ShowOtherFragment.kt`

**Problem:** `arguments?.getParcelable<T>()` without type-safe overload on newer Android APIs.

**Impact:** Deprecation warnings; may need migration for future SDK requirements.

---

## Database upgrade behavior

**File:** `data/VocabularyDB.kt`

**Problem:** `onUpgrade()` drops and recreates **all** tables.

**Impact:** Any schema version bump wipes all imported vocabulary unless export/backup is added.

**Note:** Intentional for early versions; document for users if this remains long term.

---

## How to use this file

1. When fixing an item, note the fix and date in the item or remove it.
2. Add new issues here instead of only leaving `TODO` comments in code.
3. Run [MANUAL_TEST_CHECKLIST.md](MANUAL_TEST_CHECKLIST.md) after fixes that touch vocabulary flow.
